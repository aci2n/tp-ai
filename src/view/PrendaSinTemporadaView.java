package view;

import java.util.Collection;

public class PrendaSinTemporadaView extends PrendaView {
	private Collection<ItemMaterialView> materiales;
	
	public PrendaSinTemporadaView(String codigo, String nombre, Collection<ItemMaterialView> materiales, boolean activo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.materiales = materiales;
		this.precio=calcularPrecio();
	}

	public Collection<ItemMaterialView> getMateriales() {
		return materiales;
	}

	public void setMateriales(Collection<ItemMaterialView> materiales) {
		this.materiales = materiales;
	}
	
	protected float calcularPrecio(){
		float precio = 0;
		Collection<ItemMaterialView> materiales = this.materiales;
		for(ItemMaterialView im : materiales){
			precio = precio + (im.getMaterial().getCosto())*(im.getCantidad());
		}
		return precio;
	}
}
