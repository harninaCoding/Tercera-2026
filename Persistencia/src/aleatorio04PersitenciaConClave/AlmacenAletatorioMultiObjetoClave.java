package aleatorio04PersitenciaConClave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import aleatorio04Inicial.ConversorHashMap;
import binarios03AlmacenSolucion.AlmacenBinarioMonoObjeto;

public class AlmacenAletatorioMultiObjetoClave<T> implements IAlmacenMultiObjeto<T> {
	private String pathIndex, pathData;
	private Conversor<T> conversor;
	private HashMap<Integer, Long> indice;
	private AlmacenBinarioMonoObjeto<HashMap<Integer, Long>> almacen;

	public AlmacenAletatorioMultiObjetoClave(String path, Conversor<T> conversor) {
		super();
		this.pathIndex = path + ".index";
		this.pathData = path + ".data";
		this.conversor = conversor;
		// problema: si lo hacemos asi no tendriamos persistencia del indice
		 almacen
			=new AlmacenBinarioMonoObjeto<HashMap<Integer,Long>>(pathIndex, new ConversorHashMap());
		indice = almacen.leer();
		//Esto pasa si no existe el indice en persistencia
		if(indice==null) {
			indice=new HashMap<Integer, Long>();
			//persistir
			almacen.grabar(indice);
		}
	}

	@Override
	public void grabar(T t) {
		File file = new File(pathData);
		try (RandomAccessFile flujoW = new RandomAccessFile(file, "rw")) {
			// lo que hago aqui es agregar al final
			long length = flujoW.length();
			flujoW.seek(length);
			conversor.serializar(flujoW, t);
			//cambia el indice. si un cmbio en la variable no se persiste estamos en un estado inconsistente
			//que nos obliga a persistir
			indice.put(indice.size(), length);
			almacen.grabar(indice);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public T leer(int posicion) {
		File file = new File(pathData);
		T t = null;
		Long desplazamientoDesdeElInicioDelFichero = indice.get(posicion);
		try (RandomAccessFile flujoR = new RandomAccessFile(file, "r")) {
			flujoR.seek(desplazamientoDesdeElInicioDelFichero);
			t = conversor.deserializar(flujoR);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public void borrar(int posicion) {
		// TODO Auto-generated method stub

	}

	@Override
	public T actualizar(int posicion, T t) {
		// TODO Auto-generated method stub
		return null;
	}

}
