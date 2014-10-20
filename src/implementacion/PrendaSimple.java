package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;

public abstract class PrendaSimple extends Prenda{
	protected Collection<ItemMaterial> materiales;
	
	public Collection<ItemMaterial> getMateriales() {
		return this.materiales;
	}
	
	public void setMateriales(Collection<ItemMaterial> materiales){
		this.materiales = materiales;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}
	
	public void setMaterialesDB(Collection<ItemMaterial> materiales){
		this.materiales = materiales;
	}
	
}
