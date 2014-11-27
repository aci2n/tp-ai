package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaFactura;
import view.FacturaView;
import view.ItemFacturaView;

public class Factura {
	private Collection<ItemFactura> prendas;
	private int numeroFactura;
	private static int contador = 1;
	private boolean confirmada;
	
	public Factura(){
		this.prendas = new ArrayList<ItemFactura>();
		numeroFactura = contador++;
	}
	
	// El método confirmar() retorna false en caso de que no haya stock suficiente de alguna de las prendas
	public boolean confirmar() {
		boolean stockSuficiente = true;
		for (ItemFactura item : prendas) {
			stockSuficiente = stockSuficiente && item.getPrenda().hayStock(item.getCantidad());
		}
		if (stockSuficiente) {
			for (ItemFactura item : prendas) {
				item.getPrenda().descontarStock(item.getCantidad());
			}
			AdministradorPersistenciaFactura.getInstancia().insert(this);
		}
		return false;	
	}

	public Collection<ItemFactura> getPrendas() {
		return prendas;
	}

	public void setPrendas(Collection<ItemFactura> prendas) {
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
		for (ItemFactura ip : this.prendas)
			total = total + ip.calcularSubTotal();
		return total;
	}
	
	public FacturaView generarFacturaView(){
		Collection<ItemFacturaView> prendasView = new ArrayList<ItemFacturaView>();
		for (ItemFactura p : this.prendas)
			prendasView.add(p.generarItemPrendaView());			
		return new FacturaView(this.numeroFactura, prendasView);
	}

	public static Collection<Factura> obtenerFacturas() {
		return AdministradorPersistenciaFactura.getInstancia().obtenerFacturas();
	}
	
	public void agregarItem(Prenda prenda, float cantidad) {
		ItemFactura item = new ItemFactura(prenda, cantidad);
		this.prendas.add(item);
	}

	public static int getContador() {
		return contador;
	}
	
	public boolean sosLaFactura(int numero){
		return this.numeroFactura==numero;
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}
}
