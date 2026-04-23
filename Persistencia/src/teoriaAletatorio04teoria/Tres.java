import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Tres {
//moverse dentro de un random file
	public static void main(String[] args) {
		File archivo=new File("C:\\JuegoTronos.txt");
		RandomAccessFile flujo=null;
		if(archivo.exists()){
			//Enlazamos el flujo
			try {
				flujo=new RandomAccessFile(archivo, "r");
				//Contiene información que describe las característicos 
				//o atributos de un archivo.
				FileDescriptor identificador=flujo.getFD();
				System.out.println(identificador.toString());
				//obtener posicion actual
				long guia=flujo.getFilePointer();
				System.out.println("posicion actual "+guia);
				//obtener tamaño del archivo
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
					
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			System.out.println("archivo no existe");
		}
	}
}
