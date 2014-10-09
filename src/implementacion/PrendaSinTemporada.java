package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;
import view.ItemMaterialView;
import view.PrendaSimpleView;
import view.PrendaView;

public class PrendaSinTemporada extends PrendaSimple{

	public PrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> items) {
		setActivoDB(true);
		setCodigoDB(codigo);
		setNombreDB(nombre);
		setMaterialesDB(items);
		AdministradorPersistenciaPrenda.getInstancia().insert(this);
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

	public void construirDesdeDB(String codigo, String nombre, Collection<ItemMaterial> items) {
		setActivoDB(true);
		setCodigoDB(codigo);
		setNombreDB(nombre);
		setMaterialesDB(items);
	}

	public PrendaView generarPrendaView() {
		Collection<ItemMaterialView> itemsView = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : getMateriales()) {
			itemsView.add(item.generarItemMaterialView());
		}
		return new PrendaSimpleView(getCodigo(), getNombre(), isActivo(), calcularPrecio(), itemsView);
	}
	
}
