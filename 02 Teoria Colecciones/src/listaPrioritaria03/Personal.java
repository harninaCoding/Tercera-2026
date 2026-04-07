package listaPrioritaria03;

public class Personal
//{
implements Comparable<Personal>{
	public String nombre;
	public int priority=0;

	public Personal(String nombre,int priority) {
		super();
		this.nombre = nombre;
		this.priority=priority;
	}

	@Override
	public String toString() {
		return nombre;
	}
//
//	//se puede dejar el compareto para una cosa y usar otra interfaz para la prioridad
	@Override
	public int compareTo(Personal obj) {
		return this.nombre.compareTo(obj.nombre);
	}
}
