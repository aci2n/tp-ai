package implementacion;

import view.ItemFacturaView;

public class ItemFactura {
	private Prenda prenda;
	private float cantidad;
	
	public ItemFactura(Prenda prenda, float cantidad){
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

	public ItemFacturaView generarItemPrendaView() {
		return new ItemFacturaView(this.prenda.generarPrendaView(),this.cantidad);
	}		
}
