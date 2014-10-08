package implementacion;

import persistencia.AdministradorPersistenciaProveedor;

public class Proveedor {
	private String nombre;
	private String cuit;
	private boolean activo;
		
	public Proveedor (String nombre2, String cuit2){
		this.nombre=nombre2;
		this.cuit=cuit2;
		this.activo=true;
		AdministradorPersistenciaProveedor.getInstancia().insert(this);
	}
	
	public Proveedor() {
	}

	public boolean sosElProveedor(String cuit2) {
		if (cuit2.compareTo(cuit)==0)
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
	}

	public void eliminar() {
		setActivo(false);
		AdministradorPersistenciaProveedor.getInstancia().delete(this);
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
	
	public static Proveedor buscarProveedor(String cuit){
		Proveedor p = AdministradorPersistenciaProveedor.getInstancia().buscarProveedor(cuit);
		return p;
	}
}
