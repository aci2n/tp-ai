package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaPrenda;
import view.ItemMaterialView;
import view.PrendaConTemporadaView;
import view.PrendaView;

public class PrendaConTemporada extends PrendaSimple{
	private String temporada;
	private float porcentajeVenta;
	
	public PrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> items) {
		setCodigoDB(codigo); //fijarse si esto no jode con la persistencia despues
		setNombreDB(nombre);
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		setMaterialesDB(items);
		setActivoDB(true);
		AdministradorPersistenciaPrenda.getInstancia().insert(this);
	}


	public PrendaConTemporada() {
	}


	public float calcularPrecio(){
		float precio = 0;
		Collection<ItemMaterial> materiales = super.getMateriales();
		for(ItemMaterial im : materiales){
			precio = precio + (im.getMaterial().getCosto())*(im.getCantidad());
		}
		float porcentaje = (precio * this.porcentajeVenta) / 100; 
		return (precio + porcentaje);
	}


	public float getPorcentajeVenta() {
		return this.porcentajeVenta;
	}


	public String getTemporada() {
		return this.temporada;
	}


	public void setTemporada(String temporada) {
		this.temporada = temporada;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}


	public void setPorcentajeVenta(float porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
		AdministradorPersistenciaPrenda.getInstancia().update(this);
	}

	public void construirDesdeDB(String codigo, String nombre, String temporada,float porcentajeVenta, Collection<ItemMaterial> items) {
		setNombreDB(nombre);
		setCodigoDB(codigo);
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		setMaterialesDB(items);
	}

	public PrendaView generarPrendaView() {
		Collection<ItemMaterialView> itemsView = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : getMateriales()) {
			itemsView.add(item.generarItemMaterialView());
		}
		return new PrendaConTemporadaView(getCodigo(), getNombre(), isActivo(), calcularPrecio(), itemsView, this.temporada, this.porcentajeVenta);
	}

}
