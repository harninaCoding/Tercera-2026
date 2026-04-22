package binarios03AlmacenProblema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import binarios03AlmacenSolucion.Almacen;
import binarios03AlmacenSolucion.Persona;

class AlmacenTest {

	@Test
	void test() {
		Persona nose=new Persona("f", (byte)0);
		//esto es una clase anonima
		AlmacenOld<Persona> almacenPersonas=new AlmacenOld<Persona>() {
			
			@Override
			public Persona leer() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void grabar(Persona t) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
