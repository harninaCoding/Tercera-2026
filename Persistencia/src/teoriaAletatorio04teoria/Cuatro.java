import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Cuatro {
	public static void main(String[] args) {
		File archivo=new File("JuegoTronos.txt");
		RandomAccessFile flujo=null;
		String cadena="el enano muere";
		if(archivo.exists()){
			//Enlazamos el flujo para escritura
			try {
				flujo=new RandomAccessFile(archivo, "rw");
				//Si el puntero está en la posicion 0 escribe ahí
//				flujo.writeUTF(cadena);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//si coloco el puntero casi en el final sobre escribe alli
//				long pos=flujo.length()-cadena.length();
				//si lo coloco al final añade al fichero
				long pos=flujo.length();
				flujo.seek(pos);
				//Prefiero esta
//				flujo.writeChar('c');
				//Esta escribe intercalando espacios en blanco
				//flujo.writeChars(cadena);
				//esta es la mejor para escribir caracteres
				flujo.writeBytes(cadena);
				flujo.seek(pos);
				System.out.println("lo que he escrito "+flujo.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("archivo no existe");
		}
	}
}
