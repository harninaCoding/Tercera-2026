package binarios03AlmacenMultiObjetoIndexableMapaV2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

//Version sin indice
public class AlmacenBinarioMultiObjetoIndexableMapa<T extends Keyable<K>,K> implements IAlmacenMultiObjetoIndexado<T,K>{
	private Conversor<T> conversor;
	private String pathDatos;
	private String pathIndice;
	private HashMap<K, Integer> indice;
	private AlmacenBinarioMonoObjeto<HashMap<K, Integer>> almacenIndice;
	
	public AlmacenBinarioMultiObjetoIndexableMapa(String path, Conversor<T> conversor,ConversorHashMap<K> conversorIndice) {
		super();
		this.pathDatos = path+".data";
		this.pathIndice=path+".index";
		this.conversor = conversor;
		almacenIndice=
				new AlmacenBinarioMonoObjeto<HashMap<K, Integer>>(pathIndice, conversorIndice);
		//leer el hasmap del fichero
		indice=almacenIndice.leer();
		//si falla, entonces no tengo hashmap
		if(indice==null) {
			//creo un hashmap vacio
			indice=new HashMap<K, Integer>();
			//persistir para evitar inconsistencia entre lo que hay en RAM y lo que hay en archivo
			almacenIndice.grabar(indice);
		}
	}
	
	public void grabar(T t) {
		File archivo = new File(this.pathDatos);
		try(FileOutputStream flujoW= new FileOutputStream(archivo,true)) {
			DataOutputStream adaptador=new DataOutputStream(flujoW);
			conversor.serializar(adaptador,t);
			indice.put(t.getKey(), indice.size());
			almacenIndice.grabar(indice);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public T leer(K clave) {
		File archivo = new File(this.pathDatos);
		T instancia = null;
		try (FileInputStream flujoR = new FileInputStream(archivo)) {
			DataInputStream conversorR = new DataInputStream(flujoR);
			//El primer objeto
			int contador=0;
			int posicion=indice.get(clave);
			while((instancia=conversor.deserializar(conversorR))!=null&&posicion<contador) {
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
