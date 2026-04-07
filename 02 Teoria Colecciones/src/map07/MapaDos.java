package map07;

import java.util.HashMap;

public class MapaDos {
	public static void main(String[] args) {
		// Veamos lo que pasa si los elementos son clases propias
		Cliente cliUno = new Cliente("jose", 1, 100);
		Seguro hogar = new Seguro("hogar", 1, 2);
		Seguro hogar2 = new Seguro("casa", 2, 1);
		Cliente cliDos = new Cliente("jose", 1, 200);
		
		HashMap<Cliente, Seguro> enlace = new HashMap<Cliente, Seguro>();
		enlace.put(cliUno, hogar);
		System.out.println(cliUno.hashCode());
		System.out.println(cliDos.hashCode());
		System.out.println("cliuno equals clidos "+cliUno.equals(cliDos));
		//get retorna el objeto value que corresponde al objeto key
		System.out.println(enlace.get(cliUno).nombre);
		//Esto funciona porque usa equals y hashcode
		System.out.println(enlace.get(cliDos).nombre);
		//Para que un hashmap funcione usa ambos metodos equals y hashcode
		//Pero al igual que en los sets, hash no ordena pero tree si.
		System.out.println(enlace.put(cliDos,hogar2));
		System.out.println(enlace.put(cliDos,hogar));
		System.out.println(enlace.put(cliDos,hogar));
		System.out.println(enlace.get(cliUno).nombre);
		//Esto funciona porque usa equals y hashcode
		System.out.println(enlace.get(cliDos).nombre);

		
	}
}
