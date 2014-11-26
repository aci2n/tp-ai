package view;


public class ItemFacturaView {
	private PrendaView prenda;
	private float cantidad;
	private float subtotal;

	public ItemFacturaView(PrendaView prenda, float cantidad){
		this.prenda=prenda;
		this.cantidad=cantidad;
		this.subtotal=calcularSubtotal();
	}

	
	private float calcularSubtotal() {
		return prenda.getPrecio()*this.cantidad;
	}


	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}


	public PrendaView getPrenda() {
		return prenda;
	}

	public void setPrenda(PrendaView prenda) {
		this.prenda = prenda;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
}
