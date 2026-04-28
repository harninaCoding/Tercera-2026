package aleatorio04PersitenciaIndice;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ConversorPersona implements Conversor<Persona> {

	@Override
	public void serializar(DataOutput conversor, Persona t) {
		try {
			conversor.writeUTF(t.getNombre());
			conversor.writeByte(t.getEdad());
			conversor.writeFloat(t.getDioptrias());
			conversor.writeBoolean(t.isEnfermo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Persona deserializar(DataInput conversor) {
		Persona persona=null;
		try {
			persona= new Persona(conversor.readUTF(), conversor.readByte(), conversor.readFloat(),
					conversor.readBoolean());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return persona;
	}

}
