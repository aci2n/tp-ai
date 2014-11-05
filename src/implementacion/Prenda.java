package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaConjuntoPrenda;
import persistencia.AdministradorPersistenciaPrendaConTemporada;
import persistencia.AdministradorPersistenciaPrendaSinTemporada;
import view.PrendaView;

public abstract class Prenda {
	protected String codigo;
	protected String nombre;
	protected boolean activo;
		
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

	public abstract void eliminar();
	
	public abstract void actualizar();
	
	public static Collection<Prenda> obtenerPrendas() { //ver si esto anda
		Collection<Prenda> prendas = new ArrayList<Prenda>();
		prendas.addAll(AdministradorPersistenciaConjuntoPrenda.getInstancia().obtenerConjuntosPrenda()); 
		prendas.addAll(AdministradorPersistenciaPrendaConTemporada.getInstancia().obtenerPrendasConTemporada());
		prendas.addAll(AdministradorPersistenciaPrendaSinTemporada.getInstancia().obtenerPrendasSinTemporada());
		return prendas;
	}
	
	public static Prenda buscarPrenda(String codigo){
		Prenda p = null;
		p = AdministradorPersistenciaConjuntoPrenda.getInstancia().buscarPrenda(codigo);
		if (p==null){
			p=AdministradorPersistenciaPrendaConTemporada.getInstancia().buscarPrenda(codigo);
			if(p == null){
				p=AdministradorPersistenciaPrendaSinTemporada.getInstancia().buscarPrenda(codigo);
			}
		}
		return p;	
	}
	
	public abstract PrendaView generarPrendaView();
}