package implementacion;

import persistencia.AdministradorPersistenciaMaterial;

public class Material {
	private String codigo;
	private String nombre;
	private float puntoPedido;
	private Proveedor proveedor;
	private float cantidad;
	private float costo;
	
	public Material(String codigo, String nombre, float puntoPedido, Proveedor proveedor, float cantidad, float costo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.puntoPedido = puntoPedido;
		this.proveedor = proveedor;
		this.cantidad = cantidad;
		this.costo = costo;
		AdministradorPersistenciaMaterial.getInstance().insert(this);
	}
	
	public Material(){
		
	}

	public boolean sosElMaterial(String codigo) {
		if (codigo != null && codigo.compareTo(codigo)==0)
			return true;
		return false;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		actualizar();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		actualizar();
	}

	public void setPuntoPedido(float puntoPedido) {
		this.puntoPedido = puntoPedido;
		actualizar();
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
		actualizar();
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
		actualizar();
	}

	public void setCosto(float costo) {
		this.costo = costo;
		actualizar();
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
	
	public void actualizar() {
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}
	
	public void eliminar(){
		AdministradorPersistenciaMaterial.getInstance().delete(this);
	}
	
	public MaterialView generarMaterialView(){
		return new MaterialView(this.codigo, this.nombre, this.puntoPedido, this.proveedor, this.cantidad, this.costo);
	}
	
}
