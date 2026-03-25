package model;

public class DroideProtocolo extends Droide {
    private int idiomasConocidos;
    public DroideProtocolo(String nombre, int nivelDano, int idiomasConocidos) {
        super(nombre, nivelDano, 25);
        this.idiomasConocidos = idiomasConocidos;
    }

    @Override
    public String toString() {
        return super.toString() + " | Idiomas: " + idiomasConocidos;
    }
}
