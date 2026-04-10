package modelo;

public enum TipoPagos {
	menor(1),trabajador(2),parado(1),anciano(.5);
	private static final double necesidadVitalBase=100; 
	private double coeficiente;
	
	private TipoPagos(double coeficiente) {
		this.coeficiente = coeficiente;
	}

	public double getCantidad() {
		return necesidadVitalBase*coeficiente;
	}
}
