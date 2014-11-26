/* 
 * hay que ver como hacemo para que cuando levante desde la base de datos 
 * no cree una instancia distinta a la que esta en el controlador
 * porque despues cuando actualizas ponele descontar stock se cambia
 * en la base de datos pero no en el controlador y queda medio mal
 * aunque ni se nota casi
 * otra: arreglar concurrentmodificationexception en los eliminar
 */

package controlador;

import implementacion.ConjuntoPrenda;
import implementacion.Factura;
import implementacion.ItemMaterial;
import implementacion.ItemPrenda;
import implementacion.Material;
import implementacion.Prenda;
import implementacion.PrendaConTemporada;
import implementacion.PrendaSinTemporada;
import implementacion.Proveedor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import view.ConjuntoPrendaView;
import view.FacturaView;
import view.ItemMaterialView;
import view.ItemPrendaView;
import view.MaterialView;
import view.PrendaConTemporadaView;
import view.PrendaSinTemporadaView;
import view.PrendaView;
import view.ProveedorView;

public class Controlador {
	private Collection<Material> materiales;
	private Collection<Proveedor> proveedores;
	private Collection<Prenda> prendas;
	private Collection<Factura> facturas;
	public static Controlador con;
	
	public Controlador() {
		proveedores = Proveedor.obtenerProveedores();
		materiales = Material.obtenerMateriales();
		prendas = Prenda.obtenerPrendas();
		facturas =  Factura.obtenerFacturas();
	}
		
	//SINGLETON

	public static Controlador getControlador(){
		if(con == null){
			con = new Controlador();
		}
		return con;
	}

	//ALTAS
	
	public void altaMaterial(MaterialView materialView){
		if (!existeMaterial(materialView.getCodigo())){
			Proveedor proveedor = obtenerProveedor(materialView.getCuit());
			if (proveedor != null){
				Material material = new Material(materialView.getCodigo(), materialView.getNombre(), materialView.getPuntoPedido(), proveedor, materialView.getCantidad(), materialView.getCosto());	
				material.addObserver(proveedor);
				materiales.add(material);
			}
		}
	}
	
	public void altaProveedor(ProveedorView proveedorView){
		if (!existeProveedor(proveedorView.getCuit())){
			Proveedor proveedor = new Proveedor(proveedorView.getNombre(), proveedorView.getCuit());
			proveedores.add(proveedor);
		}
	}
	
	public void altaPrendaConTemporada(PrendaConTemporadaView prendaConTemporadaView) {
		if (!existePrenda(prendaConTemporadaView.getCodigo())){
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaConTemporadaView.getMateriales()) {
				Material material = obtenerMaterial(itemView.getMaterial().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			Prenda p = new PrendaConTemporada(prendaConTemporadaView.getCodigo(), prendaConTemporadaView.getNombre(), prendaConTemporadaView.getTemporada(), prendaConTemporadaView.getPorcentajeVenta(), items);
			prendas.add(p);
		}
	}
	
	public void altaConjuntoPrenda(ConjuntoPrendaView conjuntoPrendaView) {
		if (!existePrenda(conjuntoPrendaView.getCodigo())){
			Collection<Prenda> prendas = new ArrayList<Prenda>();
			for (PrendaView prendaView : conjuntoPrendaView.getPrendas()) {
				Prenda prenda = obtenerPrenda(prendaView.getCodigo());
				if (prenda != null) {
					prendas.add(prenda);
				}
			}
			Prenda p = new ConjuntoPrenda(conjuntoPrendaView.getCodigo(), conjuntoPrendaView.getNombre(), conjuntoPrendaView.getDescuento(), prendas);
			this.prendas.add(p);
		}
	}
	
	public void altaPrendaSinTemporada(PrendaSinTemporadaView prendaSinTemporadaView){
		if (!existePrenda(prendaSinTemporadaView.getCodigo())){
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaSinTemporadaView.getMateriales()) {
				Material material = obtenerMaterial(itemView.getMaterial().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			Prenda p = new PrendaSinTemporada(prendaSinTemporadaView.getCodigo(), prendaSinTemporadaView.getNombre(), items);
			prendas.add(p);
		}
	}
	
	public void generarFactura(Collection<ItemPrendaView> prendasView){
		Collection<ItemPrenda> prendas = new ArrayList<ItemPrenda>();
		for (ItemPrendaView ipv : prendasView){
			if (obtenerPrenda(ipv.getPrenda().getCodigo()).hayStock(ipv.getCantidad())){
				ItemPrenda item = new ItemPrenda(obtenerPrenda(ipv.getPrenda().getCodigo()),ipv.getCantidad());
				item.getPrenda().descontarStock(ipv.getCantidad());
				prendas.add(item);			
			}
		}
		Factura factura = new Factura(prendas);
		this.facturas.add(factura);
	}
	
	//MODIFICAR
	
	public void modificarMaterial(MaterialView materialView) {
		if (existeMaterial(materialView.getCodigo())){
			Proveedor proveedor = obtenerProveedor(materialView.getCuit());
			if (proveedor != null){
				Material material = obtenerMaterial(materialView.getCodigo());
				material.setNombre(materialView.getNombre());
				material.setPuntoPedido(materialView.getPuntoPedido());
				material.setCantidad(materialView.getCantidad());
				material.setCosto(materialView.getCosto());
				material.setProveedor(proveedor);
				material.actualizar();
			}
		}
	}
	
	public void modificarProveedor(ProveedorView proveedorView) {
		if (existeProveedor(proveedorView.getCuit())){
			Proveedor proveedor = obtenerProveedor(proveedorView.getCuit());
			proveedor.setNombre(proveedorView.getNombre());
			proveedor.actualizar();
		}
	}
	
	public void ModificarPrendaConTemporada(PrendaConTemporadaView prendaConTemporadaView) {
		if (existePrenda(prendaConTemporadaView.getCodigo())){
			PrendaConTemporada prenda = (PrendaConTemporada) obtenerPrenda(prendaConTemporadaView.getCodigo());
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaConTemporadaView.getMateriales()) {
				Material material = obtenerMaterial(itemView.getMaterial().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			prenda.setMateriales(items);
			prenda.setNombre(prendaConTemporadaView.getNombre());
			prenda.setPorcentajeVenta(prendaConTemporadaView.getPorcentajeVenta());
			prenda.setTemporada(prendaConTemporadaView.getTemporada());
			prenda.actualizar();
		}
	}	
	
	public void ModificarPrendaSinTemporada(PrendaSinTemporadaView prendaSinTemporadaView) {
		if (existePrenda(prendaSinTemporadaView.getCodigo())){
			PrendaSinTemporada prenda = (PrendaSinTemporada) obtenerPrenda(prendaSinTemporadaView.getCodigo());
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaSinTemporadaView.getMateriales()) {
				Material material = obtenerMaterial(itemView.getMaterial().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			prenda.setMateriales(items);
			prenda.setNombre(prendaSinTemporadaView.getNombre());
			prenda.actualizar();
		}		
	}
	
	public void ModificarConjuntoPrenda(ConjuntoPrendaView conjuntoPrendaView) {
		if (existePrenda(conjuntoPrendaView.getCodigo())){
			ConjuntoPrenda prenda = (ConjuntoPrenda) obtenerPrenda(conjuntoPrendaView.getCodigo());
			Collection<Prenda> prendas = new ArrayList<Prenda>();
			for (PrendaView prendaView : conjuntoPrendaView.getPrendas()) {
				Prenda pren = obtenerPrenda(prendaView.getCodigo());
				if (pren != null) {
					prendas.add(pren);
				}
			}
			prenda.setPrendas(prendas);
			prenda.setNombre(conjuntoPrendaView.getNombre());
			prenda.setDescuento(conjuntoPrendaView.getDescuento());
			prenda.actualizar();
		}
	}
	
	//BAJAS
	
	public void eliminarMaterial(String codigo){
		Material m = obtenerMaterial(codigo);
	    if (m != null) {
	      Iterator<Prenda> iterP = prendas.iterator();
	      while (iterP.hasNext()) {
	        Prenda p = iterP.next();
	        if (p.tenesElMaterial(codigo)) {
	          eliminarPrenda(p.getCodigo());						
	        }
	      }
	      m.eliminar();
	      materiales.remove(m);
	    }
	}
	
	public void eliminarProveedor(String cuit) {
		Iterator<Proveedor> iterProv = proveedores.iterator();
		while (iterProv.hasNext()){
			Proveedor prov = iterProv.next();
			if (prov.sosElProveedor(cuit)){
				Iterator<Material> iter = materiales.iterator();
				while (iter.hasNext()){
					Material m = iter.next();
					if (m.tenesElProveedor(cuit)){
						iter.remove();
						eliminarMaterial(m.getCodigo());
					}
				}
				iterProv.remove();
				return;
			}
		}
	}

	public void eliminarPrenda(String codigo) {
		Prenda prenda = obtenerPrenda(codigo);
		if (prenda != null) {
			prendas.remove(prenda);
			prenda.eliminar();
			for (Prenda p : prendas) {
				if (p.tenesLaPrenda(codigo)) {
					prendas.remove(p);
					p.eliminar();
				}
			}
		}
	}
	
	/* 
	bajas que andan mas o menos
	
 	public void eliminarMaterial(String codigo){
		for (Material m : materiales){
			if (m.sosElMaterial(codigo)){
				m.eliminar();
				this.materiales.remove(m);
				for (Prenda p : prendas) //que borre todas las prendas que lo tienen
					if (p instanceof PrendaSimple) //fijarse si se les ocurre una forma mejor de hacer esto
						for (ItemMaterial im : ((PrendaSimple) p).getMateriales())
							if (im.getMaterial().sosElMaterial(codigo)){
								eliminarPrenda(p.getCodigo());
								return;
							}
				return;
			}
		}
	}
	
	public void eliminarProveedor(String cuit) {
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)){
				p.eliminar();
				this.proveedores.remove(p);
				for (Material m : materiales)
					if (m.getProveedor().sosElProveedor(cuit)){
						eliminarMaterial(m.getCodigo());
						return;
					}
				return;
			}
	}

	public void eliminarPrenda(String codigo) {
		for (Prenda pr : prendas)
			if (pr.sosLaPrenda(codigo)){
				pr.eliminar();
				this.prendas.remove(pr);
				for (Prenda p : prendas) //que borre todas las prendas que lo tienen
					if (p instanceof ConjuntoPrenda) //fijarse si se les ocurre una forma mejor de hacer esto
						for (Prenda pre : ((ConjuntoPrenda) p).getPrendas())
							if (pre.sosLaPrenda(codigo)){
								eliminarPrenda(p.getCodigo());
								return;
							}
				return;
			}	
	}
	*/
	
	//EXISTE
	
	public boolean existeMaterial(String codigo){
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)){
				return true;
			}
		if (Material.buscarMaterial(codigo)!=null)
			return true;
		return false;
	}
	
	public boolean existeProveedor(String cuit){
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)){
				return true;
			}
		if (Proveedor.buscarProveedor(cuit)!=null)
			return true;
		return false;
	}
	
	public boolean existePrenda(String codigo) {
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo)){
				return true;
			}
		if (Prenda.buscarPrenda(codigo)!=null)
			return true;
		return false;
	}	
	
	//OBTENER
	
	private Proveedor obtenerProveedor(String cuit){
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)==true)
				return p;
		return null;
	}
	
	public ProveedorView obtenerProveedorView(String cuit){
		Proveedor prov = obtenerProveedor(cuit);
		if (prov != null)
			return prov.generarProveedorView();
		return null;
	}
	
	private Material obtenerMaterial(String codigo) {
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)==true)
				return m;
		return null;
	}
	
	public MaterialView obtenerMaterialView(String codigo) {
		Material mat = obtenerMaterial(codigo);
		if (mat != null)
			return mat.generarMaterialView();
		return null;
	}
	
	private Prenda obtenerPrenda(String codigo) {
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo)==true)
				return p;
		return null;
	}
	
	public PrendaView obtenerPrendaView(String codigo) {
		Prenda p = obtenerPrenda(codigo);
		if (p != null)
			return p.generarPrendaView();
		return null;
	}
	
	//GETTERS
	
	public Collection<MaterialView> getMaterialesView() {
		Collection<MaterialView> materialesView = new ArrayList<MaterialView>();
		for (Material material : this.materiales) {
			materialesView.add(material.generarMaterialView());
		}
		return materialesView;
	}
	
	
	public Collection<ProveedorView> getProveedoresView() {
		Collection<ProveedorView> proveedoresView = new ArrayList<ProveedorView>();
		for (Proveedor proveedor : this.proveedores) {
			proveedoresView.add(proveedor.generarProveedorView());
		}
		return proveedoresView;
	}

	
	public Collection<PrendaView> getPrendasView() {
		Collection<PrendaView> prendasView = new ArrayList<PrendaView>();
		for (Prenda prenda : this.prendas) {
			prendasView.add(prenda.generarPrendaView());
		}
		return prendasView;
	}
	
	public int getNroFactura(){
		return Factura.getContador();
	}

	public Collection<FacturaView> getFacturasView() {
		Collection<FacturaView> facturasView = new ArrayList<FacturaView>();
		for (Factura factura : this.facturas)
			facturasView.add(factura.generarFacturaView());
		return facturasView;
	}
}
