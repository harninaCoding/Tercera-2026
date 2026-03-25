package view;

import model.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements IAccessGUI {
    private JLabel slot1, slot2, slot3;
    private JLabel etiquetaBateria;
    private JButton btnC3PO, btnSoldado, btnRepara, btnRecargar, btnVehiculo;
    private JTextField campoEstado;

    public GUI() {
        setTitle("Centro de Salud R2-D2");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Línea 1: Botones de selección
        btnC3PO = new JButton("C-3PO");
        btnSoldado = new JButton("Soldado de Asalto");
        btnVehiculo = new JButton("Vehículo");
        JPanel panelL1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        panelL1.add(btnC3PO);
        panelL1.add(btnSoldado);
        panelL1.add(btnVehiculo);

        // Línea 2: Botones de acción y Batería
        btnRepara = new JButton("Repara");
        btnRecargar = new JButton("Recargar Estación");
        etiquetaBateria = new JLabel("Batería: 100%");
        JPanel panelL2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        panelL2.add(btnRepara);
        panelL2.add(btnRecargar);
        panelL2.add(etiquetaBateria);

        // Línea 3: Bahías de reparación
        Dimension dimensionSlot = new Dimension(460, 60);
        slot1 = new JLabel("Vacío", JLabel.CENTER);
        slot2 = new JLabel("Vacío", JLabel.CENTER);
        slot3 = new JLabel("Vacío", JLabel.CENTER);

        for (JLabel slot : Arrays.asList(slot1, slot2, slot3)) {
            slot.setPreferredSize(dimensionSlot);
            slot.setMinimumSize(dimensionSlot);
            slot.setMaximumSize(dimensionSlot);
            slot.setAlignmentX(CENTER_ALIGNMENT);
        }

        slot1.setBorder(BorderFactory.createTitledBorder("Bahía 1"));
        slot2.setBorder(BorderFactory.createTitledBorder("Bahía 2"));
        slot3.setBorder(BorderFactory.createTitledBorder("Bahía 3"));

        JPanel panelSlots = new JPanel();
        panelSlots.setLayout(new BoxLayout(panelSlots, BoxLayout.Y_AXIS));
        panelSlots.add(slot1);
        panelSlots.add(slot2);
        panelSlots.add(slot3);

        // Línea 4: Estado
        campoEstado = new JTextField(30);
        campoEstado.setEditable(false);
        JPanel panelL4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        panelL4.add(new JLabel("Estado:"));
        panelL4.add(campoEstado);

        // Añadir paneles con espaciado mínimo
        add(panelL1);
        add(panelL2);
        add(panelSlots);
        add(panelL4);
        add(Box.createVerticalGlue());

        setLocationRelativeTo(null);
    }

    @Override
    public JButton getBtnC3PO() {
        return btnC3PO;
    }

    @Override
    public JButton getBtnSoldado() {
        return btnSoldado;
    }

    @Override
    public JButton getBtnRepara() {
        return btnRepara;
    }

    @Override
    public JButton getBtnRecargar() {
        return btnRecargar;
    }

    @Override
    public JButton getBtnVehiculo() {
        return btnVehiculo;
    }

    @Override
    public void actualizarCola(List<Reparable> cola) {
        List<JLabel> bahias = Arrays.asList(slot1, slot2, slot3);
        for (int i = 0; i < bahias.size(); i++) {
            if (i < cola.size()) {
                bahias.get(i).setText(cola.get(i).toString());
            } else {
                bahias.get(i).setText("Vacío");
            }
        }
    }

    @Override
    public JLabel getEtiquetaBateria() {
        return etiquetaBateria;
    }

    @Override
    public JTextField getCampoEstado() {
        return campoEstado;
    }
}
