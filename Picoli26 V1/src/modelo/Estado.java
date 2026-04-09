package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Estado {
	// atributos sobre desarrollo
	private double capital;
	private double cantidadProducidaPorTrabajador;
	private final double edadJubilacion = 65;
	private final double edadMadurez = 18;
	private final double necesidadVital=100;
	
	// poblacion
	private ArrayList<Menor> menores;
	private ArrayList<Adulto> trabajadores;
	private ArrayList<Adulto> parados;
	private ArrayList<Ser> ancianos;
	
	public Estado() {
		super();
		menores=new ArrayList<Menor>();
		trabajadores=new ArrayList<Adulto>();
		parados=new ArrayList<Adulto>();
		ancianos=new ArrayList<Ser>();
	}

	////////////////////////////////////////////////////
	/**
	 * 1º se calcula cuanto ha producido el conjunto de los trabajadores 
	 * 2º se paga a todos los seres 
	 * 3º se envejece a todos los seres 
	 * 4º se jubila a los adultos que han llegado 
	 * 		a la edad de jubilación y se les quita los ahorros
	 * 5º se eliminan los seres que han muerto
	 * y se les quita los ahorros (si son adultos)
	 */
	public void cerrarPeriodo() {
		//1 Calcular el capital
		double totalProducido=trabajadores.size()*cantidadProducidaPorTrabajador;
		this.capital+=totalProducido;
		//2 pagar a los seres
		//pago ideal (sin restricciones)
		double presupuestoMenores=menores.size()*necesidadVital;
		//Tendria que preguntarme si puedo pagarlo
		for (Menor menor : menores) {
			capital -= menor.getNecesidadVital();
			menor.alimentar(menor.getNecesidadVital());
		}
		ArrayList<Ser> poblacion=new ArrayList<Ser>();
		poblacion.addAll(menores);
		poblacion.addAll(trabajadores);
		poblacion.addAll(parados);
		poblacion.addAll(ancianos);
		envejecer(poblacion);
		// 4 jubilar a adultos
		// debo recorrer la lista de trabajadores y parados
		// comprobar si, tras envejecer, el adulto tiene 65
		// en ese caso quitarle los ahorros (se suman al capital)
		// quitarlo de la lista correspondiente y añadir al nuevo
		// anciano a la lista de los mismos
		jubila(parados,trabajadores);
		//Enterrar a los muertos
		enterrar(poblacion);
	}

	private void enterrar(ArrayList<Ser> poblacion) {
		Iterator<Ser> iterator = poblacion.iterator();
		while(iterator.hasNext()) {
			Ser ser = iterator.next();
			if(!ser.isVivo()) {
				iterator.remove();
			}
		}
	}

	private void jubila(ArrayList<Adulto>... listas) {
		for (ArrayList<Adulto> lista : listas) {
			Iterator<Adulto> iterator = lista.iterator();
			while(iterator.hasNext()) {
				//sustituye al for
//			for (Adulto adulto : lista) {
				Adulto adulto = iterator.next();
				if(isAnciano(adulto)){
					this.capital+=adulto.getAhorros();
					iterator.remove();
					ancianos.add(new Ser(adulto));
				}
			}
		}
	}
	
	private boolean isAnciano(Adulto adulto) {
		return adulto.getEdadActual()>=edadJubilacion;
	}

	private void envejecerDos(ArrayList<? extends Ser>... listas) {
		for (ArrayList<? extends Ser> lista : listas) {
			for (Ser ser :lista) {
				ser.envejecer();
			}
		}
		
	}
	private void envejecer(ArrayList<? extends Ser> lista) {
		for (Ser ser :lista) {
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
