package clone04;

public class ClonarUno implements Cloneable {
	private int uno,dos,tres;
	
	@Override
	public ClonarUno clone() throws CloneNotSupportedException {
		return (ClonarUno) super.clone();
	}

	@Override
	public String toString() {
		return "ClonarUno [uno=" + uno + ", dos=" + dos + ", tres=" + tres + "]";
	}

	public ClonarUno(int uno, int dos, int tres) {
		super();
		this.uno = uno;
		this.dos = dos;
		this.tres = tres;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getDos() {
		return dos;
	}

	public void setDos(int dos) {
		this.dos = dos;
	}

	public int getTres() {
		return tres;
	}

	public void setTres(int tres) {
		this.tres = tres;
	}
	
}
