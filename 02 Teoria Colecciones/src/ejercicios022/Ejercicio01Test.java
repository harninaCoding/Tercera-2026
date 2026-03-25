package ejercicios022;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Ejercicio01Test {
	
	@Test
	void testRotar() {
//		List<Integer> lista=List.of(5,7,9,12,23,24,56);
		//El problema es que List.of es una coleccion que no puede cambiar
		//o inmutable. Para convertirla en mutable
		List<Integer> lista=new ArrayList<Integer>(List.of(5,7,9,12,23,24,56));
		Ejercicio011 instancia=new Ejercicio011();
		int ultimoAntesDeRotar=lista.getLast();
		int penultimoAntesDeRotar=lista.get(lista.size()-2);
		instancia.rotar(lista);
		assertEquals(ultimoAntesDeRotar, lista.getFirst());
		Integer last=lista.getLast();
		assertEquals(penultimoAntesDeRotar,last );
		
	}

	@Test
	void testSumaList() {
		//Calcular la suma y la media aritmética de 
		//los valores contenidos en un ArrayList.
		List<Integer> lista=List.of(5,7,9,12,23,24,56);
		int resultado=136;
		Ejercicio011 instancia=new Ejercicio011();
		assertEquals(resultado, instancia.sumaList(lista));
		assertEquals(resultado, instancia.sumaListIterator(lista));
		float media=19.42f;
		//sumaria un margen de error 19.42+-0.01= 19.41--19.43
		float delta=.01f;
		assertEquals(media, instancia.calcularMedia(lista),delta);
	}

}
