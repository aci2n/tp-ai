package implementacion;

import java.util.Collection;

public class Proveedor {
	private String nombre;
	private String cuit;
	private boolean activo;
	
	public Proveedor (String nombre2, String cuit2){
		setNombre(nombre2);
		setCuit(cuit2);
	}
	
	public boolean sosElProveedor(String cuit2) {
		if (cuit2.compareTo(cuit)==0)
			return true;
		return false;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCuit() {
		return cuit;
	}

	
}
