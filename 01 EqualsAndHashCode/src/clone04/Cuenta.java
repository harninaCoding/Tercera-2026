package clone04;

import java.util.Objects;

public class Cuenta implements Cloneable {
	private int numero;
	private String tipo;
	public Cuenta(int numero, String tipo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
	}
	
	@Override
	public  Cuenta clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Cuenta) super.clone();
	}
	@Override
	public int hashCode() {
		return Objects.hash(numero, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return numero == other.numero && Objects.equals(tipo, other.tipo);
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Cuenta [numero=" + numero + ", tipo=" + tipo + "]";
	}
	
}
