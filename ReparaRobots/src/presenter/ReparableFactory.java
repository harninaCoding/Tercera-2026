package presenter;

import model.*;

public class ReparableFactory {
    private static int contadorDroides = 1;

    public static Reparable crearReparable(TipoReparable tipo) {
        int danoInicial = (int) (Math.random() * 51 + 50);

        switch (tipo) {
            case PROTOCOLO:
                return new DroideProtocolo("C-3PO #" + contadorDroides++, danoInicial, 5);
            case COMBATE:
                String[] armas = { "Rifle Bláster", "Cañón de Muñeca", "Detonador Térmico" };
                String arma = armas[(int) (Math.random() * armas.length)];
                return new DroideCombate("Soldado #" + contadorDroides++, danoInicial, arma);
            case VEHICULO:
                String matricula = "V-" + (int) (Math.random() * 9000 + 1000);
                return new Vehiculo(matricula, danoInicial);
            default:
                throw new IllegalArgumentException("Tipo de reparable desconocido: " + tipo);
        }
    }
}
