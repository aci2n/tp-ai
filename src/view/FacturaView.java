package view;

import java.util.Collection;

public class FacturaView {
	private int numeroFactura;
	private Collection<ItemPrendaView> prendas;
	private float precio;
	
	public FacturaView(int numeroFactura, Collection<ItemPrendaView> prendas){
		this.numeroFactura=numeroFactura;
		this.prendas=prendas;
		this.precio=calcularPrecio();
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Collection<ItemPrendaView> getPrendas() {
		return prendas;
	}

	public void setPrendas(Collection<ItemPrendaView> prendas) {
		this.prendas = prendas;
	}
	
	private float calcularPrecio(){
		float total = 0;
		for (ItemPrendaView ip : this.prendas)
			total = total + ip.getSubtotal();
		return total;
	}

	public float getPrecio() {
		return precio;
	}	
	
	
}
