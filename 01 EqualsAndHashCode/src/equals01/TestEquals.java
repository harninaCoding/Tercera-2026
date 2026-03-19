package equals01;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TestEquals {

	@Test
	void test() {
		Persona perUno=new Persona("1", "Jose", "aqui");
		Persona perDos=new Persona("1", "Jose", "aqui");
		assertFalse(perUno==perDos);
		assertFalse(perUno.equals(perDos));
	}

}
