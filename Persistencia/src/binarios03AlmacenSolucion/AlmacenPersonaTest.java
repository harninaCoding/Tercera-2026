package binarios03AlmacenSolucion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import binarios03AlmacenProblema.AlmacenPersona;


class AlmacenPersonaTest {

	@Test
	void test() {
		Persona persona = new Persona("luis", (byte) 56, .5f, true);
		String path="pruebaPersonaConversor.data";
		Almacen<Persona> almacenPersona=new Almacen<Persona>(path,new ConversorPersona());
		almacenPersona.grabar(persona);
		Persona leer = almacenPersona.leer();
		assertEquals(persona, leer);
		Almacen<Socio> almacenSocios=new Almacen<Socio>(path, new ConversorSocio());
	}

}
