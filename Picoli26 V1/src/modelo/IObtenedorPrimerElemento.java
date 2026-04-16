package modelo;

import java.util.AbstractCollection;

public interface IObtenedorPrimerElemento<T> {
	public T getFirst(AbstractCollection<T> miembros);
}
