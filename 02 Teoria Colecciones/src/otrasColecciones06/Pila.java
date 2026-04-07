package otrasColecciones06;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Pila {
	public static void main(String[] args) {
		/******************USAR LINKED LIST COMO PILA**********************
		 * 
		 */
		// La pila es un tipo de lista que cumple la condicion LIFO
		// Last in First Out
		String palabrejas[] = { "columnas", "filas", "zalamero", "zascandil" };
		LinkedList<String> linkedPila=new LinkedList<String>(Arrays.asList(palabrejas));
		linkedPila.push("na");
		linkedPila.pop();
		linkedPila.peek();
		//////////////////////////////////////////////
		Stack<String> palabrasPila = new Stack<String>();
		// Aunque tiene varios métodos suelen usarse los de ingreso o extraccion
		// a la pila
		// para poner en la pila
		// push coloca un elemento en la cima de la pila
		palabrasPila.push(palabrejas[0]);
		palabrasPila.push(palabrejas[1]);
		System.out.println(palabrasPila);
		// como se obtienen los elementos seegun el criterio de una pila
		// dos formas
		// 1º obteniendo el elemento y borrandolo de la pila
		// con pop se retorna el elemento en la cima y se borra de la pila
		System.out.println("cima " + palabrasPila.pop());
		System.out.println("cima " + palabrasPila.pop());
		System.out.println(palabrasPila);
		try {
			System.out.println("cima " + palabrasPila.pop());
		} catch (Exception e) {
			System.out.println("pila vacia");
		}
		// Como hemos dicho se puede trabajar con Stack como si fuese una colecction
		for (int i = 0; i < palabrejas.length; i++) {
			palabrasPila.push(palabrejas[i]);
		}
		// 2º metodo de acceso a Stack como pila
		// Se trata del metodo peek que es como el pop pero
		// no elimina el elemento de la cima
		for (int i = 0; i < 5; i++) {
			System.out.println("cima " + palabrasPila.peek());
		}
		// Si usas una Stack es porque lo necesitas. java te permite usarla como
		// una collection normal, pero en realidad solo se deberian usar las
		// cuatro funciones asociadas a a pila
		// pop,push,peek,isEmpty
		System.out.println(palabrasPila.size());
	}
}
