package implementacion;

import view.ItemPrendaView;

public class ItemPrenda {
	private Prenda prenda;
	private float cantidad;
	
	public ItemPrenda(Prenda prenda, float cantidad){
		this.prenda=prenda;
		this.cantidad=cantidad;
	}

	public Prenda getPrenda() {
		return prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public float calcularSubTotal(){
		return prenda.calcularPrecio()*cantidad;
	}

	public ItemPrendaView generarItemPrendaView() {
		return new ItemPrendaView(this.prenda.generarPrendaView(),this.cantidad);
	}		
}
