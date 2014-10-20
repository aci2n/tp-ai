package view;

public class ProveedorView {
	private String nombre;
	private String cuit;
	private boolean activo;
	
	public ProveedorView(String nombre, String cuit, boolean activo) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.setActivo(activo);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	
}
