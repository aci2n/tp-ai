package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;

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
	
}
