package view;

import java.util.Collection;

public class ConjuntoPrendaView extends PrendaView {
	private float descuento;
	private Collection<PrendaView> prendas;
	
	public ConjuntoPrendaView(String codigo, String nombre, float descuento, Collection<PrendaView> prendas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descuento = descuento;
		this.prendas = prendas;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Collection<PrendaView> getPrendas() {
		return prendas;
	}

	public void setPrendas(Collection<PrendaView> prendas) {
		this.prendas = prendas;
	}
	
}
