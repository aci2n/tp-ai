package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaProveedor;


public class Proveedor {
	private String nombre;
	private String cuit;
		
	public Proveedor (String nombre, String cuit){
		this.nombre = nombre;
		this.cuit = cuit;
	}
	
	public Proveedor() {
	}

	public boolean sosElProveedor(String cuit) {
		if (cuit != null && cuit.compareTo(cuit)==0)
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

	public void eliminar() {
		setActivo(false);
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
		AdministradorPersistenciaProveedor.getInstancia().update(this);
	}	
	
	public static Proveedor buscarProveedor(String cuit){
		Proveedor p = null;
		return p;
	}
	
	public static Collection<Proveedor> obtenerProveedores(){
		return AdministradorPersistenciaProveedor.getInstancia().obtenerProveedores();
	}

	public boolean isActivo() {
		return activo;
	}

}
