package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrendaSinTemporada;

public class PrendaSinTemporada extends PrendaSimple {

	public PrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> materiales) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.materiales = materiales;
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().insert(this);
	}

	public float calcularPrecio() {
		return 0;
	}

	public Collection<ItemMaterial> getMateriales() {
		return this.materiales;
	}

	public void setMateriales(Collection<ItemMaterial> materiales) {
		this.materiales = materiales;
		actualizar();
	}

	public void actualizar() {
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().update(this);
	}

	public void eliminar() {
		AdministradorPersistenciaPrendaSinTemporada.getInstancia().delete(this);
	}
	
}
