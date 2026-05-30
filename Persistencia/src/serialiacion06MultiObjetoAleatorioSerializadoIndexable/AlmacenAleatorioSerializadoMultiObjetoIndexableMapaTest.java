package serialiacion06MultiObjetoAleatorioSerializadoIndexable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

class AlmacenAleatorioSerializadoMultiObjetoIndexableMapaTest
{

	@Test
	void test() {
		AlmacenAleatorioSerializadoMultiObjetoIndexableMapa<Persona,String> almacenPersonas
		=new AlmacenAleatorioSerializadoMultiObjetoIndexableMapa<Persona,String>("personas");
		Persona uno=new Persona("a", (byte)1);
		almacenPersonas.grabar(uno);
		Persona dos=new Persona("b", (byte)2); 
		almacenPersonas.grabar(dos);
		Persona tres=new Persona("c", (byte)3); 
		almacenPersonas.grabar(tres);
		Persona cuatro=new Persona("d", (byte)4); 
		almacenPersonas.grabar(cuatro);
		Persona leer = almacenPersonas.leer(tres.getKey());
		assertEquals(tres, leer);
		leer = almacenPersonas.leer(uno.getKey());
		assertEquals(uno, leer);
		leer = almacenPersonas.leer("g");
		assertNull(leer);
		System.out.println(almacenPersonas.leer(cuatro.getKey()));
	}

}
