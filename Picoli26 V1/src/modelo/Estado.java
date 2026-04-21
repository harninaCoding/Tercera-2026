package modelo;

import static modelo.TipoPago.anciano;
import static modelo.TipoPago.menor;
import static modelo.TipoPago.parado;
import static modelo.TipoPago.trabajador;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Estado {
	// atributos sobre desarrollo
	private double capital = 0;
	private double cantidadProducidaPorTrabajador;
	private final double edadJubilacion = 65;
	private final double edadMadurez = 18;
	private final double necesidadVitalBase = 100;

	// poblacion
	private Sector<Menor> menores;
	private Sector<Adulto> trabajadores;
	private Sector<Adulto> parados;
	private Sector<Ser> ancianos;

	// prduccion
	private double totalDemandado = 0;

	public Estado() {
		super();
		menores = new SectorNoPrioritario<Menor>(menor);
		trabajadores = new SectorPrioritario<Adulto>(trabajador);
		ancianos = new SectorNoPrioritario<Ser>(anciano);
		parados = new SectorPrioritarioParados(parado);
	}

	public void abrirPeriodo(double porcentajeIncrementoDemanda) {
		// 1 calcular la cantidad que debe producir el estado segun el incremento (puede
//		// ser una cantidad menor)
//		double objetivoProduccion = calcularCantidadAProducir(porcentajeIncrementoProduccion);
		totalDemandado *= 1 + porcentajeIncrementoDemanda;
//		// 2 Contratar o despedir a adultos segun sea la necesidad
//		gestionarEmpleos(objetivoProduccion);
//		// 3 decidir los nacimientos en funcion de cuantas defunciones, y otras cosas,
//		// hayan pasado en el periodo anterior
//		gestionarNacimientos();
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
		pagar(menores, ancianos, trabajadores, parados);
		// Tendria que preguntarme si puedo pagarlo
		ArrayList<Ser> poblacion = new ArrayList<Ser>();
		poblacion.addAll(menores.getMiembros());
		poblacion.addAll(trabajadores.getMiembros());
		poblacion.addAll(parados.getMiembros());
		poblacion.addAll(ancianos.getMiembros());
		envejecer(poblacion);
		jubila(parados.getMiembros(), trabajadores.getMiembros());
		enterrar(menores.getMiembros(), parados.getMiembros(), trabajadores.getMiembros(), ancianos.getMiembros());
	}

	private void pagar(Sector<? extends Ser>... sector) {
		double deficit = 0;
		for (Sector<? extends Ser> poblacion : sector) {
			double presupuestoMaximo = poblacion.getTotalPago();
			deficit = capital - presupuestoMaximo;
			double pagoReal = poblacion.pago(deficit);
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
		double presupuestoMenores = menores.size() * menor.getPago();
		double presupuestoAncianos = ancianos.size() * anciano.getPago();
		double prespuestoParados = parados.size() * parado.getPago();
		double presupuestoTrabajadores = trabajadores.size() * trabajador.getPago();
		return prespuestoParados + presupuestoAncianos + presupuestoMenores + presupuestoTrabajadores;
	}

	// Pendiente para el lunes 13 abril robar a los muertos
	private void enterrar(AbstractCollection<? extends Ser>... listas) {
		for (AbstractCollection<? extends Ser> poblacion : listas) {
			Iterator<? extends Ser> iterator = poblacion.iterator();
			while (iterator.hasNext()) {
				Ser ser = iterator.next();
				if (!ser.isVivo()) {
					iterator.remove();
				}
			}
		}
	}

	private void jubila(AbstractCollection<Adulto>... listas) {
		for (AbstractCollection<Adulto> lista : listas) {
			Iterator<Adulto> iterator = lista.iterator();
			while (iterator.hasNext()) {
				// sustituye al for
//			for (Adulto adulto : lista) {
				Adulto adulto = iterator.next();
				if (isAnciano(adulto)) {
					this.capital += adulto.getAhorros();
					iterator.remove();
					ancianos.getMiembros().add(new Ser(adulto));
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

	public AbstractCollection<Menor> getMenores() {
		return menores.getMiembros();
	}

	public AbstractCollection<Adulto> getTrabajadores() {
		return trabajadores.getMiembros();
	}

	public AbstractCollection<Adulto> getParados() {
		return parados.getMiembros();
	}

	public AbstractCollection<Ser> getAncianos() {
		return ancianos.getMiembros();
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}
}
