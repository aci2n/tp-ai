package view;

import implementacion.ItemMaterial;

import java.util.ArrayList;
import java.util.Collection;

public class PrendaSinTemporadaView extends PrendaView {
	private Collection<ItemMaterialView> materiales;
	
	public PrendaSinTemporadaView(String codigo, String nombre, Collection<ItemMaterial> materiales, float precio, boolean activo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.activo = activo;
		this.materiales = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : materiales) {
			this.materiales.add(item.generarItemMaterialView());
		}
	}

	public Collection<ItemMaterialView> getMateriales() {
		return materiales;
	}

	public void setMateriales(Collection<ItemMaterialView> materiales) {
		this.materiales = materiales;
	}
}
