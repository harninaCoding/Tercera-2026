import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;
//en esta version implementamos la pagina atras.
//al ser un fichero secuencial todos los movimeintos se hacen 
//de principio a fin. Esto nos lleva a utilizar el metodo skip
//Una posible solucion es almacenar la posicion de cada caracter
//que da comienzo a una página

public class Librero {
	// La clase con los métodos de acceso a fichero
	private File archivo = null;
	private BufferedReader bufer = null;
	private String frase;
	private long posicionActual = 0;
	private char ultimaLetra = 0;
	private Vector<Long> indicePaginas = new Vector<>();
	private int paginaActual = 0;
	private int ultimaPagina = 0;

	public Librero() {
		// TODO Auto-generated constructor stub
		try {
			iniciarFlujo();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public long getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(int posicionActual) {
		this.posicionActual = posicionActual;
	}

	public char getUltimaLetra() {
		return ultimaLetra;
	}

	public void setUltimaLetra(char ultimaLetra) {
		this.ultimaLetra = ultimaLetra;
	}

	public boolean iniciarFlujo() throws UnsupportedEncodingException {
		// Da los valores inciales al flujo
		// controlar si no se inicia el archivo
		archivo = new File("C:\\JuegoTronos.txt");
		if (archivo.exists()) {
			//
			try {
				// bufer=new RandomAccessFile(archivo, "r");

				bufer = new BufferedReader(new InputStreamReader(
						new FileInputStream(archivo), "UTF8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			indicePaginas.add((long) 0);
			return true;
		} else
			return false;
	}

	public void leerComienzoPagina() {
		// Este metodo coloca la primera letra del String que ha
		// sobrado de la pagina anterior
		frase = new String();
		if (paginaActual >= ultimaPagina)
			frase = Character.toString(ultimaLetra);

	}

	public void anadirLetra() {
		// Añade una letra al final de frase
		char letra = 0;
		try {
			letra = (char) bufer.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frase += letra;
		ultimaLetra = letra;
		if (ultimaPagina <= paginaActual)
			posicionActual++;
	}

	public void quitarLetra() {
		// Quita la ultima letra de un String
		if (frase.length() > 0) {
			char temporal[] = new char[frase.length() - 1];
			for (int contador = 0; contador < temporal.length; contador++)
				temporal[contador] = frase.charAt(contador);
			frase = String.valueOf(temporal);
			// esta orden no me deja añadir una nueva página al indice si ya
			// esta creada
			if (indicePaginas.size() <= paginaActual) {
				indicePaginas.add(posicionActual - 1);
			}
			if (ultimaPagina < paginaActual)
				ultimaPagina = paginaActual;

		}
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public void atrasarPagina() {
		long offset = 0;
		if (paginaActual > 0)
			offset = indicePaginas.elementAt(paginaActual);
		try {
			bufer.close();
			bufer = new BufferedReader(new InputStreamReader(
					new FileInputStream(archivo), "UTF8"));
			bufer.skip(offset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
