package teoriaAletatorio04teoria;

import java.io.File;
import java.io.FileDescriptor;
import java.io.RandomAccessFile;


public class cinco {
	public static void main(String[] args) {
		File archivo=new File("C:\\JuegoTronos.txt");
		RandomAccessFile flujo=null;
		if(archivo.exists()){
			//Enlazamos el flujo
			try {
				flujo=new RandomAccessFile(archivo, "r");
				//Contiene informaci�n que describe las caracter�sticos 
				//o atributos de un archivo.
				FileDescriptor identificador=flujo.getFD();
				System.out.println(identificador.toString());
				//obtener posicion actual
				long guia=flujo.getFilePointer();
				System.out.println("posicion actual "+guia);
				//obtener tama�o del archivo
				long ultimo=flujo.length();
				System.out.println("posicion final "+ultimo);
				//ir a una posicion
				long pos=1333;
				flujo.seek(pos);
				pos-=333;
				//ir hacia atras
				flujo.seek(pos);
				guia=flujo.getFilePointer();
				System.out.println("posicion actual "+guia);
			}catch(Exception e){
				
			}
		}
	}
}
