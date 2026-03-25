package presenter;

import java.util.ArrayList;
import java.util.List;

import model.Reparable;
import model.ReparacionInforme;

public class Taller {
    private List<Reparable> cola;
    private int bateria;

    public Taller() {
        this.cola = new ArrayList<>();
        this.bateria = 100;
    }

    public void agregarReparable(Reparable r) {
        if (cola.size() >= 3) {
            throw new IllegalStateException("Capacidad máxima alcanzada (3 elementos).");
        }
        cola.add(r);
    }

    public ReparacionInforme procesarSiguiente() {
        if (cola.isEmpty()) {
            return new ReparacionInforme(false, null, "No hay elementos en la cola.", null);
        }

        Reparable actual = cola.get(0);
        int costeEnergia = 15;

        if (bateria < costeEnergia) {
            return new ReparacionInforme(false, null,
                    "¡Error! Estación sin energía suficiente para reparar (" + costeEnergia + " necesario).",
                    null);
        }

        actual.aplicarReparacion();

        bateria -= costeEnergia;
        Reparable reparadoCompletamente = null;

        if (actual.getNivelDano() == 0) {
            reparadoCompletamente = cola.remove(0);
        }

        String msg = "Reparación (" + actual.getCapacidadReparacion() + " pts) completada en " + actual.getNombre();
        return new ReparacionInforme(true, msg, null, reparadoCompletamente);
    }

    public void recargar() {
        bateria = 100;
    }

    public List<Reparable> getCola() {
        return new ArrayList<>(cola);
    }

    public int getBateria() {
        return bateria;
    }
}
