package binarios03AlmacenProblema;

public interface AlmacenOld<T> extends IGrabable<T> {
	public T leer();
	public void borrar();
	public T actualizar(T t);
}
