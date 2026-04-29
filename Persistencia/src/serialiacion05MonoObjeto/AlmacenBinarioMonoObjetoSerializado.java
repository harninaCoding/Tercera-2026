package serialiacion05MonoObjeto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Vamos a hacer una clases para gestionar objetos (uno) grabado en un fichero
public class AlmacenBinarioMonoObjetoSerializado<T extends Serializable> {
	private String path;
	File archivo;
	
	public AlmacenBinarioMonoObjetoSerializado(String path) {
		super();
		this.path = path;
		 archivo = new File(path);
	}
	public T leer() {
		T instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			ObjectInputStream lector = new ObjectInputStream(flujoR);
			return (T) lector.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instancia;
	};
	public void grabar(T t) {
		File archivo = new File(path);
		try(FileOutputStream flujoW= new FileOutputStream(archivo)) {
			ObjectOutputStream grabador=new ObjectOutputStream(flujoW);
			grabador.writeObject(t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
