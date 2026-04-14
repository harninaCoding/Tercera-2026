package modelo;

import java.util.ArrayList;

public class Sector<T extends Ser> {
	

	private ArrayList<T> miembros;
	private final double necesidadVital;
	private final double pago;
	private final double reduccionMaxima;

	public Sector(double necesidadVital, double pago, double reduccionMaxima) {
		super();
		miembros = new ArrayList<>();
		this.necesidadVital = necesidadVital;
		this.pago = pago;
		this.reduccionMaxima = reduccionMaxima;
	}
	
	public double pago(double deficit) {
		double pagoSector = this.pago;
		double pagoTotalPorSector=0;
		if (deficit < 0) {
			double presupuestoSector = miembros.size() * pagoSector;
			double presupuestoCorregidoMaximo = presupuestoSector * reduccionMaxima;
			double presupuestoSectorReal = presupuestoSector + deficit;
			pagoTotalPorSector = Math.max(presupuestoCorregidoMaximo, presupuestoSectorReal);
			deficit += presupuestoSector - pagoTotalPorSector;
			pagoSector = pagoTotalPorSector / miembros.size();
		}
		for (Ser poblador : miembros) {
			poblador.alimentar(pagoSector);
		}
		return pagoTotalPorSector;
	}
	public int size() {
		return miembros.size();
	}

	public ArrayList<T> getMiembros() {
		return miembros;
	}
	
	
}
