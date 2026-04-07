package listaPrioritaria03;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Prioridad {
	public static void main(String[] args) {
		//Crear un elemento de comparacion que es valido en este 
		//ambito
		Comparator<Personal> comparator=new Comparator<Personal>() {
			
			@Override
			public int compare(Personal o1, Personal o2) {
				// mecqanismo de comparacion como el comparable
//				return o2.priority - o1.priority;
				return o1.priority - o2.priority;
//				return o2.nombre.compareTo(o1.nombre);
			}
		};
		//Se puede hacer que para cada cola se organize de forma diferente
		//metiendo, o no, un comparador concreto en cada cola
		PriorityQueue<Personal> pro = new PriorityQueue<Personal>(comparator);
//		PriorityQueue<Personal> pro = new PriorityQueue<Personal>();
		//Tras la primera se meten como un a pila?
		//es que las entrega en cualquier orden si tienen la 
		//misma prioridad
//		(se usa en colas offer)
		pro.offer(new Personal("b", 1));
		pro.offer(new Personal("aa", 1));
		pro.offer(new Personal("zb", 1));
		pro.offer(new Personal("c", 1));
		pro.offer(new Personal("ac", 2));
//		pro.add(new Personal("b", 1));
//		pro.add(new Personal("c", 1));
//		pro.add(new Personal("ab", 1));
//		pro.add(new Personal("aa", 1));
//		pro.add(new Personal("ac", 1));
		System.out.println(pro);
		while (!pro.isEmpty()) {
			//asi se dice en colas
			System.out.print(pro.poll() + " ");
		}
	}
}
