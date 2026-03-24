package colecciones01;

import java.util.ArrayList;

public class EjemploBusqueda021 {
public static void main(String[] args) {
	ArrayList<Integer> lista=new ArrayList<Integer>();
	//add
	lista.add(2);
	lista.add(5);
	lista.add(6);
	lista.add(8);
	lista.add(12);
	lista.add(22);
	lista.add(8);
	lista.add(32);
	//para buscar
	int indexOf = lista.indexOf(8);
	System.out.println(indexOf);
	int lastIndexOf = lista.lastIndexOf(8);
	System.out.println(lastIndexOf);
	
//Como se buscan elementos repetidos
//	indexOf contiene el valor de la posicion del primer elemento
	int primerEncuentro = lista.indexOf(8);
	System.out.println(indexOf);
	//buscamos en el resto de la lista
	indexOf= lista.subList(++primerEncuentro, lista.size()).indexOf(8);
	System.out.println(primerEncuentro+indexOf);
	
	// como buscar muchos ?
	for (int i = 0; i < lista.size(); i++) {
		if(lista.get(i)==8) System.out.println("elemento en posicion "+i);
	}
}
}
