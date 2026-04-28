package aleatorio04PersitenciaIndice;

import java.io.DataInput;
import java.io.DataOutput;

public interface Conversor<T> {
	public void serializar(DataOutput conversor,T t);
	public T deserializar(DataInput conversor);
}
