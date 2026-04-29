package aleatorio04PersitenciaConClave;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ConversorHashMap<K> implements ConversorConClave<K,HashMap<K,Integer>> {

	@Override
	public void serializar(DataOutputStream conversor, HashMap<K, Integer> t) {
		Set<Entry<K, Integer>> entrySet = t.entrySet();
		try {
			//primero grabamos el tamano del map
			conversor.writeInt(t.size());
			for (Entry<K, Integer> entry : entrySet) {
				writeKey(conversor, entry.getKey());
				conversor.writeInt(entry.getValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public HashMap<K, Integer> deserializar(DataInputStream conversor) {
		HashMap<K, Integer> indice=new HashMap<>();
		try {
			int size = conversor.readInt();
			for (int i = 0; i < size; i++) {
				Integer valor = conversor.readInt();
				indice.put(readKey(conversor), valor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return indice;
	}

}
