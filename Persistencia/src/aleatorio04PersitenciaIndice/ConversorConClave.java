package aleatorio04PersitenciaIndice;

import java.io.DataInput;
import java.io.DataOutput;

import binarios03AlmacenSolucion.Conversor;

public interface ConversorConClave<K, T> extends Conversor<T> {
	public K readKey(DataInput conversor);
	public void writeKey(DataOutput conversor,K k);
}
