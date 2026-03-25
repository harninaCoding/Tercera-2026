package model;

public class Vehiculo implements Reparable {
    private String matricula;
    //Observa como estos atributos se repiten en Droides y vehiculo
    //sin embargo un vehiculo no es un droide, como se puede arreglar?
    //generando una clase Padre de Vehiculo y Droide 
    private int nivelDano;
    private int capacidadReparacion;

    public Vehiculo(String matricula, int nivelDano) {
        this.matricula = matricula;
        this.nivelDano = nivelDano;
        this.capacidadReparacion = 20; // Valor fijo para vehículos
    }

    @Override
    public void aplicarReparacion() {
    	 nivelDano = nivelDano - capacidadReparacion;
    }

    @Override
    public String getNombre() {
        return "Vehículo: " + matricula;
    }

    @Override
    public int getNivelDano() {
        return nivelDano;
    }

    @Override
    public int getCapacidadReparacion() {
        return capacidadReparacion;
    }

    @Override
    public String toString() {
        return getNombre() + " - Daño: " + nivelDano + "%";
    }
}
