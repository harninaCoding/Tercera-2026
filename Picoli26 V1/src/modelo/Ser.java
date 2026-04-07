package modelo;

public abstract class Ser {
    protected int edadActual;
    protected double esperanzaVida;
    protected double necesidadVital;

    public Ser(double esperanzaVida, double necesidadVital) {
        this.edadActual = 0;
        this.esperanzaVida = esperanzaVida;
        this.necesidadVital = necesidadVital;
    }

    public Ser(int edadActual, double esperanzaVida, double necesidadVital) {
        this.edadActual = edadActual;
        this.esperanzaVida = esperanzaVida;
        this.necesidadVital = necesidadVital;
    }

    public void envejecer() {
        edadActual++;
    }

    public abstract double alimentar(double cantidadEntregada);

    public int getEdadActual() {
        return edadActual;
    }

    public double getEsperanzaVida() {
        return esperanzaVida;
    }

    public double getNecesidadVital() {
        return necesidadVital;
    }

    public boolean isVivo() {
        return edadActual>esperanzaVida;
    }

   }
