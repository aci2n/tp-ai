package implementacion;

import java.util.Collection;

public class ConjuntoPrenda extends Prenda{
	private String codigo;
	private float descuento;
	private Collection<Prenda> prendas;
	
	public ConjuntoPrenda(){
		
	}
	
	public ConjuntoPrenda(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		
	}

	public float calcularPrecio() {
		return 0;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public Collection<Prenda> getPrendas() {
		return this.prendas;
	}

	public void setPrendas(Collection<Prenda> prendas2) {
		this.setPrendas(prendas2);
	}	
}
