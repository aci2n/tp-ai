package implementacion;

import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;

public class ConjuntoPrenda extends Prenda{
	private float descuento;
	private Collection<Prenda> prendas;
	
	public ConjuntoPrenda(){
		
	}
	
	public ConjuntoPrenda(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		this.setCodigoDB(codigo);
		this.setNombreDB(nombre);
		this.descuento=descuento;
		this.prendas=prendas;
		this.setActivoDB(true);
		AdministradorPersistenciaPrenda.getInstancia().insert(this);
	}

	public float calcularPrecio() {
		float pre = 0;
		for(Prenda p : prendas){
			Prenda prenda = AdministradorPersistenciaPrenda.getInstancia().buscarPrenda(p.getCodigo());
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
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}

	public void construirDesdeDB(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		setCodigoDB(codigo);
		setNombreDB(nombre);
		this.descuento=descuento;
		this.prendas=prendas;
	}	
	
	
}
