package model;

//Es abstracta porque los droides que generamos no son nunca de este tipo
public abstract class Droide implements Reparable {
	/*
	 * Observa como los atributos comunes se escriben en el padre
	 */
    protected String nombre;
    protected int nivelDano; // 0 a 100
    protected int capacidadReparacion;

    public Droide(String nombre, int nivelDano, int capacidadReparacion) {
        this.nombre = nombre;
        this.nivelDano = nivelDano;
        this.capacidadReparacion = capacidadReparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelDano() {
        return nivelDano;
    }

    public int getCapacidadReparacion() {
        return capacidadReparacion;
    }

    //cada maquina se repara en funcion de su capacidad
    @Override
    public void aplicarReparacion() {
    	 nivelDano = nivelDano - capacidadReparacion;
    }

    @Override
    public String toString() {
        return nombre + " - Daño: " + nivelDano + "%";
    }
}
