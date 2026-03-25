package ejercicios022;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Ejercicio011 {
	
	public void rotar(List<Integer> lista) {
//		lista.addFirst(lista.getLast());
//		lista.addFirst(lista.removeLast());
		//Pero siempre hay que intentar buscar si ya esta hecho
		Collections.rotate(lista, 1);
	}
	
	public float calcularMedia(List<Integer> lista) {
		return (float)sumaList(lista)/lista.size();
	}

	public int sumaList(List<Integer> lista) {
		// Recorrer para sumar
		int acumulador = 0;
		for (int i = 0; i < lista.size(); i++) {
			acumulador += lista.get(i);
		}
		return acumulador;
	}

	public int sumaListIterator(List<Integer> lista) {
		// Recorrer para sumar
		int acumulador = 0;
		Iterator<Integer> iterator = lista.iterator();
		while (iterator.hasNext()) {
			acumulador += iterator.next();
		}
		return acumulador;
	}

	public int sumaListForEach(List<Integer> lista) {
		// Recorrer para sumar
		int acumulador = 0;
		for (Integer integer : lista) {
			acumulador += integer;
		}
		return acumulador;
	}

}
