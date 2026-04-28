package binarios03AlmacenMultiObjetoIndexableMapaV2;

import binarios03AlmacenProblema.IGrabable;

public interface IAlmacenMultiObjetoIndexado<T,K> extends IGrabable<T> {
	public T leer(K clave);
	public void borrar(K clave);
	public T actualizar(K clave,T t);
}
