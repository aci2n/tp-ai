package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;

public class PrendaConTemporada extends PrendaSimple{
	private String temporada;
	private float porcentajeVenta;
	
	public PrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> items) {
		setCodigoDB(codigo); //fijarse si esto no jode con la persistencia despues
		setNombreDB(nombre);
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		setMaterialesDB(items);
		setActivoDB(true);
		AdministradorPersistenciaPrenda.getInstancia().insert(this);
	}


	public PrendaConTemporada() {
	}


	public float calcularPrecio(){
			return 0;
	}


	public float getPorcentajeVenta() {
		return this.porcentajeVenta;
	}


	public String getTemporada() {
		return this.temporada;
	}


	public void setTemporada(String temporada) {
		this.temporada = temporada;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}


	public void setPorcentajeVenta(float porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}


	public void construirDesdeDB(String codigo, String nombre, String temporada,float porcentajeVenta, Collection<ItemMaterial> items) {
		setNombreDB(nombre);
		setCodigoDB(codigo);
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		setMaterialesDB(items);
	}

}
