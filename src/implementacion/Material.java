package implementacion;

public class Material {
	private String codigo;
	private String nombre;
	private float puntoPedido;
	private Proveedor proveedor;
	private float cantidad;
	private float costo;
	private boolean activo;
	
	public Material(String codigo2, String nombre2, float puntoPedido2,
			Proveedor proveedor2, float cantidad2, float costo2) {
		setCodigo(codigo2);
		setNombre(nombre2);
		setPuntoPedido(puntoPedido2);
		setProveedor(proveedor2);
		setCantidad(cantidad2);
		setCosto(costo2);
	}

	public boolean sosElMaterial(String codigo2) {
		if (codigo2.compareTo(codigo)==0)
			return true;
		return false;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntoPedido(float puntoPedido) {
		this.puntoPedido = puntoPedido;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public float getPuntoPedido() {
		return puntoPedido;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public float getCantidad() {
		return cantidad;
	}

	public float getCosto() {
		return costo;
	}
	
	public MaterialView generarMaterialView(){
		
		return new MaterialView(this.codigo, this.nombre, this.puntoPedido,
			this.proveedor, this.cantidad, this.costo);
	}
	
}
