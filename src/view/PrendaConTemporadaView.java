package view;

import java.util.Collection;

public class PrendaConTemporadaView extends PrendaView {
	private String temporada;
	private float porcentajeVenta;
	private Collection<ItemMaterialView> materiales;
	
	public PrendaConTemporadaView(String codigo, String nombre, boolean activo,  Collection<ItemMaterialView> materiales, String temporada,float porcentajeVenta) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.temporada = temporada;
		this.porcentajeVenta = porcentajeVenta;
		this.activo = activo;
		this.materiales = materiales;
		}

	public String getTemporada() {
		return temporada;
	}


	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}


	public float getPorcentajeVenta() {
		return porcentajeVenta;
	}


	public void setPorcentajeVenta(float porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
	}


	public Collection<ItemMaterialView> getMateriales() {
		return materiales;
	}


	public void setMateriales(Collection<ItemMaterialView> materiales) {
		this.materiales = materiales;
	}
}
