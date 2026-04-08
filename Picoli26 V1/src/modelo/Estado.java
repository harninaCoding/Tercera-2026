package modelo;

import java.util.ArrayList;

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
		//...
		//3 envejecer
//		for (Ser ser : menores) {
//			ser.envejecer();
//		}
//		for (Ser ser : ancianos) {
//			ser.envejecer();
//		}
//		for (Ser ser : trabajadores) {
//			ser.envejecer();
//		}
//		for (Ser ser : parados) {
//			ser.envejecer();
//		}
//		envejecer(menores);
//		envejecer(ancianos);
//		envejecer(trabajadores);
//		envejecer(parados);
//		envejecerDos(menores,trabajadores,parados,ancianos);
		////Otra forma de envejecer
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
}
