package implementacion;

import java.util.Collection;

public abstract class PrendaSimple extends Prenda{
	protected Collection<ItemMaterial> materiales;
	
	public abstract Collection<ItemMaterial> getMateriales();
	
	public abstract void setMateriales(Collection<ItemMaterial> materiales);

}
