package ficherostexto01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerTexto00 {
	public static void main(String[] args) {
		File archivo = new File("prueba.txt");
		// texto para conectar el file con el flujo
		FileReader flujoR = null;
		if (archivo.exists()) {
			// asociar el file al flujo
			try {
				flujoR = new FileReader(archivo);
				// si llegamos aqui es que no ha habido error al crear el flujo
				char[] array = new char[2];
				System.out.println("he leido " + flujoR.read(array));
				System.out.println(array);
				System.out.println("segunda lectura");
				array = new char[2];
				System.out.println("he leido " + flujoR.read(array));
				System.out.println(array);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (flujoR != null)
					try {
						flujoR.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
}
