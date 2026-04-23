import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class Dos {
	//En este segundo vamos a leer
	public static void main(String[] args) {
		File archivo = new File("C:\\JuegoTronos.txt");
		RandomAccessFile flujo = null;
		if (archivo.exists()) {
			// Enlazamos el flujo
			try {
				flujo = new RandomAccessFile(archivo, "r");
				//Estos son los diferentes tipos de lectura que hay
//				flujo.read()
//				flujo.read(byte[])
//				flujo.read(byte[], off, len)
//				flujo.readBoolean()
//				flujo.readChar()
//				flujo.readDouble()
//				flujo.readFloat()
//				flujo.readFully(Byte[])
//				flujo.readInt()
//				flujo.readLine()
//				flujo.readLong()
//				flujo.readShort()
//				flujo.readUnsignedByte()
//				flujo.readUnsignedShort()
//				flujo.readUTF()
				//Una interesante forma de leer UTF8
				byte[] cosas=new byte[50];
				flujo.read(cosas);
				String cadena=new String(cosas,Charset.forName("UTF8"));
				for (int i = 0; i < 20; i++) {
					System.out.println(cadena);
					flujo.readFully(cosas);
					cadena=new String(cosas,Charset.forName("UTF8"));
				}
				System.out.println("mirar");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("archivo no existe");
		}
	}
}
