package binarios03AlmacenMultiObjetoIndexableMapaV2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ConversorHashMapString extends ConversorHashMap<String> {

	@Override
	public String readKey(DataInput conversor) {
		try {
			return conversor.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeKey(DataOutput conversor, String k) {
		try {
			conversor.writeUTF(k);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
