package colecciones01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class MatodosArrayList02 {
	public static void main(String[] args) {
		ArrayList miLista = new ArrayList();
		// anade un elemento a la lista
		miLista.add(4);

		// reemplaza un elemento de la lista
		miLista.set(0, 3);
		System.out.println(miLista);

		try {
			// intentando reemplazar un elemento que no existe
			miLista.set(1, 4);
		} catch (Exception e) {
			System.out.println("posicion no valida");
		}
		System.out.println(miLista.get(0));
		try {
			System.out.println(miLista.get(1));
		} catch (Exception e) {
			System.out.println("posicion no valida");
		}
		miLista.add(9);
		System.out.println(miLista);
		// como conocer la posicion de un elemento
		System.out.println("donde esta el nueve? " + miLista.indexOf(9));
		// otra forma menos especifica
		System.out.println("esta el nueve? " + miLista.contains(9));
		// Hay una peculiaridad de las lista y es que no se pueden modificar
		// mientras se recorren
		// cual es la solucion? Es usar un iterador para recorrer la lista
		// la lista se bloquea al recorrerla y eso es algo que se evita
		// con el iterator
		for (Iterator iterator = miLista.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(object);
		}
		// recorre la propia lista y por lo tanto la bloquea
		for (Object object : miLista) {
			System.out.println(object);
		}
		ListIterator espeIterator=miLista.listIterator();
		for (ListIterator iterator = miLista.listIterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(iterator.previous());
			System.out.println(iterator.next());
			
		}
		//anade al comienzo de la lista (iterator)
		espeIterator.add(18);
		System.out.println(" while y listiterator");
		while(espeIterator.hasNext()) {
			System.out.println(espeIterator.next());
		}
		// Una vez recorrido el iterator ya esta consumido
		// y para volver a recorrerlo debemos reasignarlo
		espeIterator.remove();
		espeIterator=miLista.listIterator();
		System.out.println("dame mas");
		while(espeIterator.hasNext()) {
			System.out.println(espeIterator.next());
		}
		
	}
}
