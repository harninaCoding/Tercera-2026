package colecciones01;

import java.util.ArrayList;
import java.util.LinkedList;

public class MetodosLinkedList03 {
public static void main(String[] args) {
	LinkedList lista=new LinkedList();
	ArrayList listaArray=new ArrayList<>();
	listaArray.add(12);
	listaArray.add(22);
	listaArray.add(32);
	listaArray.add(42);
	listaArray.add(25);
	
	lista.add(1);
	lista.add(2);
	lista.add(3);
	lista.add(4);
	lista.add(2, 10);
	System.out.println(lista);
	System.out.println(lista.get(0));
	System.out.println(listaArray.get(0));
	//cosas que solo son de linked
	lista.addFirst(7);
	lista.addLast(9);
	lista.getFirst();
	lista.getLast();
	lista.peek();
	lista.poll();
	lista.pop();
	//Si yo quiero hacer una pila. LIFO (Last In First Out)
	lista.push(9);
	lista.peek();
	lista.pop();
	//una estructura de cola. FIFO (First In First Out)
	lista.offer(8);
	lista.poll();
	//para recorrer desde el final al principio
	lista.descendingIterator();
}
}
