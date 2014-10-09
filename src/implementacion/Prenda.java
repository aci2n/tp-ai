package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;
import view.PrendaView;

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
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}

	public boolean sosLaPrenda(String codigo) {
		if (codigo != null && this.getCodigo().compareTo(codigo)==0)
			return true;
		return false;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void setCodigoDB(String codigo){
		this.codigo = codigo;
	}
	
	public void setNombreDB(String nombre){
		this.nombre = nombre;
	}
	
	public void setActivoDB(boolean activo){
		this.activo = activo;
	}
	
	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaPrenda.getInstancia().delete(this);
	}

	public static Collection<Prenda> obtenerPrendas() {
		return AdministradorPersistenciaPrenda.getInstancia().obtenerPrendas();
	}
	
	public static Prenda buscarPrenda(String codigo){
		return AdministradorPersistenciaPrenda.getInstancia().buscarPrenda(codigo);
	}
	
	public abstract PrendaView generarPrendaView();
	
}