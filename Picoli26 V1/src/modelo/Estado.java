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
		// Antes de refactorizar
		double presupuesto = calcularPresupuesto();
		double deficit = obtenerDeficit(presupuesto);
		double pagoSector = menor.getCantidad();
		if (deficit < 0) {
			double presupuestoMenores = menores.size() * menor.getCantidad();
			double presupuestoCorregidoMaximo = presupuestoMenores * .45;
			double presupuestoMenoresReal = presupuestoMenores + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoMenoresReal);
			deficit += presupuestoMenores - min;
			pagoSector = min / menores.size();
		}
		for (Menor menor : menores) {
			capital -= pagoSector;
			menor.alimentar(pagoSector);
		}
		pagoSector = anciano.getCantidad();
		if (deficit < 0) {
			double presupuestoAncianos = ancianos.size() * anciano.getCantidad();
			double presupuestoCorregidoMaximo = presupuestoAncianos * .3;
			double presupuestoAncianosReal = presupuestoAncianos + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoAncianosReal);
			deficit += presupuestoAncianos - min;
			pagoSector = min / ancianos.size();
		}
		for (Ser anciano : ancianos) {
			capital -= pagoSector;
			anciano.alimentar(pagoSector);
		}
		pagoSector = trabajador.getCantidad();
		if (deficit < 0) {
			double presupuestoTrabajadores = trabajadores.size() * trabajador.getCantidad();
			double presupuestoCorregidoMaximo = presupuestoTrabajadores * .5;
			double presupuestoTrabajadoresReal = presupuestoTrabajadores + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoTrabajadoresReal);
			deficit += presupuestoTrabajadores - min;
			pagoSector = min / trabajadores.size();
		}
		for (Adulto trabajador : trabajadores) {
			capital -= pagoSector;
			trabajador.alimentar(pagoSector);
		}
		for (Adulto parado : parados) {
			double necesidad = parado.getNecesidad();
			capital -= necesidad;
			parado.alimentar(necesidad);
		}

		// Tendria que preguntarme si puedo pagarlo
		ArrayList<Ser> poblacion = new ArrayList<Ser>();
		poblacion.addAll(menores);
		poblacion.addAll(trabajadores);
		poblacion.addAll(parados);
		poblacion.addAll(ancianos);
		envejecer(poblacion);
		jubila(parados, trabajadores);
		enterrar(menores, parados, trabajadores, ancianos);
		capital+=deficit;
	}

	private boolean hayDeficit(double presupuesto) {
		return capital < presupuesto;
	}

	private void pagarSeres(ArrayList<? extends Ser>... sector) {
		Presupuesto presupuesto = new Presupuesto(menores.size(), parados.size(), trabajadores.size(), ancianos.size());
		double deficit = obtenerDeficit(presupuesto.calcularPresupuesto());
		////////////////////////////////// Pago a menores
		double pagoSector = menor.getCantidad();
		double presupuestoMenores = presupuesto.getPresupuestoMenores();
		if (deficit < 0) {
			double presupuestoCorregidoMaximo = presupuestoMenores * .45;
			double presupuestoMenoresReal = presupuestoMenores + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoMenoresReal);
			deficit += presupuestoMenores - min;
			pagoSector = min / menores.size();
		}
		for (Menor menor : menores) {
			capital -= pagoSector;
			menor.alimentar(pagoSector);
		}
		////////////////////////////////// Pago a ancianos
		pagoSector = anciano.getCantidad();
		double presupuestoAncianos = presupuesto.getPresupuestoAncianos();
		if (deficit < 0) {
			double presupuestoCorregidoMaximo = presupuestoAncianos * .3;
			double presupuestoAncianosReal = presupuestoAncianos + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoAncianosReal);
			deficit += presupuestoAncianos - min;
			pagoSector = min / ancianos.size();
		}
		for (Ser anciano : ancianos) {
			capital -= pagoSector;
			anciano.alimentar(pagoSector);
		}
		////////////////////////// Pago a trabajadores
		pagoSector = trabajador.getCantidad();
		double presupuestoTrabajadores = presupuesto.getPresupuestoTrabajadores();
		if (deficit < 0) {
			double presupuestoCorregidoMaximo = presupuestoTrabajadores * .3;
			double presupuestoTrabajadoresReal = presupuestoTrabajadores + deficit;
			double min = Math.max(presupuestoCorregidoMaximo, presupuestoTrabajadoresReal);
			deficit += presupuestoTrabajadores - min;
			pagoSector = min / trabajadores.size();
		}
		for (Adulto trabajador : trabajadores) {
			capital -= pagoSector;
			trabajador.alimentar(pagoSector);
		}
		//////////////////////// Pago a parados
		for (Adulto parado : parados) {
			double necesidad = parado.getNecesidad();
			capital -= necesidad;
			parado.alimentar(necesidad);
		}
		capital += deficit;
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
