package modelo;

public abstract class Ser {
    protected int edadActual;
    protected double esperanzaVida;
    protected double necesidadVital;
    protected boolean vivo;

    public Ser(double esperanzaVida, double necesidadVital) {
        this.edadActual = 0;
        this.esperanzaVida = esperanzaVida;
        this.necesidadVital = necesidadVital;
        this.vivo = true;
    }

    public Ser(int edadActual, double esperanzaVida, double necesidadVital) {
        this.edadActual = edadActual;
        this.esperanzaVida = esperanzaVida;
        this.necesidadVital = necesidadVital;
        this.vivo = true;
    }

    public void envejecer() {
        if (!vivo)
            return;
        edadActual++;
        if (edadActual > esperanzaVida) {
            vivo = false;
        }
    }

    public abstract void alimentar(double cantidadEntregada);

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
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
