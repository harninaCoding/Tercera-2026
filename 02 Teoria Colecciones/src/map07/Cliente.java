package map07;

public class Cliente {
	public String nombre;
	public long numeroCuenta;
	public float saldo;
	
	public Cliente(String nombre, long numeroCuenta, float saldo) {
		super();
		this.nombre = nombre;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}
	@Override
	public boolean equals(Object obj) {
		Cliente nom=(Cliente)obj;
		return nombre.equals(nom.nombre);
	}
	@Override
	public int hashCode() {
		return (int) numeroCuenta;
	}
}
