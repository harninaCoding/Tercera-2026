package ejercicios022;

import java.util.ArrayList;
import java.util.List;

import ejercicios023.Alumno;

public class AlumnoManager {
    private List<Alumno> alumnos;

    // El constructor recibe los datos fijos
    public AlumnoManager(String[] ids, float[] alturas) {
        this.alumnos = new ArrayList<>();
        
        // Usamos un for indexado para recorrer ambos arrays a la vez
        // Suponemos que ambos tienen el mismo tamaño (15)
        int limite = Math.min(ids.length, alturas.length);
        
        for (int i = 0; i < limite; i++) {
            this.alumnos.add(new Alumno(ids[i], alturas[i]));
        }
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}