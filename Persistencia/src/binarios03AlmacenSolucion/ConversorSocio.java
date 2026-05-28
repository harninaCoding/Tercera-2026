package binarios03AlmacenSolucion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConversorSocio implements Conversor<Socio> {

	@Override
	public void serializar(DataOutputStream conversor, Socio t) {
		try {
			conversor.writeUTF(t.getApodo());
			conversor.writeDouble(t.getSaldo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Socio deserializar(DataInputStream conversor) {
		// TODO Auto-generated method stub
		return null;
	}

}
