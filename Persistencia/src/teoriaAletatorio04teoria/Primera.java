import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class Primera {
	public static void main(String[] args) {
		RandomAccessFile flujo = null;
		try {
			flujo = new RandomAccessFile("C:\\JuegoTronos.txt", "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {flujo.read();
			flujo.read();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (int contador = 0; contador < 280; contador++)
			try {
				Character nuevoc=flujo.readChar();
				byte letra[]=new byte[1];
				letra[0]=(byte) flujo.readUnsignedByte();
				String a=flujo.readUTF();
				String nuevo=new String(letra, Charset.forName("UTF-8"));
				System.out.printf("%s",nuevo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			flujo.seek(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int contador = 0; contador < 280; contador++)
			try {
				System.out.printf("%c",flujo.readChar());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
