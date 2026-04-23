package binarios03AlmacenMultiObjetoSolucion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConversorPersona implements Conversor<Persona> {

	@Override
	public void serializar(DataOutputStream conversor, Persona t) {
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
	public Persona deserializar(DataInputStream conversor) {
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
