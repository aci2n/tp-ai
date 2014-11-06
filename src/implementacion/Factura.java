package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaFactura;
import view.FacturaView;
import view.ItemPrendaView;

public class Factura {
	private Collection<ItemPrenda> prendas;
	private int numeroFactura;
	private static int contador = 1;
	
	public Factura(Collection<ItemPrenda> prendas){
		this.prendas=prendas;
		numeroFactura=contador++;
		AdministradorPersistenciaFactura.getInstancia().insert(this);
	}

	public Factura() {
	}

	public Collection<ItemPrenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(Collection<ItemPrenda> prendas) {
		this.prendas = prendas;
	}

	public static void setContador(int contador) {
		Factura.contador = contador;
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public float calcularPrecio(){
		float total = 0;
		for (ItemPrenda ip : this.prendas)
			total = total + ip.calcularSubTotal();
		return total;
	}
	
	public FacturaView generarFacturaView(){
		Collection<ItemPrendaView> prendasView = new ArrayList<ItemPrendaView>();
		for (ItemPrenda p : this.prendas)
			prendasView.add(p.generarItemPrendaView());			
		return new FacturaView(this.numeroFactura, prendasView);
	}

	public static Collection<Factura> obtenerFacturas() {
		return AdministradorPersistenciaFactura.getInstancia().obtenerFacturas();
	}

	public static int getContador() {
		return contador;
	}
	
	public boolean sosLaFactura(int numero){
		return this.numeroFactura==numero;
	}
}
