package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaConjuntoPrenda;
import view.ConjuntoPrendaView;
import view.PrendaView;

public class ConjuntoPrenda extends Prenda{
	private float descuento;
	private Collection<Prenda> prendas;
	
	public ConjuntoPrenda(){
		
	}
	
	public ConjuntoPrenda(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descuento = descuento;
		this.prendas = prendas;
		this.activo = true;
		AdministradorPersistenciaConjuntoPrenda.getInstancia().insert(this);
	}
	
	public void actualizar(){
		AdministradorPersistenciaConjuntoPrenda.getInstancia().update(this);
	}
	
	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaConjuntoPrenda.getInstancia().delete(this);
	}

	public float calcularPrecio() {
		float pre = 0;
		for(Prenda p : prendas){
			Prenda prenda = AdministradorPersistenciaConjuntoPrenda.getInstancia().buscarPrenda(p.getCodigo());
			pre = pre + prenda.calcularPrecio();
		}
		float descuento = (pre * this.descuento) / 100;
		return (pre - descuento);
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

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public PrendaView generarPrendaView() {
		Collection<PrendaView> prendasView = new ArrayList<PrendaView>();
		for (Prenda prenda : this.prendas){
			prendasView.add(prenda.generarPrendaView());
		}
		return new ConjuntoPrendaView(getCodigo(), getNombre(), this.descuento, prendasView);
	}

	public void descontarStock(float cantidad) {
		for (Prenda p : prendas)
			p.descontarStock(cantidad);
	}

	public boolean tenesElMaterial(String codigo) {
		for (Prenda p : prendas)
			if (p.tenesElMaterial(codigo))
				return true;
		return false;
	}

	public boolean tenesLaPrenda(String codigo) {
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo) || p.tenesLaPrenda(codigo))
				return true;
		return false;
	}	
	
	public boolean hayStock(float cantidad){
		for (Prenda p : prendas)
			if (!p.hayStock(cantidad))
				return false;
		return true;
	}
}
