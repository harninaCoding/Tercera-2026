package binarios02Iniciales;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirBinarioConversor03 {
public static void main(String[] args) {
	Persona persona = new Persona("luis", (byte) 56, .5f, true);
	File archivo = new File("persona.data");
	try(FileOutputStream flujoW= new FileOutputStream(archivo)) {
		//PAra poder convertir una persona en un tren de bytes
		DataOutputStream conversor=new DataOutputStream(flujoW);
		conversor.writeUTF(persona.getNombre());
		conversor.writeByte(persona.getEdad());
		conversor.writeFloat(persona.getDioptrias());
		conversor.writeBoolean(persona.isEnfermo());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
