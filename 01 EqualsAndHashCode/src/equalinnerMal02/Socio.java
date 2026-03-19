package equalinnerMal02;

import java.util.Objects;

public class Socio {
	private String numero;
	private String nombre;
	private String direccion;
	private Cuenta cuenta;

	public Socio(String numero, String nombre, String direccion, Cuenta cuenta) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.direccion = direccion;
		this.cuenta = cuenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuenta, direccion, nombre, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(cuenta, other.cuenta) 
				&& Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre) 
				&& Objects.equals(numero, other.numero);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
