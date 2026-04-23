package aleatorio04Inicial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class AlmacenAletatorioMultiObjeto<T> implements IAlmacenMultiObjeto<T> {
	private String path;
	private Conversor<T> conversor;
	private HashMap<Integer, Long> indice;

	public AlmacenAletatorioMultiObjeto(String path, Conversor<T> conversor) {
		super();
		this.path = path;
		this.conversor = conversor;
		//problema: si lo hacemos asi no tendriamos persistencia del indice
		indice=new HashMap<Integer, Long>();
	}

	@Override
	public void grabar(T t) {
		File file = new File(path);
		try (RandomAccessFile flujoW = new RandomAccessFile(file, "rw")) {
			// lo que hago aqui es agregar al final
			long length = flujoW.length();
			flujoW.seek(length);
			conversor.serializar(flujoW, t);
			indice.put(indice.size(), length);
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
		File file = new File(path);
		T t=null;
		Long desplazamientoDesdeElInicioDelFichero = indice.get(posicion);
		try (RandomAccessFile flujoR = new RandomAccessFile(file, "r")) {
			flujoR.seek(desplazamientoDesdeElInicioDelFichero);
			t=conversor.deserializar(flujoR);
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
