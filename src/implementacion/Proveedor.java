package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaProveedor;
import view.ProveedorView;


public class Proveedor {
	private String nombre;
	private String cuit;
	private boolean activo;
		
	public Proveedor (String nombre, String cuit){
		this.nombre=nombre;
		this.cuit=cuit;
		this.activo=true;
		AdministradorPersistenciaProveedor.getInstancia().insert(this);
	}
	
	public Proveedor() {
	}

	public boolean sosElProveedor(String cuit) {
		if (cuit != null && getCuit().compareTo(cuit)==0)
			return true;
		return false;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
	
	public static Proveedor buscarProveedor(String cuit){
		return AdministradorPersistenciaProveedor.getInstancia().buscarProveedor(cuit);
	}
	
	public static Collection<Proveedor> obtenerProveedores(){
		return AdministradorPersistenciaProveedor.getInstancia().obtenerProveedores();
	}

	public boolean isActivo() {
		return activo;
	}
		
	public ProveedorView generarProveedorView() {
		return new ProveedorView(this.nombre, this.cuit, this.activo);
	}

	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaProveedor.getInstancia().delete(this);
	}
	
	public void actualizar(){
		AdministradorPersistenciaProveedor.getInstancia().update(this);
	}
}
