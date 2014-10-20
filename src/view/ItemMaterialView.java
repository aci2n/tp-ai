package view;

public class ItemMaterialView {
	private float cantidad;
	private MaterialView material;
	
	public ItemMaterialView(float cantidad, MaterialView material) {
		this.cantidad = cantidad;
		this.material = material;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public MaterialView getMaterial() {
		return material;
	}

	public void setMaterial(MaterialView material) {
		this.material = material;
	}
	
}
