package presenter;

import model.Reparable;
import model.ReparacionInforme;
import model.TipoReparable;
import view.IAccessGUI;

public class EventsGUI {
    private IAccessGUI vista;
    private Taller taller;

    public EventsGUI(IAccessGUI vista) {
        this.vista = vista;
        taller = new Taller();
        configurarListeners();
        actualizarVista();
    }

    private void configurarListeners() {
        vista.getBtnC3PO().addActionListener(e -> alAñadir(TipoReparable.PROTOCOLO));
        vista.getBtnSoldado().addActionListener(e -> alAñadir(TipoReparable.COMBATE));
        vista.getBtnVehiculo().addActionListener(e -> alAñadir(TipoReparable.VEHICULO));
        vista.getBtnRepara().addActionListener(e -> alSiguientePaso());
        vista.getBtnRecargar().addActionListener(e -> alRecargar());
    }

    private void actualizarVista() {
        actualizarCola();
        actualizarBateria();
    }

    private void actualizarCola() {
        vista.actualizarCola(taller.getCola());
    }

    private void actualizarBateria() {
        vista.getEtiquetaBateria().setText("Batería: " + taller.getBateria() + "%");
    }

    private void mostrarMensaje(String mensaje) {
        vista.getCampoEstado().setText(mensaje);
    }

    private void mostrarError(String mensaje) {
        vista.getCampoEstado().setText("ERROR: " + mensaje);
    }

    public void alAñadir(TipoReparable tipo) {
        try {
            Reparable nuevo = ReparableFactory.crearReparable(tipo);
            taller.agregarReparable(nuevo);
            actualizarCola();
        } catch (IllegalStateException | IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    public void alSiguientePaso() {
        ReparacionInforme informe = taller.procesarSiguiente();

        if (!informe.isExito()) {
            if (informe.getMensajeError() != null) {
                mostrarError(informe.getMensajeError());
            }
            return;
        }

        if (informe.getMensaje() != null) {
            mostrarMensaje(informe.getMensaje());
        }

        if (informe.getElementoReparado() != null) {
            mostrarMensaje(informe.getElementoReparado().getNombre() + " ha sido enviado a la base.");
        }

        actualizarCola();
        actualizarBateria();
    }

    public void alRecargar() {
        taller.recargar();
        actualizarBateria();
        mostrarMensaje("Estación recargada al 100%.");
    }
}
