package colleciones01;

import java.util.ArrayList;
import java.util.Collections;

import colecciones01.PersonaDos;

public class ComparandoClases05 {

	public static void main(String[] args) {
		ArrayList<PersonaDos> listado = new ArrayList<PersonaDos>();
		listado.add(new PersonaDos("Luis", 12));
		listado.add(new PersonaDos("Esteban", 18));
		listado.add(new PersonaDos("Arturo", 14));
		listado.add(new PersonaDos("Roberto", 19));
		PersonaDos uno=new PersonaDos("ziburcio", 27);
		PersonaDos dos=new PersonaDos("adelmiro", 12);
		PersonaDos person = new PersonaDos("Benito", 12);
		System.out.println("comparando tiburcio con edelmiro "+uno.compareTo(dos));
		System.out.println(listado);
		Collections.sort(listado);
		listado.add(person);
		System.out.println(listado);
		Collections.sort(listado);
		System.out.println(listado);
	}

}
