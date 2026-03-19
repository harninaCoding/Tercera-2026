package equals01;

import java.util.Objects;

public class Persona {
	private String numero;
	private String nombre;
	private String direccion;

	public Persona(String numero, String nombre, String direccion) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.direccion = direccion;
	}

/////////contrato equals and hashcode
	@Override
	public int hashCode() {
		//Te dice si dos elemntos son diferentes pero no si son iguales
		//lo hace mucho mas rapido
		return Objects.hash(direccion, nombre, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return 
				Objects.equals(direccion, other.direccion) 
				&& Objects.equals(nombre, other.nombre)
				&& Objects.equals(numero, other.numero);
	}

////////////////////

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
