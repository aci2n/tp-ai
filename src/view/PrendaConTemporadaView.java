package view;

import implementacion.ItemMaterial;

import java.util.ArrayList;
import java.util.Collection;

public class PrendaConTemporadaView extends PrendaView {
	private String temporada;
	private float porcentajeVenta;
	private Collection<ItemMaterialView> materiales;
	
	
	public PrendaConTemporadaView(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> materiales, float precio, boolean activo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.temporada = temporada;
		this.porcentajeVenta = porcentajeVenta;
		this.precio = precio;
		this.activo = activo;
		this.materiales = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : materiales) {
			this.materiales.add(item.generarItemMaterialView());
		}
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
