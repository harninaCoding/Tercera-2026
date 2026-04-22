package binarios03AlmacenProblema;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AlmacenChicharro implements AlmacenOld<Chicharro> {

	private String path;

	public AlmacenChicharro(String path) {
		super();
		this.path = path;
	}

	@Override
	public Chicharro leer() {
		File archivo = new File(path);
		Chicharro instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			DataInputStream conversorR = new DataInputStream(flujoR);
//			instancia = new Chicharro(conversorR.readUTF(), conversorR.readByte(), conversorR.readFloat(),
//					conversorR.readBoolean());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instancia;
	}

	@Override
	public void grabar(Chicharro t) {
		File archivo = new File(path);
		try(FileOutputStream flujoW= new FileOutputStream(archivo)) {
			//PAra poder convertir una persona en un tren de bytes
			DataOutputStream conversor=new DataOutputStream(flujoW);
//			conversor.writeUTF(t.getNombre());
//			conversor.writeByte(t.getEdad());
//			conversor.writeFloat(t.getDioptrias());
//			conversor.writeBoolean(t.isEnfermo());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
