package implementacion;

public class MaterialView {
	
	private String codigo;
	private String nombre;
	private float puntoPedido;
	private Proveedor proveedor;
	private float cantidad;
	private float costo;
	private boolean activo;

	public MaterialView(String codigo, String nombre, float puntoPedido,
			Proveedor proveedor, float cantidad, float costo){
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.puntoPedido = puntoPedido;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.costo = costo;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

}

