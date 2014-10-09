package implementacion;

import persistencia.AdministradorPersistenciaMaterial;

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
		activo = true;
		AdministradorPersistenciaMaterial.getInstance().insert(this);
	}
	
	public Material(){
		
	}

	public boolean sosElMaterial(String codigo2) {
		if (codigo2.compareTo(codigo)==0)
			return true;
		return false;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}

	public void setPuntoPedido(float puntoPedido) {
		this.puntoPedido = puntoPedido;
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}

	public void setCosto(float costo) {
		this.costo = costo;
		AdministradorPersistenciaMaterial.getInstance().update(this);
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
	
	public void eliminar(){
		this.activo=(false);
		AdministradorPersistenciaMaterial.getInstance().delete(this);
	}
	
	public MaterialView generarMaterialView(){
		
		return new MaterialView(this.codigo, this.nombre, this.puntoPedido,
			this.proveedor, this.cantidad, this.costo);
	}
	
}
