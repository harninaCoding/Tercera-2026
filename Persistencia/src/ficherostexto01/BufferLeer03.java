package ficherostexto01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferLeer03 {
public static void main(String[] args) {
	File archivo = new File("c:/JuegoTronos.txt");
	//es una herramienta de buffer para un flujo
	BufferedReader br = null;
	if (archivo.exists()) {
		try {
			FileReader in = new FileReader(archivo);
			br=new BufferedReader(in);
			for (int i = 0; i < 1000; i++) {
				String line = br.readLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
