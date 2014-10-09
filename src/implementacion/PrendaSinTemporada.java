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
		return 0;
	}

	public void construirDesdeDB(String codigo, String nombre, Collection<ItemMaterial> items) {
		setActivoDB(true);
		setCodigoDB(codigo);
		setNombreDB(nombre);
		setMaterialesDB(items);
	}
	
}
