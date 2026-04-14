package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import static modelo.TipoPago.*;

public class Estado {
	// atributos sobre desarrollo
	private double capital;
	private double cantidadProducidaPorTrabajador;
	private final double edadJubilacion = 65;
	private final double edadMadurez = 18;
	private final double necesidadVitalBase=100;

	// poblacion
	private Sector<Menor> menores;
	private Sector<Adulto> trabajadores;
	private Sector<Adulto> parados;
	private Sector<Ser> ancianos;

	public Estado() {
		super();
		menores = new Sector<Menor>(this.necesidadVitalBase,1,.45);
		trabajadores = new Sector<Adulto>(this.necesidadVitalBase,2,1);
		ancianos = new Sector<Ser>(this.necesidadVitalBase/2,1,.3);
		// Sobreescritura de un metodo par aun objeto especial
		parados = new Sector<Adulto>(this.necesidadVitalBase,1,1) {
			@Override
			public double pago(double deficit) {
				for (Ser miembro : getMiembros()) {
					double necesidad = miembro.getNecesidad();
					capital -= necesidad;
					miembro.alimentar(necesidad);
				}
			}
		};
	}

	////////////////////////////////////////////////////
	/**
	 * 1º se calcula cuanto ha producido el conjunto de los trabajadores 2º se paga
	 * a todos los seres 3º se envejece a todos los seres 4º se jubila a los adultos
	 * que han llegado a la edad de jubilación y se les quita los ahorros 5º se
	 * eliminan los seres que han muerto y se les quita los ahorros (si son adultos)
	 */
	public void cerrarPeriodo() {
		// 1 Calcular el capital
		double totalProducido = trabajadores.size() * cantidadProducidaPorTrabajador;
		this.capital += totalProducido;
		// 2 pagar a los seres
		pagar(menores, ancianos,trabajadores, parados);
		

		// Tendria que preguntarme si puedo pagarlo
		ArrayList<Ser> poblacion = new ArrayList<Ser>();
		poblacion.addAll(menores);
		poblacion.addAll(trabajadores);
		poblacion.addAll(parados);
		poblacion.addAll(ancianos);
		envejecer(poblacion);
		jubila(parados, trabajadores);
		enterrar(menores, parados, trabajadores, ancianos);
	}

	private void pagar(Sector<? extends Ser>...sector) {
		double presupuestoMaximo = calcularPresupuesto();
		double deficit = capital - presupuestoMaximo;
		for (Sector<? extends Ser> poblacion : sector) {
			double pagoReal =poblacion.pago(deficit);
			capital -= pagoReal;
			deficit += presupuestoMaximo - pagoReal;
		}
		capital += deficit;
	}
	private boolean hayDeficit(double presupuesto) {
		return capital < presupuesto;
	}

	
	private double obtenerDeficit(double presupuesto) {
		return capital - presupuesto;
	}

	private double calcularPresupuesto() {
		double presupuestoMenores = menores.size() * menor.getCantidad();
		double presupuestoAncianos = ancianos.size() * anciano.getCantidad();
		double prespuestoParados = parados.size() * parado.getCantidad();
		double presupuestoTrabajadores = trabajadores.size() * trabajador.getCantidad();
		return prespuestoParados + presupuestoAncianos + presupuestoMenores + presupuestoTrabajadores;
	}

	// Pendiente para el lunes 13 abril robar a los muertos
	private void enterrar(ArrayList<? extends Ser>... listas) {
		for (ArrayList<? extends Ser> poblacion : listas) {
			Iterator<? extends Ser> iterator = poblacion.iterator();
			while (iterator.hasNext()) {
				Ser ser = iterator.next();
				if (!ser.isVivo()) {
					iterator.remove();
				}
			}
		}
	}

	private void jubila(ArrayList<Adulto>... listas) {
		for (ArrayList<Adulto> lista : listas) {
			Iterator<Adulto> iterator = lista.iterator();
			while (iterator.hasNext()) {
				// sustituye al for
//			for (Adulto adulto : lista) {
				Adulto adulto = iterator.next();
				if (isAnciano(adulto)) {
					this.capital += adulto.getAhorros();
					iterator.remove();
					ancianos.add(new Ser(adulto));
				}
			}
		}
	}

	private boolean isAnciano(Adulto adulto) {
		return adulto.getEdadActual() >= edadJubilacion;
	}

	private void envejecer(ArrayList<? extends Ser> lista) {
		for (Ser ser : lista) {
			ser.envejecer();
		}
	}

	public void abrirPeriodo(double porcentajeIncrementoProduccion) {

	}

	public ArrayList<Menor> getMenores() {
		return menores;
	}

	public ArrayList<Adulto> getTrabajadores() {
		return trabajadores;
	}

	public ArrayList<Adulto> getParados() {
		return parados;
	}

	public ArrayList<Ser> getAncianos() {
		return ancianos;
	}

}
