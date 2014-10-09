package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaProveedor;


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
		AdministradorPersistenciaProveedor.getInstancia().update(this);
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
		AdministradorPersistenciaProveedor.getInstancia().update(this);
	}

	public void eliminar() {
		setActivo(false);
		AdministradorPersistenciaProveedor.getInstancia().delete(this);
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
		AdministradorPersistenciaProveedor.getInstancia().update(this);
	}	
	
	public static Proveedor buscarProveedor(String cuit){
		Proveedor p = AdministradorPersistenciaProveedor.getInstancia().buscarProveedor(cuit);
		return p;
	}
	
	public static Collection<Proveedor> obtenerProveedores(){
		return AdministradorPersistenciaProveedor.getInstancia().obtenerProveedores();
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void construirDesdeDB(String cuit, String nombre, boolean activo){
		this.cuit=cuit;
		this.nombre=nombre;
		this.activo=activo;
	}

}
