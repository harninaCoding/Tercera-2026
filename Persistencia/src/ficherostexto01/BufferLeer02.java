package ficherostexto01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferLeer02 {
public static void main(String[] args) {
	File archivo = new File("prueba.txt");
	//es una herramienta de buffer para un flujo
	BufferedReader br = null;
	if (archivo.exists()) {
		try {
			FileReader in = new FileReader(archivo);
			br=new BufferedReader(in);
			String line = br.readLine();
			System.out.println(line);
			line = br.readLine();
			System.out.println(line);
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
