package map07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {
	public static void main(String[] args) {
		// Dependiendo de si lo necesitamos ordenado o no
//	HashMap<K, V>
//	treeMap 
		//Un mapa siempre es una dupla clave, valor
		String cadenas[]= {"hola","adios"};
		HashMap<Integer,String> mapaHashMap=new HashMap<Integer, String>();
		List<String> asList = Arrays.asList(cadenas);
		//Para insertar elmentos se usa put()
		for (int i = 0; i < asList.size(); i++) {
			mapaHashMap.put(i, asList.get(i));
		}
		//Es como crear un indice
		System.out.println(mapaHashMap);
		//ahora podemos acceder a los elementos a traves de esa clave
		System.out.println(mapaHashMap.get(1));
	}
}
