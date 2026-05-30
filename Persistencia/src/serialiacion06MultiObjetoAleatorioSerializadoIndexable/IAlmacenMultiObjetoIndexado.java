package serialiacion06MultiObjetoAleatorioSerializadoIndexable;

public interface IAlmacenMultiObjetoIndexado<T,K> extends IGrabable<T> {
	public T leer(K clave);
	public void borrar(K clave);
	public T actualizar(K clave,T t);
}
