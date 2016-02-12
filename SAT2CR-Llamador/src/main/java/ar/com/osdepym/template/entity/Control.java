package ar.com.osdepym.template.entity;


public class Control {
	private int idControl;
	private boolean siguiente = false;
	private boolean anterior =false;

	
	
	public int getIdControl() {
		return idControl;
	}
	public void setIdControl(int idControl) {
		this.idControl = idControl;
	}
	public boolean isSiguiente() {
		return siguiente;
	}
	public void setSiguiente(boolean siguiente) {
		this.siguiente = siguiente;
	}
	public boolean isAnterior() {
		return anterior;
	}
	public void setAnterior(boolean anterior) {
		this.anterior = anterior;
	}

	
	
}

 