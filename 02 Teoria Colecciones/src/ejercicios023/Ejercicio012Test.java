package ejercicios023;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import ejercicios022.AlumnoManager;

class Ejercicio012Test {
	 String[] misIds = {
		        "Alu01", "Alu02", "Alu03", "Alu04", "Alu05", 
		        "Alu06", "Alu07", "Alu08", "Alu09", "Alu10", 
		        "Alu11", "Alu12", "Alu13", "Alu14", "Alu15"
		    };
	 float[] misAlturas = {
		        1.70f, 1.65f, 1.82f, 1.90f, 1.55f, 
		        1.74f, 1.68f, 1.77f, 1.85f, 1.60f, 
		        1.72f, 1.69f, 1.81f, 1.76f, 1.88f
		    };
	 AlumnoManager alumnoObjetMother = new AlumnoManager(misIds, misAlturas);

	@Test
	void testGetMediaAltura() {
		 float media=1.74f;
		 float delta=0.002f;
		 Ejercicio012 instancia=new Ejercicio012();
		 assertEquals(media,instancia.getMediaAltura(alumnoObjetMother.getAlumnos()),delta);
	}

}
