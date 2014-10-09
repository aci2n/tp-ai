package implementacion;

public class ItemMaterial {
	private float cantidad;
	private Material material;
	
	public ItemMaterial(Material material, Float cantidad) {
		this.cantidad=cantidad;
		this.material=material;
	}

	public float getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
}
