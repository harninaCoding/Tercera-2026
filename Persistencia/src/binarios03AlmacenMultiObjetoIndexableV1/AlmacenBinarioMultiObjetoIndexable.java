package binarios03AlmacenMultiObjetoIndexableV1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Version sin indice
public class AlmacenBinarioMultiObjetoIndexable<T extends Keyable<K>,K> implements IAlmacenMultiObjetoIndexado<T,K>{
	private String path;
	private Conversor<T> conversor;
	
	public AlmacenBinarioMultiObjetoIndexable(String path, Conversor<T> conversor) {
		super();
		this.path = path;
		this.conversor = conversor;
	}
	
	public void grabar(T t) {
		File archivo = new File(path);
		try(FileOutputStream flujoW= new FileOutputStream(archivo,true)) {
			DataOutputStream adaptador=new DataOutputStream(flujoW);
			conversor.serializar(adaptador,t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public T leer(K clave) {
		File archivo = new File(path);
		T instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			DataInputStream conversorR = new DataInputStream(flujoR);
			//El primer objeto
			int contador=0;
			while((instancia=conversor.deserializar(conversorR))!=null&&instancia.getKey()!=clave) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instancia;
	}
	@Override
	public void borrar(K clave) {
		// TODO Auto-generated method stub
	}
	@Override
	public T actualizar(K clave, T t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
