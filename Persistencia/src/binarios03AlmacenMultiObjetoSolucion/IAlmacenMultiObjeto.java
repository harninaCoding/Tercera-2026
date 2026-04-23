package binarios03AlmacenMultiObjetoSolucion;

import binarios03AlmacenProblema.IGrabable;

public interface IAlmacenMultiObjeto<T> extends IGrabable<T> {
	public T leer(int posicion);
	public void borrar(int posicion);
	public T actualizar(int posicion,T t);
}
