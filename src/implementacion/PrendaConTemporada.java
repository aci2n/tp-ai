package implementacion;

import java.util.ArrayList;
import java.util.Collection;

import persistencia.AdministradorPersistenciaPrendaConTemporada;
import view.ItemMaterialView;
import view.PrendaConTemporadaView;
import view.PrendaView;

public class PrendaConTemporada extends PrendaSimple{
	private String temporada;
	private float porcentajeVenta;
	
	public PrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> materiales) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		this.materiales = materiales;
		this.activo = true;
		AdministradorPersistenciaPrendaConTemporada.getInstancia().insert(this);
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

	public void actualizar(){
		AdministradorPersistenciaPrendaConTemporada.getInstancia().update(this);
	}
	
	public void eliminar(){
		this.activo=false;
		AdministradorPersistenciaPrendaConTemporada.getInstancia().delete(this);
	}
	
	public float getPorcentajeVenta() {
		return this.porcentajeVenta;
	}


	public String getTemporada() {
		return this.temporada;
	}


	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public void setPorcentajeVenta(float porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
	}

	public PrendaView generarPrendaView() {
		Collection<ItemMaterialView> itemsView = new ArrayList<ItemMaterialView>();
		for (ItemMaterial item : getMateriales()) {
			itemsView.add(item.generarItemMaterialView());
		}
		return new PrendaConTemporadaView(getCodigo(), getNombre(), isActivo(),  itemsView, this.temporada, this.porcentajeVenta);
	}
}
