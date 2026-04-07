package modelo;

public class Adulto extends Ser {
    private double ahorros;
    private boolean empleado;

    // Constructor para transición desde etapa Menor
    public Adulto(Menor menor, boolean empleado, double necesidadVital) {
        super(menor.getEdadActual(), menor.getEsperanzaVida(), necesidadVital);
        this.ahorros = 0;
        this.empleado = empleado;
    }

    // Constructor general / directo
    public Adulto(int edadActual, double esperanzaVida, double necesidadVital, double ahorrosIniciales,
            boolean empleado) {
        super(edadActual, esperanzaVida, necesidadVital);
        this.ahorros = ahorrosIniciales;
        this.empleado = empleado;
    }

    @Override
    public void alimentar(double cantidadEntregada) {
        if (!vivo)
            return;

        double consumido = 0;

        if (empleado) {
            // Se le paga 2*necesidadVital si es posible (enviado en cantidadEntregada)
            if (cantidadEntregada >= necesidadVital) {
                consumido = necesidadVital;
                ahorros += (cantidadEntregada - necesidadVital);
            } else {
                consumido = cantidadEntregada;
            }
        } else {
            // Paro: usamos ahorros primero
            double deAhorros = Math.min(ahorros, necesidadVital);
            ahorros -= deAhorros;
            consumido = deAhorros;

            // Faltante cubierto por la cantidadEntregada (el estado)
            if (consumido < necesidadVital) {
                double falta = necesidadVital - consumido;
                double deEstado = Math.min(cantidadEntregada, falta);
                consumido += deEstado;
                // Asumimos que si sobra estado (no debería), el adulto no ahorra, según
                // enunciado "guarda el resto" es al trabajar con sueldo.
            }
        }

        // Decrecimiento proporcional de vida si no se cumplió con la necesidad vital
        if (consumido < necesidadVital) {
            double proporcionAfectada = 1.0 - (consumido / necesidadVital);
            esperanzaVida -= proporcionAfectada; // maximo de 1 periodo reducido por vez
        }
    }

    public double entregarAhorros() {
        double ahorrosEntregados = ahorros;
        this.ahorros = 0;
        return ahorrosEntregados;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public double getAhorros() {
        return ahorros;
    }
}
