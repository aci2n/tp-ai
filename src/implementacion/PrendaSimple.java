package implementacion;

import java.util.Collection;

public abstract class PrendaSimple extends Prenda{
	protected Collection<ItemMaterial> materiales;
	
	public Collection<ItemMaterial> getMateriales() {
		return this.materiales;
	}
	
	public void setMateriales(Collection<ItemMaterial> materiales){
		this.materiales = materiales;
	}	
	
	public void descontarStock(float cantidad){
		for (ItemMaterial im : materiales)
			im.getMaterial().modificarStock(-cantidad*im.getCantidad());
	}
	
	public boolean tenesElMaterial(String codigo) {
		for (ItemMaterial im : materiales)
			if (im.getMaterial().sosElMaterial(codigo))
				return true;
		return false;
	}

	public boolean tenesLaPrenda(String codigo) {
		return false;
	}
}
