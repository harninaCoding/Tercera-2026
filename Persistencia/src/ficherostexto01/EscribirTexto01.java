package ficherostexto01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirTexto01 {
public static void main(String[] args) {
	  File archivo = new File("prueba.txt");
	  FileWriter flujoW = null;
	  try {
		  //sobreescritura
//		flujoW = new FileWriter(archivo);
		  //agregar al final
		flujoW = new FileWriter(archivo,true);
		 for (int i = 0; i < 6; i++) {
	            int valor = (i + 50) * 2;
	            System.out.println(valor);
	            flujoW.write((char)valor); // grabamos como texto
	        }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
        try {
            if (flujoW != null) flujoW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
