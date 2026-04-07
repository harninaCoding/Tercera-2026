package modelo;

public class Anciano extends Ser {
    // Constructor general / directo
    public Anciano(int edadActual, double esperanzaVida, double necesidadVitalDeAdulto) {
        // La necesidad pasa a ser la mitad al transferirse a Anciano
        super(edadActual, esperanzaVida, necesidadVitalDeAdulto / 2.0);
    }

    // Constructor transición desde etapa Adulto
    public Anciano(Adulto adulto) {
        super(adulto.getEdadActual(), adulto.getEsperanzaVida(), adulto.getNecesidadVital() / 2.0);
    }

    @Override
    public double alimentar(double cantidadEntregada) {
        double proporcion = cantidadEntregada / necesidadVital;
        if (proporcion < 1.0) {
            if (proporcion >= 0.30) {
                // Entre 99% y 30%, pierde hasta un periodo de forma proporcional a su déficit
                // en ese tramo
                esperanzaVida -= (1.0 - proporcion);
            } else {
                // Del 29% al 0%, pierde hasta 2 periodos vitales extra si baja de ese umbral
                double proporcionEnTramo = 1.0 - (proporcion / 0.29);
                esperanzaVida -= (1.0 + proporcionEnTramo);
            }
        }
        return cantidadEntregada;
    }
}
