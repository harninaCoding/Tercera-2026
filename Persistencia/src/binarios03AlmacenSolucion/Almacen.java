package binarios03AlmacenSolucion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

//Vamos a hacer una clases para gestionar objetos (uno) grabado en un fichero
public class Almacen<T> {
	private String path;
	private Conversor<T> conversor;
	
	public Almacen(String path, Conversor<T> conversor) {
		super();
		this.path = path;
		this.conversor = conversor;
	}
	public T leer() {
		File archivo = new File(path);
		T instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			DataInputStream conversorR = new DataInputStream(flujoR);
			instancia =conversor.deserializar(conversorR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instancia;
	};
	public void grabar(T t) {
		File archivo = new File(path);
		try(FileOutputStream flujoW= new FileOutputStream(archivo)) {
			DataOutputStream adaptador=new DataOutputStream(flujoW);
			conversor.serializar(adaptador,t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
