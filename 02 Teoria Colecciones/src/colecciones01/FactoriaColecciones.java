package colecciones01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FactoriaColecciones {
	public static List<Integer> factoryMethod() {
		// hace un sorteo entre uno y dos
		int sorteo = (int) (Math.random()*2);
		if (sorteo == 0) {
			return new ArrayList<Integer>();
		}
		if (sorteo == 1) {
			return new LinkedList<Integer>();
		}
		return null;
	}
}
