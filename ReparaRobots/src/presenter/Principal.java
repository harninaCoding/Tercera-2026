package presenter;

import view.GUI;

public class Principal {
    public static void main(String[] args) {
        GUI window = new GUI();
        new EventsGUI(window);
        window.setVisible(true);
    }
}
