package view;

public class ProveedorView {
	private String nombre;
	private String cuit;
	
	public ProveedorView(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
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
