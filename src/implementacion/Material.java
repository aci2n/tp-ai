package implementacion;

import java.util.Collection;
import java.util.Observable;
import persistencia.AdministradorPersistenciaMaterial;
import view.MaterialView;

public class Material extends Observable {
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
		this.activo = true;
		this.addObserver(SistemaProveedores.getInstance());
		AdministradorPersistenciaMaterial.getInstance().insert(this);
	}
	
	public Material(){
		this.addObserver(SistemaProveedores.getInstance());
	}

	public boolean sosElMaterial(String codigo) {
		if (codigo!=null && getCodigo().compareTo(codigo)==0)
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
		setChanged();
		if (this.cantidad < this.puntoPedido) 
			this.notifyObservers();
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
	
	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaMaterial.getInstance().delete(this);
	}
	
	public void actualizar(){
		AdministradorPersistenciaMaterial.getInstance().update(this);
	}
	
	public MaterialView generarMaterialView(){
		return new MaterialView(this.codigo, this.nombre, this.puntoPedido, this.proveedor.getCuit(), this.cantidad, this.costo);
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

	public void modificarStock(float cantidad) {
		setCantidad(this.cantidad+cantidad);
		this.actualizar();
	}

	public boolean tenesElProveedor(String cuit) {
		return proveedor.sosElProveedor(cuit);
	}

	public boolean tenesStock(float cantidad) {
		return this.cantidad>=cantidad;
	}
}
