package aleatorio04Inicial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import binarios03AlmacenSolucion.Conversor;

public class ConversorHashMap implements Conversor<HashMap<Integer, Long>> {

	@Override
	public void serializar(DataOutputStream conversor, HashMap<Integer, Long> t) {
		Set<Entry<Integer, Long>> entrySet = t.entrySet();
		try {
			//primero grabamos el tamano del map
			conversor.writeInt(t.size());
			for (Entry<Integer, Long> entry : entrySet) {
				conversor.writeInt(entry.getKey());
				conversor.writeLong(entry.getValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public HashMap<Integer, Long> deserializar(DataInputStream conversor) {
		HashMap<Integer, Long> indice=new HashMap<>();
		try {
			int size = conversor.readInt();
			for (int i = 0; i < size; i++) {
				Integer clave = conversor.readInt();
				long valor = conversor.readLong();
				indice.put(clave, valor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return indice;
	}

}
