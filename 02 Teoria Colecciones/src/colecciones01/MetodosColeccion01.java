package colecciones01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MetodosColeccion01 {

	public static void main(String[] args) {
		// lo hago porque ArrayList hereda de collection
		// pero solo puedo acceder a los metodos y propiedades
		// de collection
		ArrayList<Integer> lista = new ArrayList<Integer>();
		LinkedList<Integer> linkedd = new LinkedList<Integer>();
		// Upcasting
		Collection<Integer> miColeccion = lista;

		// Si puedo acceder a este metodo porque es ArrayList
		lista.addFirst(8);
		// no puedo acceder porque es un upcasting a Collection
//		miColeccion.addFirst(9);
		miColeccion.add(10);
///////////////////////////////////////////
		miColeccion = linkedd;
		List<Integer> colec= FactoriaColecciones.factoryMethod();
	}

}
