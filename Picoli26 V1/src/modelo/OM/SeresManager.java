package modelo.OM;

import java.util.ArrayList;
import java.util.List;

import modelo.Adulto;
import modelo.Menor;
import modelo.Ser;

public class SeresManager {

	public List<Menor> getMenores(int cantidad){
		ArrayList<Menor> menores=new ArrayList<>();
		int esperanzaVida=50;
		for (int i = 0; i < cantidad; i++) {
			try {
				menores.add(new Menor(esperanzaVida));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return menores;
	}
	public List<Adulto> getAdultos(int cantidad){
		ArrayList<Adulto> adultos=new ArrayList<>();
		int esperanzaVida=50;
		for (int i = 0; i < cantidad; i++) {
			try {
				Adulto e = new Adulto(18, esperanzaVida);
				adultos.add(e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return adultos;
	}
	
	public List<Ser> getAncianos(int cantidad){
		ArrayList<Ser> ancianos=new ArrayList<>();
		int esperanzaVida=80;
		for (int i = 0; i < cantidad; i++) {
			try {
				ancianos.add(new Ser(esperanzaVida));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ancianos;
	}
}
