package binarios03AlmacenMultiObjetoSolucion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

//Vamos a hacer una clases para gestionar objetos (uno) grabado en un fichero
public class AlmacenBinarioMultiObjeto<T> implements IAlmacenMultiObjeto<T>{
	private String path;
	private Conversor<T> conversor;
	
	public AlmacenBinarioMultiObjeto(String path, Conversor<T> conversor) {
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
	public T leer(int posicion) {
		File archivo = new File(path);
		T instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			DataInputStream conversorR = new DataInputStream(flujoR);
			//El primer objeto
			int contador=0;
			while((instancia=conversor.deserializar(conversorR))!=null&&posicion>contador++) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instancia;
	}
	@Override
	public void borrar(int posicion) {
		// TODO Auto-generated method stub
		//Lo intentamos al modo OldStyle (pasar a un nuevo fichero todos los objetos
		//del antiguo fichero menos el que queremos borrar)
	}
	@Override
	public T actualizar(int posicion, T t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
