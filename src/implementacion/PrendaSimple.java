package implementacion;

import java.util.Collection;

public abstract class PrendaSimple extends Prenda{
	private Collection<ItemMaterial> materiales;
	
	public Collection<ItemMaterial> getMateriales() {
		return this.materiales;
	}
	
	public void setMateriales(Collection<ItemMaterial> materiales){
		this.materiales=materiales;
	}

}
