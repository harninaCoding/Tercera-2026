package equalsInnerBien03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SocioTest {

	@Test
	void test() {
		Cuenta cuenta=new Cuenta(1, "Oro");
		Cuenta cuentaDos=new Cuenta(1, "Oro");
		assertTrue(cuenta.equals(cuentaDos));
		Socio uno=new Socio("1", "Luis", "alli", cuenta);
		Socio dos=new Socio("1", "Luis", "alli", cuentaDos);
		assertTrue(uno.equals(dos));
	}

}
