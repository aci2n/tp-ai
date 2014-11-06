package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaPrendaSinTemporada;
import view.ItemMaterialView;
import view.PrendaSinTemporadaView;
import view.PrendaView;

public class PrendaSinTemporada extends PrendaSimple{

	public PrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> materiales) {
		this.activo = true;
		this.codigo = codigo;
		this.nombre = nombre;
		this.materiales = materiales;
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().insert(this);
	}

	public PrendaSinTemporada() {
	}

	public float calcularPrecio() {
		float precio = 0;
		Collection<ItemMaterial> materiales = super.getMateriales();
		for(ItemMaterial im : materiales){
			precio = precio + (im.getMaterial().getCosto())*(im.getCantidad());
		}
		return precio;
	}
	
	public void actualizar(){
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().update(this);
	}
	
	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().delete(this);
	}

	public PrendaView generarPrendaView() {
		Collection<ItemMaterialView> itemsView = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : getMateriales()) {
			itemsView.add(item.generarItemMaterialView());
		}
		return new PrendaSinTemporadaView(this.codigo, this.nombre, itemsView, this.activo);
	}
	
}
