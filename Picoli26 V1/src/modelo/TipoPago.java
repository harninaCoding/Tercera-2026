package modelo;

public enum TipoPago {
	
	menor(1),parado(1),trabajador(2),anciano(1);
	private double coeficiente;
	private static final double necesidadVitalBase=100;
	private TipoPago(double coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	public double getCantidad() {
		return necesidadVitalBase*coeficiente;
	}
}
