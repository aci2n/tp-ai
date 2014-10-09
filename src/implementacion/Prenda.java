package implementacion;

public abstract class Prenda {
	protected String codigo;
	protected String nombre;
		
	public abstract float calcularPrecio();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		actualizar();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		actualizar();
	}
	
	public abstract void actualizar();
	
	public abstract void eliminar();
	
	public boolean sosLaPrenda(String codigo) {
		if (codigo != null && this.codigo.compareTo(codigo) == 0)
			return true;
		return false;
	}
	
}