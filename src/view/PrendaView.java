package view;

public abstract class PrendaView {
	protected String codigo;
	protected String nombre;
	protected float precio;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public boolean sosLaPrenda(String codigo){
		return this.codigo.equals(codigo);
	}
	
	protected abstract float calcularPrecio();
}
