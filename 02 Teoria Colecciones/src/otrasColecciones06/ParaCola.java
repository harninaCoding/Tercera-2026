package otrasColecciones06;

import java.util.ArrayDeque;
import java.util.Collections;

public class ParaCola {
	public static void main(String[] args) {
		// una implementacion ya hecha de una cola
		// obviamente una cola es como en la vida real
		// una estructura que cumple la condicion FIFO
		// First In First Out
		ArrayDeque<String> cola = new ArrayDeque<String>();
		// Que dos cosas podemos usar?
		String palabrejas[] = { "columnas", "filas", "zalamero", "zascandil" };
		llenarcola(cola, palabrejas);
		while (!cola.isEmpty()) {
//			//hacer avanzar la cola
//			//desencolar
			System.out.println(cola.poll());
			//es como poll pero no borra de la cola
			System.out.println(cola.peek());
			System.out.println(cola);
		}
		//Una peculiaridad de la cola es que pueden colar elementos
		llenarcola(cola, palabrejas);

		cola.offerFirst("colador");
		System.out.println(cola);
		//tambien puedo hacer polling 
		System.out.println("ultimo "+cola.pollLast());
		System.out.println(cola);
		//La cola es un tipo especial de lista y se base en cautro metodos
		//offer(solo o con los sufijos First o Last) para añadir al frente
		//poll (solo o con los sufijos First o Last) para extraer del frente
		//peek (solo o con los sufijos First o Last) para leer del frente sin extraer
		
	}

	private static void llenarcola(ArrayDeque<String> cola, String[] palabrejas) {
		for (int i = 0; i < palabrejas.length; i++) {
			// offer coloca el elemento al final de la cola
			// encolar
			cola.offer(palabrejas[i]);
		}
	}
}
