package view;

public class MaterialView {
	
	private String codigo;
	private String nombre;
	private float puntoPedido;
	private String cuit;
	private float cantidad;
	private float costo;
	private boolean activo;

	public MaterialView(String codigo, String nombre, float puntoPedido, String cuit, float cantidad, float costo){
		this.codigo = codigo;
		this.nombre = nombre;
		this.puntoPedido = puntoPedido;
		this.cuit=cuit;
		this.cantidad = cantidad;
		this.costo = costo;
		this.activo = true;
	}

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

	public float getPuntoPedido() {
		return puntoPedido;
	}

	public void setPuntoPedido(float puntoPedido) {
		this.puntoPedido = puntoPedido;
	}


	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean sosElMaterial(String codigo) {
		return this.codigo.equals(codigo);
	}

}

