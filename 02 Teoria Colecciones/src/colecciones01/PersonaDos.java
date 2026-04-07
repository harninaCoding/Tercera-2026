package colecciones01;

public class PersonaDos implements Comparable<PersonaDos> {
	public String nombre;
	public int edad;

	public PersonaDos(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	public String toString() {
		return nombre+" "+edad;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edad;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaDos other = (PersonaDos) obj;
		if (edad != other.edad)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public int compareTo(PersonaDos o) {
		//el resultado es <0 si o es menor que this
		//El peso *1XXXX nos dice la importancia del atributo 
		//a comparar, mayor peso nos da mayor importancia
		//En este caso *100 es mas importante en la comparacion que
		//*10
		int nombres=this.nombre.compareTo(o.nombre)*10;
		int edades=(this.edad-o.edad)*100;
		return edades-nombres;
	}
	
}
