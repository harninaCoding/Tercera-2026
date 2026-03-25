package model;

public class DroideCombate extends Droide {
	//Propiedad especifica del combate, motiva que esto sea una clase
    private String armamento;
    public DroideCombate(String nombre, int nivelDano, String armamento) {
    	//Al crear el objeto el valor de capacidadReparacion ya viene dado (10)
        super(nombre, nivelDano, 10);
        this.armamento = armamento;
    }

    @Override
    public String toString() {
        return super.toString() + " | Arma: " + armamento;
    }
}
