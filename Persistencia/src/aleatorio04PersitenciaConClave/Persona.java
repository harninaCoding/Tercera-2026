package aleatorio04PersitenciaConClave;

import java.util.Objects;

public class Persona {
	private String nombre;
	private byte edad;
	private float dioptrias;
	private boolean enfermo;

	public Persona(String nombre, byte edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.dioptrias = 0f;
		this.enfermo = false;
	}

	public Persona(String nombre, byte edad, boolean enfermo) {
		this(nombre, edad);
		this.enfermo = enfermo;
	}
	

	public Persona(String nombre, byte edad, float dioptrias, boolean enfermo) {
		this(nombre, edad,enfermo);
		this.dioptrias = dioptrias;
	}

	@Override
	public String toString() {
		return nombre+" "+edad+" "+dioptrias+" "+enfermo;
	}
	public byte getEdad() {
		return edad;
	}

	public void setEdad(byte edad) {
		this.edad = edad;
	}

	public float getDioptrias() {
		return dioptrias;
	}

	public void setDioptrias(float dioptrias) {
		this.dioptrias = dioptrias;
	}

	public boolean isEnfermo() {
		return enfermo;
	}

	public void setEnfermo(boolean enfermo) {
		this.enfermo = enfermo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dioptrias, edad, enfermo, nombre);
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
		return Float.floatToIntBits(dioptrias) == Float.floatToIntBits(other.dioptrias) && edad == other.edad
				&& enfermo == other.enfermo && Objects.equals(nombre, other.nombre);
	}

}
