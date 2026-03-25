package model;

public class ReparacionInforme {
    private boolean exito;
    private String mensaje;
    private String mensajeError;
    private Reparable elementoReparado;

    public ReparacionInforme(boolean exito, String mensaje, String mensajeError, Reparable elementoReparado) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.mensajeError = mensajeError;
        this.elementoReparado = elementoReparado;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public Reparable getElementoReparado() {
        return elementoReparado;
    }
}
