package clone04;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class ClonarUnoTest {

	@Test
	void test() {
		ClonarUno unoA=new ClonarUno(4,5,6);
		ClonarUno dosA=null;
		try {
			dosA=unoA.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		unoA.setDos(89);
		System.out.println(unoA);
		System.out.println(dosA);
		Cuenta cuenta=new Cuenta(1, "Oro");
		Socio uno=new Socio("1", "Luis", "alli", cuenta);
		Socio dos = null;
		try {
			dos = uno.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(uno.equals(dos));
		System.out.println("no tienen ninguna propiedad diferente");
		System.out.println("no tienen ninguna propiedad diferente");
		System.out.println(uno.toString());
		System.out.println(dos.toString());
		System.out.println("diferente direccion");
		dos.setDireccion("ningun sitio");
		System.out.println(uno.toString());
		System.out.println(dos.toString());
		assertFalse(uno.equals(dos));
		dos.getCuenta().setTipo("Plata");
		System.out.println("diferente tipo de cuenta");
		System.out.println(uno.toString());
		System.out.println(dos.toString());
	}

}
