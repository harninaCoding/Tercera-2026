package ejercicios023;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio012 {
	public float getMediaAltura(List<Alumno> alumnos) {
		float acumulador=0;
		for (Alumno alumno : alumnos) {
			acumulador+=alumno.getAltura();
		}
		return acumulador/alumnos.size();
	}
	public float getMediaAlturaFiltrado(List<Alumno> alumnos,float alturaMinima) {
		ArrayList<Alumno> filtro=new ArrayList<Alumno>();
		for (Alumno alumno : alumnos) {
			if(alumno.getAltura()>=alturaMinima) filtro.add(alumno);
		}
		return getMediaAltura(filtro);
	}
}
