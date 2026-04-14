package modelo;

public class Presupuesto {
	
	private double presupuestoMenores;
	private double presupuestoTrabajadores;
	private double presupuestoParados;
	private double presupuestoAncianos;
	
	public Presupuesto(int cantidadMenores,int cantidadParados,int cantidadTrabajadores,int cantidadAncianos) {
		super();
		 presupuestoMenores=cantidadMenores*TipoPago.menor.getCantidad();
		 presupuestoTrabajadores=cantidadTrabajadores*TipoPago.trabajador.getCantidad();
		 presupuestoParados=cantidadParados*TipoPago.parado.getCantidad();
		 presupuestoAncianos=cantidadTrabajadores*TipoPago.anciano.getCantidad();
	}
	public double calcularPresupuesto() {
		return presupuestoAncianos+presupuestoMenores+presupuestoParados+presupuestoTrabajadores;
	}
	public double getPresupuestoMenores() {
		return presupuestoMenores;
	}
	public double getPresupuestoTrabajadores() {
		return presupuestoTrabajadores;
	}
	public double getPresupuestoParados() {
		return presupuestoParados;
	}
	public double getPresupuestoAncianos() {
		return presupuestoAncianos;
	}
	
	
	
}
