package binarios03AlmacenSolucion;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Conversor<T> {
	public void serializar(DataOutputStream conversor,T t);
	public T deserializar(DataInputStream conversor);
}
