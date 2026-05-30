package serialiacion06MultiObjetoAleatorioSerializadoIndexable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.HashMap;

public class AlmacenAleatorioSerializadoMultiObjetoIndexableMapa<T extends Keyable<K>&Serializable,K> implements IAlmacenMultiObjetoIndexado<T,K>{
	private String pathDatos;
	private String pathIndice;
	private HashMap<K, Long> indice;
	private AlmacenBinarioMonoObjetoSerializado<HashMap<K, Long>> almacenIndice;
	private File fileData;
	
	public AlmacenAleatorioSerializadoMultiObjetoIndexableMapa(String path ) {
		super();
		this.pathDatos = path+".data";
		this.pathIndice=path+".index";
		almacenIndice=
				new AlmacenBinarioMonoObjetoSerializado<HashMap<K, Long>>(pathIndice);
		//leer el hasmap del fichero
		try {
			indice=almacenIndice.leer();
		} catch (FileNotFoundException e) {
			//creo un hashmap vacio
			indice=new HashMap<K, Long>();
			//persistir para evitar inconsistencia entre lo que hay en RAM y lo que hay en archivo
			almacenIndice.grabar(indice);
		}
		fileData=new File(pathDatos);
	}
	
	public void grabar(T t) {
		try (RandomAccessFile flujoW = new RandomAccessFile(fileData, "rw")) {
			long length = flujoW.length();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(t);
	        byte[] datos = baos.toByteArray();
	        //ya tienes el objeto serializado
	        //vas al final del archivo
	        flujoW.seek(length);
	        flujoW.writeInt(datos.length);
	        flujoW.write(datos);
			indice.put(t.getKey(), length);
			almacenIndice.grabar(indice);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public T leer(K clave) {
		T t = null;
		Long desplazamientoDesdeElInicioDelFichero = indice.get(clave);
		if(desplazamientoDesdeElInicioDelFichero==null) return null;
		try (RandomAccessFile flujoR = new RandomAccessFile(fileData, "r")) {			
			flujoR.seek(desplazamientoDesdeElInicioDelFichero);
			//cuanto ocupa el objeto
			int sizeObjecto = flujoR.readInt();
			byte[] buffer = new byte[sizeObjecto];
			//leemos todos los bytes
			flujoR.readFully(buffer);
			//deserializamos
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        
	        t=(T) ois.readObject();
			//ya no necesitamos conversor
//			t = conversor.deserializar(flujoR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
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
