package view;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Reparable;

public interface IAccessGUI {

    JButton getBtnC3PO();

    JButton getBtnSoldado();

    JButton getBtnRepara();

    JButton getBtnRecargar();

    JButton getBtnVehiculo();

    void actualizarCola(List<Reparable> cola);

    JLabel getEtiquetaBateria();

    JTextField getCampoEstado();
}
