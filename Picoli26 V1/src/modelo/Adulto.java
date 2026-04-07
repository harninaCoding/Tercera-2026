package modelo;

public class Adulto extends Ser {
    private double ahorros;
    // Constructor para transición desde etapa Menor
    public Adulto(Menor menor, boolean empleado, double necesidadVital) {
        super(menor.getEdadActual(), menor.getEsperanzaVida(), necesidadVital);
        this.ahorros = 0;
    }

    // Constructor general / directo
    public Adulto(int edadActual, double esperanzaVida, double necesidadVital, double ahorrosIniciales,
            boolean empleado) {
        super(edadActual, esperanzaVida, necesidadVital);
        this.ahorros = ahorrosIniciales;
    }

    @Override
    public double alimentar(double cantidadEntregada) {
        double consumido = 0;
            if (cantidadEntregada >= necesidadVital) {
                consumido = necesidadVital;
                ahorros += (cantidadEntregada - necesidadVital);
            } else {
                consumido = cantidadEntregada;
                double deAhorros = necesidadVital-consumido;
                if(ahorros>=deAhorros) {
                	consumido=necesidadVital;
                	ahorros-=deAhorros;
                }else {
                	consumido+=ahorros;
                	ahorros=0;
                	if (consumido < necesidadVital) {
                		double falta = necesidadVital - consumido;
                		double deEstado = Math.min(cantidadEntregada, falta);
                		consumido += deEstado;
                		// Asumimos que si sobra estado (no debería), el adulto no ahorra, según
                		// enunciado "guarda el resto" es al trabajar con sueldo.
                	}
                }
        }

        // Decrecimiento proporcional de vida si no se cumplió con la necesidad vital
        if (consumido < necesidadVital) {
            double proporcionAfectada = 1.0 - (consumido / necesidadVital);
            esperanzaVida -= proporcionAfectada; // maximo de 1 periodo reducido por vez
        }
        return consumido;
    }

    public double entregarAhorros() {
        double ahorrosEntregados = ahorros;
        this.ahorros = 0;
        return ahorrosEntregados;
    }

    public double getAhorros() {
        return ahorros;
    }
}
