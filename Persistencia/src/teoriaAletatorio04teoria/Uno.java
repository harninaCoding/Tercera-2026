import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Uno {
	// Un fichero de acceso aleatorio no se usa como los otros
	// No usa un buffer, no tiene sentido
	public static void main(String[] args) {
		File archivo=new File("C:\\JuegoTronos.txt");
		RandomAccessFile flujo=null;
		if(archivo.exists()){
			//Enlazamos el flujo
			try {
				flujo=new RandomAccessFile(archivo, "r");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			System.out.println("archivo no existe");
		}
	}
}
