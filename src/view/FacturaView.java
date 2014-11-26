package view;

import java.util.Collection;

public class FacturaView {
	private int numeroFactura;
	private Collection<ItemFacturaView> prendas;
	private float precio;
	
	public FacturaView(int numeroFactura, Collection<ItemFacturaView> prendas){
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

	public Collection<ItemFacturaView> getPrendas() {
		return prendas;
	}

	public void setPrendas(Collection<ItemFacturaView> prendas) {
		this.prendas = prendas;
	}
	
	private float calcularPrecio(){
		float total = 0;
		for (ItemFacturaView ip : this.prendas)
			total = total + ip.getSubtotal();
		return total;
	}

	public float getPrecio() {
		return precio;
	}	
	
	public boolean sosLaFactura(int numero){
		return this.numeroFactura==numero;
	}
}
