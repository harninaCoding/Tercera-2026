package test.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayDeque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Adulto;
import modelo.Estado;
import modelo.Menor;
import modelo.Ser;
import modelo.TipoPago;
import modelo.OM.SeresManager;

class EstadoAbrirCerrarTest {

	private Estado estado;
	private SeresManager seresManager;
	private final int cantidadSeresSector = 10;

	@BeforeEach
	void beforeEach() {
		estado = new Estado();
		seresManager = new SeresManager();

		// Poblamos con una población estándar de 10 de cada tipo
		estado.getMenores().addAll(seresManager.getMenores(cantidadSeresSector));
		estado.getParados().addAll(seresManager.getAdultos(cantidadSeresSector));
		estado.getTrabajadores().addAll(seresManager.getAdultos(cantidadSeresSector));
		estado.getAncianos().addAll(seresManager.getAncianos(cantidadSeresSector));

		// Establecemos valores por defecto para demanda/producción para evitar NaN / división por cero
		estado.cantidadProducidaPorTrabajador = 0.0;
		estado.totalDemandado = 1000.0;
	}

	/* =========================================================================
	   1. PRUEBAS PARA cerrarPeriodo
	   ========================================================================= */

	/**
	 * 1º) Hay dinero:
	 * Establece una población suficiente para poder hacer las pruebas (beforeEach).
	 * En este caso lo que tenéis que hacer es ver que las propiedades acaban teniendo los resultados esperados.
	 */
	@Test
	void testPagarHayDineroSuficiente() {
		// Calculamos el presupuesto requerido para pagar a todos los sectores por completo
		// Menores: 10 * 100 = 1000. Ancianos: 10 * 50 = 500. Trabajadores: 10 * 200 = 2000.
		// Pago total necesario del estado = 3500.0 (los parados consumen sus propios ahorros o devuelve 0/negativo)
		double capitalInicial = 10000.0;
		estado.setCapital(capitalInicial);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Verificamos los resultados
		// Los menores deben tener su factorDesarrollo incrementado en 5.55 al haber cobrado al completo
		for (Menor menor : estado.getMenores()) {
			assertEquals(5.55, menor.getFactorDesarrollo(), 0.01);
		}

		// Los trabajadores cobran 200, su necesidad es 100. Por lo tanto, sus ahorros aumentan en 100
		for (Adulto trabajador : estado.getTrabajadores()) {
			assertEquals(100.0, trabajador.ahorros, 0.01);
		}

		// Los ancianos cobran 50, su necesidad es 50. Su esperanza de vida no varía (excepto por el envejecimiento natural o reducción si no cobran)
		for (Ser anciano : estado.getAncianos()) {
			// Los ancianos deberían seguir vivos al haber sido alimentados por completo
			assertTrue(anciano.isVivo());
		}
	}

	/**
	 * 2º) No hay dinero:
	 * Reutiliza los mismos valores de situación que en la prueba anterior.
	 * Comprueba los resultados.
	 */
	@Test
	void testPagarNoHayDinero() {
		// Establecemos el capital a 0
		estado.setCapital(0.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Comprobamos los resultados. Con capital 0, los sectores reciben pagos reducidos.
		// Pago a menores: 1000 * 0.45 = 450. Como el capital era 0, se reduce al máximo.
		// Confirmamos que el factor de desarrollo se ve afectado por la reducción.
		double expectedFactor = 5.55 * 0.45; // 2.4975
		for (Menor menor : estado.getMenores()) {
			assertEquals(expectedFactor, menor.getFactorDesarrollo(), 0.01);
		}
	}

	/**
	 * 2º) No hay dinero - Especialización:
	 * Comprueba si no tiene dinero para pagar a los ancianos, ancianos y menores, etc.
	 */
	@Test
	void testPagarNoHayDineroEspecializado() {
		// Establecemos un presupuesto específico que solo basta para pagar a los Menores al completo, pero nada para el resto
		// Los menores necesitan 1000.0. Fijamos el capital exactamente en 1000.0.
		estado.setCapital(1000.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Los menores deben cobrar por completo (factorDesarrollo = 5.55)
		for (Menor menor : estado.getMenores()) {
			assertEquals(5.55, menor.getFactorDesarrollo(), 0.01);
		}

		// El capital habrá sido agotado por los menores, de modo que los ancianos y trabajadores reciben pagos mínimos.
		// Los ancianos reciben su mínimo (0.3 de 50 = 15.0), al no quedar capital para ellos.
		// Esto provoca que su esperanza de vida disminuya. Comprobamos que sufren esta reducción en su esperanza de vida.
		for (Ser anciano : estado.getAncianos()) {
			// La esperanzaVida base es 80 (desde SeresManager)
			// Proporción = 15 / 50 = 0.3. Como proporción >= 0.3, pierde (1 - 0.3) = 0.7
			// esperanzaVida = 80 - 0.7 = 79.3
			assertEquals(79.3, anciano.getEsperanzaVida(), 0.01);
		}
	}

	/**
	 * 3º) No hay dinero, pago a parados (pago una parte o todo de la NV)
	 * Tener en cuenta si calcula bien el pago a parados (necesito que algunos parados tengan una cantidad de ahorros)
	 */
	@Test
	void testPagarAParadosConAhorros() {
		// Limpiamos los parados y añadimos casos específicos
		estado.getParados().clear();
		
		try {
			Adulto paradoConAhorros = new Adulto(25, 50);
			paradoConAhorros.ahorros = 150.0; // tiene 150 de ahorros, la necesidad es 100
			
			Adulto paradoSinAhorros = new Adulto(25, 50);
			paradoSinAhorros.ahorros = 0.0; // tiene 0 de ahorros, la necesidad es 100
			
			estado.getParados().add(paradoConAhorros);
			estado.getParados().add(paradoSinAhorros);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		estado.setCapital(5000.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Verificamos que paradoConAhorros gastó de sus ahorros para cubrir la necesidad vital
		Adulto p1 = null;
		Adulto p2 = null;
		for (Adulto p : estado.getParados()) {
			if (p.getEdadActual() == 26) { // envejece 1 año
				if (p.getAhorros() > 0) {
					p1 = p;
				} else {
					p2 = p;
				}
			}
		}
		
		// Si ambos envejecieron y se procesaron:
		// p1 debe tener ahorros = 150 - 100 = 50
		// p2 debe tener ahorros = 0
		assertNotNull(p1);
		assertEquals(50.0, p1.ahorros, 0.01);
		assertNotNull(p2);
		assertEquals(0.0, p2.ahorros, 0.01);
	}

	/**
	 * 4º) En cualquier caso debes comprobar si pagar menos de la cuenta incide, negativamente, 
	 * en la esperanza de vida, como de debe ser, de cada ser. Ten en cuenta que los Adultos 
	 * siempre cobran su necesidad vital, por lo tanto no sufren deterioro. A los menores 
	 * no les afecta en su esperanza de vida. Por lo tanto solo hay que probar a los ancianos.
	 */
	@Test
	void testPagarMenosDeLaCuentaDeterioroAncianos() {
		// Establecemos el capital a 0.0 para forzar la reducción máxima a los Ancianos
		estado.setCapital(0.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Verificamos que los ancianos sufrieron una reducción en su esperanza de vida
		for (Ser anciano : estado.getAncianos()) {
			// Esperanza de vida base = 80
			// Cobran 15 (mínimo porque el capital es 0, es decir, 0.3 * 50)
			// Proporción = 0.30 -> pierde 1 - 0.30 = 0.70
			// esperanzaVida = 80 - 0.70 = 79.3
			assertEquals(79.3, anciano.getEsperanzaVida(), 0.01);
		}

		// Verificamos que los menores NO perdieron esperanza de vida (sigue en 50)
		for (Menor menor : estado.getMenores()) {
			assertEquals(50.0, menor.getEsperanzaVida(), 0.01);
		}
	}

	/**
	 * El siguiente punto es envejecer a la población. Plantea una situación inicial.
	 * 1º) Debes plantear si los seres envejecen bien. También deberías probar si en los adultos 
	 * se controla bien la propiedad “periodosEnEstado” que controla cuanto lleva en el estado actual.
	 */
	@Test
	void testEnvejecerPoblacionYPeriodosEnEstado() {
		// Establecemos capital suficiente para que nadie muera ni sufra deterioro prematuro
		estado.setCapital(10000.0);

		// Registramos los valores iniciales para un subconjunto
		int edadAncianoInicial = estado.getAncianos().iterator().next().getEdadActual();
		int edadMenorInicial = estado.getMenores().iterator().next().getEdadActual();
		
		Adulto trabajadorSample = estado.getTrabajadores().iterator().next();
		int edadTrabajadorInicial = trabajadorSample.getEdadActual();
		int periodosEnEstadoInicial = trabajadorSample.getPeriodosEnEstado();

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Verificamos el incremento de edad
		assertEquals(edadAncianoInicial + 1, estado.getAncianos().iterator().next().getEdadActual());
		assertEquals(edadMenorInicial + 1, estado.getMenores().iterator().next().getEdadActual());
		assertEquals(edadTrabajadorInicial + 1, trabajadorSample.getEdadActual());

		// Verificamos que periodosEnEstado se incrementa para los Adultos
		assertEquals(periodosEnEstadoInicial + 1, trabajadorSample.getPeriodosEnEstado());
	}

	/**
	 * Luego debes jubilar
	 * 1º) Debes comprobar si los seres Adultos que llegan a una edad concreta, pasan a ser Ancianos (Ser) 
	 * y ceden todos sus ahorros al estado. Para esta prueba necesitas seres que estén al borde de jubilarse, 
	 * que tengan ahorros.
	 */
	@Test
	void testJubilarAdultoCedeAhorros() {
		// Limpiamos sectores y añadimos un adulto con 64 años (edad de jubilación es 65)
		estado.getMenores().clear();
		estado.getParados().clear();
		estado.getTrabajadores().clear();
		estado.getAncianos().clear();

		double ahorrosJubilado = 1500.0;
		double capitalInicial = 5000.0;
		estado.setCapital(capitalInicial);

		try {
			Adulto aPuntoDeJubilarse = new Adulto(64, 90);
			aPuntoDeJubilarse.ahorros = ahorrosJubilado;
			estado.getParados().add(aPuntoDeJubilarse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// El adulto debería estar jubilado y haberse trasladado al sector de Ancianos
		assertEquals(0, estado.getParados().size());
		assertEquals(1, estado.getAncianos().size());

		// Los ahorros deben cederse al capital del Estado
		// capital = capitalInicial (5000) + ahorros restantes tras alimentarse
		// Tras la jubilación, los ahorros son transferidos.
		assertEquals(capitalInicial + ahorrosJubilado - TipoPago.parado.getNecesidadVital(), estado.getCapital(), 0.01);

		// El nuevo Anciano debe ser un Ser con las propiedades del adulto
		Ser jubilado = estado.getAncianos().iterator().next();
		assertEquals(65, jubilado.getEdadActual());
		assertEquals(90.0, jubilado.getEsperanzaVida(), 0.01);
	}

	/**
	 * Finalmente enterrar.
	 * 1º) En este caso debes tener en cuenta que los seres que mueren son los que llegan a la edad 
	 * en la que igualan su esperanza de vida. Puedes probar con pocos seres de diferentes Sectores.
	 */
	@Test
	void testEnterrarMuertePorEsperanzaVida() {
		estado.getMenores().clear();
		estado.getParados().clear();
		estado.getTrabajadores().clear();
		estado.getAncianos().clear();

		try {
			// Menor con esperanza = 1, edad = 0. Envejecerá a 1 y morirá.
			Menor menorAlBorde = new Menor(1);
			estado.getMenores().add(menorAlBorde);

			// Adulto con esperanza = 20, edad = 19. Envejecerá a 20 y morirá.
			Adulto adultoAlBorde = new Adulto(19, 20);
			estado.getParados().add(adultoAlBorde);

			// Anciano con esperanza = 70, edad = 69. Envejecerá a 70 y morirá.
			Ser ancianoAlBorde = new Ser(69, 70, TipoPago.anciano.getNecesidadVital());
			estado.getAncianos().add(ancianoAlBorde);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		estado.setCapital(10000.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// Los 3 deben haber muerto y haber sido enterrados (sectores vacíos)
		assertEquals(0, estado.getMenores().size());
		assertEquals(0, estado.getParados().size());
		assertEquals(0, estado.getAncianos().size());
		
		// Conteo de defunciones del periodo anterior
		assertEquals(3, estado.defuncionesPeridoAnterior);
	}

	/**
	 * Finalmente enterrar.
	 * 2º) Si un ser muere como adulto, es decir, muere antes de jubilarse y tiene ahorros, 
	 * estos no pasan al estado, porque se hace cuando se jubilan. Debes probar que eso se subsana en enterrar.
	 */
	@Test
	void testEnterrarAdultoMuertoCedeAhorrosAlEstado() {
		estado.getMenores().clear();
		estado.getAncianos().clear();
		estado.getParados().clear();
		estado.getTrabajadores().clear();

		double ahorrosAdultoMuerto = 850.0;
		double capitalInicial = 5000.0;
		estado.setCapital(capitalInicial);

		try {
			// Adulto con edad 29, esperanza 30 (muere inmediatamente al envejecer a 30)
			Adulto adultoAlBorde = new Adulto(29, 30);
			adultoAlBorde.ahorros = ahorrosAdultoMuerto;
			estado.getParados().add(adultoAlBorde);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		// El adulto debe estar muerto y haber sido eliminado
		assertEquals(0, estado.getParados().size());

		// Capital should have received the adult's savings in enterrar
		// capital = capitalInicial (5000) + ahorros (850) = 5850.0
		assertEquals(capitalInicial + ahorrosAdultoMuerto - TipoPago.parado.getNecesidadVital(), estado.getCapital(), 0.01);
	}

	/* =========================================================================
	   2. PRUEBAS PARA abrirPeriodo
	   ========================================================================= */

	/**
	 * Lo primero es probar que se calcula la cantidad a producir, en el periodo que empieza, es correcta.
	 */
	@Test
	void testCalcularCantidadAProducirCorrecta() {
		// Fijamos totalDemandado a 2000
		estado.totalDemandado = 2000.0;

		// Ejecutamos el SUT: abrirPeriodo con un incremento del 10%
		try {
			estado.abrirPeriodo(0.10);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Verificamos que la demanda aumenta un 10% (2000 * 1.10 = 2200)
		assertEquals(2200.0, estado.totalDemandado, 0.01);
	}

	/**
	 * Lo siguiente es probar que la colección “historicoIncrementosDemanda” almacena correctamente los incrementos.
	 */
	@Test
	void testHistoricoIncrementosDemanda() {
		ArrayDeque<Double> historico = estado.historicoIncrementosDemanda;
		historico.clear();

		try {
			estado.abrirPeriodo(0.05);
			estado.abrirPeriodo(0.10);
			estado.abrirPeriodo(-0.02);
			estado.abrirPeriodo(0.08);
			estado.abrirPeriodo(0.01);
			estado.abrirPeriodo(-0.04); // 6ª llamada
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Verificamos que el tamaño está limitado a 5
		assertEquals(5, historico.size());

		// Comprobamos los valores almacenados (debe contener los 5 últimos)
		Double[] expected = {0.10, -0.02, 0.08, 0.01, -0.04};
		int idx = 0;
		for (Double val : historico) {
			assertEquals(expected[idx++], val, 0.001);
		}
	}

	/**
	 * gestionarEmpleos:
	 * 0º) Debes comprobar que cuando un adulto pasa de parado a trabajador, su propiedad “periodosEnEstado” 
	 * se reinicia a 0. Y Viceversa.
	 */
	@Test
	void testGestionarEmpleosReinicioPeriodosEnEstado() {
		// Poblamos los parados con un adulto concreto
		estado.getParados().clear();
		estado.getTrabajadores().clear();

		Adulto adultoParaContratar = null;
		try {
			adultoParaContratar = new Adulto(20, 50);
			adultoParaContratar.setPeriodosEnEstado(5); // inicialmente lleva 5 periodos en estado de Parado
			estado.getParados().add(adultoParaContratar);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Fijamos valores de producción para forzar la contratación de 1 trabajador
		// 0 trabajadores inicialmente, producción por trabajador = 100
		// demanda = 0, incrementando para requerir 1 trabajador
		estado.cantidadProducidaPorTrabajador = 100.0;
		estado.totalDemandado = 0.0;

		try {
			// abrirPeriodo calcula la producción requerida:
			// demanda *= 1 + incremento. Nos aseguramos de que requiera 1 trabajador.
			// Invocamos la apertura del periodo para activar gestionarEmpleos
			estado.totalDemandado = 100.0 / 1.10;
			estado.abrirPeriodo(0.10); // objetivo = 100
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Verificamos que el trabajador es contratado
		assertEquals(0, estado.getParados().size());
		assertEquals(1, estado.getTrabajadores().size());

		// Verificamos que periodosEnEstado se reinicia a 0
		Adulto contratado = estado.getTrabajadores().iterator().next();
		assertEquals(0, contratado.getPeriodosEnEstado());
	}

	/**
	 * gestionarEmpleos:
	 * 1º) En el caso de que el incremento sea positivo. Normalmente esto me obliga a contratar. 
	 * Debes comprobar que se contrata a parados en la cantidad suficiente para atender la demanda.
	 */
	@Test
	void testGestionarEmpleosIncrementoPositivoContratar() {
		estado.getTrabajadores().clear();
		estado.getParados().clear();

		// Añadimos 5 parados
		estado.getParados().addAll(seresManager.getAdultos(5));

		estado.cantidadProducidaPorTrabajador = 100.0;
		estado.totalDemandado = 200.0 / 1.50; // pasará a ser 200 tras un incremento del 50%

		try {
			// Esto calculará la producción objetivo = 200, necesitando 2 trabajadores
			estado.abrirPeriodo(0.50);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// 2 parados deberían ser contratados como trabajadores
		assertEquals(2, estado.getTrabajadores().size());
		assertEquals(3, estado.getParados().size());
	}

	/**
	 * gestionarEmpleos:
	 * 2º) Debes establecer qué pasa si no hay parados suficientes. En principio deberías tener una 
	 * producción inferior a la requerida pero mayor o igual a la que tenías en el periodo anterior.
	 */
	@Test
	void testGestionarEmpleosIncrementoPositivoSinParadosSuficientes() {
		estado.getTrabajadores().clear();
		estado.getParados().clear();

		// Añadimos 1 trabajador (que ya produce 100) y 1 parado disponible
		try {
			estado.getTrabajadores().add(new Adulto(22, 50));
			estado.getParados().add(new Adulto(22, 50));
		} catch (Exception e) {
			fail(e.getMessage());
		}

		estado.cantidadProducidaPorTrabajador = 100.0;
		estado.totalDemandado = 160.0; // producción anterior = 100 (1 trabajador)

		try {
			// La demanda aumenta un 87.5% -> totalDemandado = 300, requiriendo 3 trabajadores (debe contratar 2)
			// ¡Pero solo tenemos 1 parado disponible!
			estado.abrirPeriodo(0.875);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Todos los parados deberían ser contratados
		assertEquals(2, estado.getTrabajadores().size());
		assertEquals(0, estado.getParados().size());

		// La producción es 2 * 100 = 200.
		// La producción requerida era 300. La anterior era 100.
		// La producción es inferior a la requerida (200 < 300) pero mayor o igual a la anterior (200 >= 100)
		double produccionReal = estado.getTrabajadores().size() * 100.0;
		assertTrue(produccionReal < 300.0);
		assertTrue(produccionReal >= 100.0);
	}

	/**
	 * gestionarEmpleos:
	 * 3º) Si el incremento es negativo te toca despedir. En este caso debes comprobar que con ello 
	 * consigues una producción adecuada a lo que te solicitan.
	 */
	@Test
	void testGestionarEmpleosIncrementoNegativoDespedir() {
		estado.getTrabajadores().clear();
		estado.getParados().clear();

		// Añadimos 5 trabajadores (produciendo 500)
		estado.getTrabajadores().addAll(seresManager.getAdultos(5));

		estado.cantidadProducidaPorTrabajador = 100.0;
		estado.totalDemandado = 500.0;

		try {
			// La demanda disminuye un 40% -> demanda objetivo = 300, requiriendo 3 trabajadores (debe despedir 2)
			estado.abrirPeriodo(-0.40);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// 2 trabajadores deberían ser despedidos (trasladados a parados)
		assertEquals(3, estado.getTrabajadores().size());
		assertEquals(2, estado.getParados().size());

		// La producción coincide con la demanda requerida (300)
		double produccionReal = estado.getTrabajadores().size() * 100.0;
		assertEquals(300.0, produccionReal, 0.01);
	}

	/**
	 * gestionarNacimientos:
	 * 1º) Comprueba que las defunciones del periodo anterior son correctas. Para ello debes conocer 
	 * la población anterior al cierre de un periodo, con seres al borde de su esperanza de vida, cumple que: 
	 * La suma de la población antes es igual a la suma después de cerrar mas el conteo de defunciones.
	 */
	@Test
	void testGestionarNacimientosConteoDefunciones() {
		estado.getMenores().clear();
		estado.getParados().clear();
		estado.getTrabajadores().clear();
		estado.getAncianos().clear();

		try {
			// Creamos 3 seres normales y 2 seres al borde de la muerte
			estado.getParados().add(new Adulto(25, 60)); // sobrevive
			estado.getParados().add(new Adulto(25, 60)); // sobrevive
			estado.getAncianos().add(new Ser(70, 80, TipoPago.anciano.getNecesidadVital())); // sobrevive
			
			estado.getMenores().add(new Menor(1)); // muere (edad 0 -> 1 >= 1)
			estado.getAncianos().add(new Ser(79, 80, TipoPago.anciano.getNecesidadVital())); // muere (edad 79 -> 80 >= 80)
		} catch (Exception e) {
			fail(e.getMessage());
		}

		int poblacionAntes = estado.getMenores().size() + estado.getParados().size() 
				+ estado.getTrabajadores().size() + estado.getAncianos().size();
		assertEquals(5, poblacionAntes);

		estado.setCapital(10000.0);

		// Ejecutamos el SUT
		estado.cerrarPeriodo();

		int poblacionDespues = estado.getMenores().size() + estado.getParados().size() 
				+ estado.getTrabajadores().size() + estado.getAncianos().size();

		// La suma de la población antes es igual a la suma después de cerrar más el recuento de defunciones
		assertEquals(poblacionAntes, poblacionDespues + estado.defuncionesPeridoAnterior);
		assertEquals(2, estado.defuncionesPeridoAnterior);
		assertEquals(3, poblacionDespues);
	}

	/**
	 * gestionarNacimientos:
	 * 2º) Los nacimientos en cada periodo se basan en las defunciones del periodo anterior, pero tienen 
	 * un factor corrector: los incrementos de demanda. Para poder controlarlos tenemos una colección 
	 * que recoge las últimos cinco periodos. Existe un método que calcula la media. Dicha media es 
	 * el factor de corrección y aplicada a los nacimientos nos da la cantidad de los mismos que el estado va a crear. 
	 * Comprueba que el índice que corrige la cantidad de nacimientos es correcto.
	 */
	@Test
	void testGestionarNacimientosFactorCorrector() {
		// Fijamos los incrementos históricos de demanda (media = 0.20)
		ArrayDeque<Double> historico = estado.historicoIncrementosDemanda;
		historico.clear();
		historico.offer(0.10);
		historico.offer(0.30);
		historico.offer(0.20); // la media de estos es 0.20
		
		// Fijamos las defunciones del periodo anterior en 10
		estado.defuncionesPeridoAnterior = 10;

		// Limpiamos los menores para aislar los nuevos nacimientos
		estado.getMenores().clear();

		// Desencadenamos abrirPeriodo (que calculará media = (0.10+0.30+0.20 + nuevo_incremento)/4 )
		// Invocamos abrirPeriodo con un incremento de 0.20, de modo que el nuevo valor del histórico sea 0.20
		// El nuevo histórico es [0.10, 0.30, 0.20, 0.20], suma = 0.80, tamaño = 4, media = 0.20.
		// Nacimientos esperados = (int) (defuncionesPeriodoAnterior * (1 - media))
		// = (int) (10 * (1 - 0.20)) = 8.
		try {
			estado.abrirPeriodo(0.20);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Verificamos que se crearon 8 nacimientos
		assertEquals(8, estado.getMenores().size());
	}
}
