package equalinnerMal02;

public class Cuenta {
	
	/////////////////////////////////
	//NO HAY EQUALS
	///////////////////////////////
	private int numero;
	private String tipo;
	public Cuenta(int numero, String tipo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
