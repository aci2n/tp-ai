package implementacion;

import java.util.Collection;

import controlador.Controlador;
import persistencia.AdministradorPersistenciaMaterial;

public class Material {
	private String codigo;
	private String nombre;
	private float puntoPedido;
	private Proveedor proveedor;
	private float cantidad;
	private float costo;
	private boolean activo;
	
	public Material(String codigo, String nombre, float puntoPedido,Proveedor proveedor, float cantidad, float costo) {
		this.codigo=codigo;
		this.nombre=nombre;
		this.puntoPedido=puntoPedido;
		this.proveedor=proveedor;
		this.cantidad=cantidad;
		this.costo=costo;
		activo = true;
		AdministradorPersistenciaMaterial.getInstance().insert(this);
	}
	
	public Material(){
	}

	public boolean sosElMaterial(String codigo) {
		if (codigo!=null && getCodigo().compareTo(codigo)==0)
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
	
	public static Collection<Material> obtenerMateriales(){
		return AdministradorPersistenciaMaterial.getInstance().obtenerMateriales();
	}
	
	public static Material buscarMaterial(String codigo){
		return AdministradorPersistenciaMaterial.getInstance().buscarMaterial(codigo);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void construirDesdeDB(String codigo, String cuit, String nombre, float cantidad, float puntoPedido, float costo, boolean activo) {
		this.codigo=codigo;
		this.proveedor=Controlador.getControlador().obtenerProveedor(cuit);
		this.nombre=nombre;
		this.cantidad=cantidad;
		this.puntoPedido=puntoPedido;
		this.costo=costo;
		this.activo=activo;		
	}
	
	
}
