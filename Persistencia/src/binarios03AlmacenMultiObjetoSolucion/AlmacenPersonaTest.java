package binarios03AlmacenMultiObjetoSolucion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlmacenPersonaTest {

	private String path = "pruebaPersonaConversor.data";

	@BeforeEach
	void before() {
		new File(path).delete();
	}

	@Test
	void test() {
		Persona persona = new Persona("luis", (byte) 56, .5f, true);
		AlmacenBinarioMultiObjeto<Persona> almacenPersona = new AlmacenBinarioMultiObjeto<Persona>(path, new ConversorPersona());
		almacenPersona.grabar(persona);
		Persona leer=null;
//		= almacenPersona.leer(0);
//		assertEquals(persona, leer);
		persona = new Persona("Arturo Jose", (byte) 45, .15f, false);
		almacenPersona.grabar(persona);
		leer = almacenPersona.leer(1);
		assertEquals(persona, leer);
	}

	@AfterEach
	void tearDown() {
		// es mas para cuando has modificado algo que quieres poner en su estado inicial
		new File(path).delete();
	}
}
