package implementacion;

public abstract class Prenda {
	private String codigo;
	private String nombre;
	private boolean activo;
		
	public abstract float calcularPrecio();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean sosLaPrenda(String codigo2) {
		if (this.codigo.compareTo(codigo2)==0)
			return true;
		return false;
	}
	
	
}