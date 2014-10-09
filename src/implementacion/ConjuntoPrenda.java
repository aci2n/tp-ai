package implementacion;

import java.util.Collection;

public class ConjuntoPrenda extends Prenda {
	private float descuento;
	private Collection<Prenda> prendas;
	
	public ConjuntoPrenda(){
		
	}
	
	public ConjuntoPrenda(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descuento = descuento;
		this.prendas = prendas;
		actualizarPrenda();
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

	public void setPrendas(Collection<Prenda> prendas) {
		this.prendas = prendas;
	}	
}
