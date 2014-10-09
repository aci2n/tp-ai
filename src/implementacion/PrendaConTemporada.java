package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrendaConTemporada;

public class PrendaConTemporada extends PrendaSimple {
	private String temporada;
	private float porcentajeVenta;
	
	public PrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> materiales) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.temporada = temporada;
		this.porcentajeVenta = porcentajeVenta;
		this.materiales = materiales;
		actualizarPrenda();
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
		actualizarPrenda();
	}

	public void setPorcentajeVenta(float porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
		actualizarPrenda();
	}
	
	public Collection<ItemMaterial> getMateriales() {
		return this.materiales;
	}

	public void setMateriales(Collection<ItemMaterial> materiales) {
		this.materiales = materiales;
		actualizarPrenda();
	}

	public void actualizarPrenda() {
		AdministradorPersistenciaPrendaConTemporada.getInstancia().update(this);
	}

	public void eliminar() {
		AdministradorPersistenciaPrendaConTemporada.getInstancia().delete(this);
	}
	
}
