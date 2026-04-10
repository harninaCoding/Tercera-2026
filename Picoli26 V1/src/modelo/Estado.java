package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import static modelo.TipoPagos.*;

public class Estado {
	// atributos sobre desarrollo
	private double capital;
	private double cantidadProducidaPorTrabajador;
	private final double edadJubilacion = 65;
	private final double edadMadurez = 18;

	// poblacion
	private ArrayList<Menor> menores;
	private ArrayList<Adulto> trabajadores;
	private ArrayList<Adulto> parados;
	private ArrayList<Ser> ancianos;

	public Estado() {
		super();
		menores = new ArrayList<Menor>();
		trabajadores = new ArrayList<Adulto>();
		parados = new ArrayList<Adulto>();
		ancianos = new ArrayList<Ser>();
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
		// pago ideal (sin restricciones)
		double presupuesto=calcularPresuesto();
		if(hayDeficit(presupuesto)) {
			//una forma de pago especial
			//no hay cama pa tanta gente
			//presuesto 8000 capital 7500
			//ej deficit=500
			double deficit=presupuesto-capital;
			// hay que calcular cuanto deberia cobrar cada menor
			// para no tener deficit
			//cantidad 1500
			double presupuestoMenores=menores.size()*menor.getCantidad();
			// cantidad recortada 675 
			double presupuestoCorregidoMaximo=presupuestoMenores*.45;
			//diferencia 1500-675=825
			//lo que debo pagar 1500-500=1000
			//la cantidad que se paga a cada menor es 1000/menores.size()
			
		}else {
			//una forma de pago sin restricciones
			for (Menor menor : menores) {
				capital -= menor.getNecesidadVital();
				menor.alimentar(TipoPagos.menor.getCantidad());
			}
			
		}
		// Tendria que preguntarme si puedo pagarlo
		
		ArrayList<Ser> poblacion = new ArrayList<Ser>();
		poblacion.addAll(menores);
		poblacion.addAll(trabajadores);
		poblacion.addAll(parados);
		poblacion.addAll(ancianos);
		envejecer(poblacion);
		jubila(parados, trabajadores);
		enterrar(menores,parados,trabajadores,ancianos);
	}

	private boolean hayDeficit(double presupuesto) {
		return capital<presupuesto;
	}

	private double calcularPresuesto() {
		double presupuestoMenores = menores.size() * menor.getCantidad();
		double presupuestoAncianos=ancianos.size()* anciano.getCantidad();
		double prespuestoParados=parados.size()*parado.getCantidad();
		double presupuestoTrabajadores=trabajadores.size()*trabajador.getCantidad();
		return prespuestoParados+presupuestoAncianos+presupuestoMenores+presupuestoTrabajadores;
	}

	//Pendiente para el lunes 13 abril robar a los muertos
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

	private void envejecerDos(ArrayList<? extends Ser>... listas) {
		for (ArrayList<? extends Ser> lista : listas) {
			for (Ser ser : lista) {
				ser.envejecer();
			}
		}

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
