package controlador;

import implementacion.ConjuntoPrenda;
import implementacion.ItemMaterial;
import implementacion.Material;
import implementacion.Prenda;
import implementacion.PrendaConTemporada;
import implementacion.PrendaSinTemporada;
import implementacion.Proveedor;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import view.ConjuntoPrendaView;
import view.ItemMaterialView;
import view.MaterialView;
import view.PrendaConTemporadaView;
import view.PrendaSimpleView;
import view.PrendaView;
import view.ProveedorView;

public class Controlador {
	private Collection<Material> materiales;
	private Collection<Proveedor> proveedores;
	private Collection<Prenda> prendas;
	public static Controlador con;
	
	public Controlador() {
		prendas = new ArrayList<Prenda>();
		materiales = new ArrayList<Material>();
		proveedores = new ArrayList<Proveedor>();
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
			Proveedor proveedor = obtenerProveedor(materialView.getProveedorView().getCuit());
			if (proveedor != null){
				Material material = new Material(materialView.getCodigo(), materialView.getNombre(), materialView.getPuntoPedido(), proveedor, materialView.getCantidad(), materialView.getCosto());	
				materiales.add(material);
				JOptionPane.showMessageDialog(null, "Material agregado correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe un material con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);			
	}
	
	public void altaProveedor(ProveedorView proveedorView){
		if (!existeProveedor(proveedorView.getCuit())){
			Proveedor proveedor = new Proveedor(proveedorView.getNombre(), proveedorView.getCuit());
			proveedores.add(proveedor);
			JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe un proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
	}
	
	public void altaPrendaConTemporada(PrendaConTemporadaView prendaConTemporadaView) {
		if (!existePrenda(prendaConTemporadaView.getCodigo())){
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaConTemporadaView.getMaterialesView()) {
				Material material = obtenerMaterial(itemView.getMaterialView().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			Prenda p = new PrendaConTemporada(prendaConTemporadaView.getCodigo(), prendaConTemporadaView.getNombre(), prendaConTemporadaView.getTemporada(), prendaConTemporadaView.getPorcentajeVenta(), items);
			prendas.add(p);
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
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
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
	}
	
	public void altaPrendaSinTemporada(PrendaSimpleView prendaSinTemporadaView){
		if (!existePrenda(prendaSinTemporadaView.getCodigo())){
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaSinTemporadaView.getMaterialesView()) {
				Material material = obtenerMaterial(itemView.getMaterialView().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			Prenda p = new PrendaSinTemporada(prendaSinTemporadaView.getCodigo(), prendaSinTemporadaView.getNombre(), items);
			prendas.add(p);
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);	
	}
	
	
	//MODIFICAR
	
	public void modificarMaterial(MaterialView materialView) {
		if (existeMaterial(materialView.getCodigo())){
			Proveedor proveedor = obtenerProveedor(materialView.getProveedorView().getCuit());
			if (proveedor != null){
				Material material = obtenerMaterial(materialView.getCodigo());
				material.setNombre(materialView.getNombre());
				material.setPuntoPedido(materialView.getPuntoPedido());
				material.setCantidad(materialView.getCantidad());
				material.setCosto(materialView.getCosto());
				material.setProveedor(proveedor);
				JOptionPane.showMessageDialog(null, "Material modificado.","OK",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe material con el código seleccionado.","Error",JOptionPane.ERROR_MESSAGE);	//no se tendría que llegar nunca aca pero bue
	}
	
	public void modificarProveedor(ProveedorView proveedorView) {
		if (existeProveedor(proveedorView.getCuit())){
			Proveedor proveedor = obtenerProveedor(proveedorView.getCuit());
			proveedor.setNombre(proveedorView.getNombre());
			proveedor.setActivo(proveedorView.isActivo());
			JOptionPane.showMessageDialog(null, "Proveedor modificado.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT seleccionado.","Error",JOptionPane.ERROR_MESSAGE);	//no se tendría que llegar nunca aca pero bue
	}
	
	public void ModificarPrendaConTemporada(PrendaConTemporadaView prendaConTemporadaView) {
		if (existePrenda(prendaConTemporadaView.getCodigo())){
			PrendaConTemporada prenda = (PrendaConTemporada) obtenerPrenda(prendaConTemporadaView.getCodigo());
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaConTemporadaView.getMaterialesView()) {
				Material material = obtenerMaterial(itemView.getMaterialView().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			prenda.setMateriales(items);
			prenda.setNombre(prendaConTemporadaView.getNombre());
			prenda.setPorcentajeVenta(prendaConTemporadaView.getPorcentajeVenta());
			prenda.setTemporada(prendaConTemporadaView.getTemporada());
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe la prenda.","Error",JOptionPane.ERROR_MESSAGE);		
	}	
	
	public void ModificarPrendaSinTemporada(PrendaSimpleView prendaSinTemporadaView) {
		if (existePrenda(prendaSinTemporadaView.getCodigo())){
			PrendaSinTemporada prenda = (PrendaSinTemporada) obtenerPrenda(prendaSinTemporadaView.getCodigo());
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
			for (ItemMaterialView itemView : prendaSinTemporadaView.getMaterialesView()) {
				Material material = obtenerMaterial(itemView.getMaterialView().getCodigo());
				if (material != null) {
					items.add(new ItemMaterial(material, itemView.getCantidad()));
				}
			}
			prenda.setMateriales(items);
			prenda.setNombre(prendaSinTemporadaView.getNombre());
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe la prenda.","Error",JOptionPane.ERROR_MESSAGE);		
		
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
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe el conjunto.","Error",JOptionPane.ERROR_MESSAGE);			
	}
	
	//BAJAS
	
	public void eliminarMaterial(MaterialView materialView){
		for (Material m : materiales)
			if (m.sosElMaterial(materialView.getCodigo())){
				m.eliminar();
				JOptionPane.showMessageDialog(null,"Material eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		for(Material m : Material.obtenerMateriales())
			if (m.sosElMaterial(materialView.getCodigo())){
				m.eliminar();
				JOptionPane.showMessageDialog(null,"Material eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}
	
	public void eliminarProveedor(ProveedorView proveedorView) {
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(proveedorView.getCuit())){
				p.eliminar();
				JOptionPane.showMessageDialog(null,"Proveedor eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}		
		for(Proveedor p : Proveedor.obtenerProveedores())
			if (p.sosElProveedor(proveedorView.getCuit())){
				p.eliminar();
				JOptionPane.showMessageDialog(null,"Proveedor eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}

	public void eliminarPrenda(PrendaView prendaView) {
		for (Prenda pr : prendas)
			if (pr.sosLaPrenda(prendaView.getCodigo())){
				pr.eliminar();
				JOptionPane.showMessageDialog(null,"Prenda eliminada.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}	
		for(Prenda pr : Prenda.obtenerPrendas())
			if (pr.sosLaPrenda(prendaView.getCodigo())){
				pr.eliminar();
				JOptionPane.showMessageDialog(null,"Prenda eliminada.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}
	
	//EXISTE
	
	public boolean existeMaterial(String codigo){
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)){
				return true;
			}
		if (Material.buscarMaterial(codigo) != null)
			return true;
		return false;
	}
	
	public boolean existeProveedor(String cuit){
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)){
				return true;
			}
		if (Proveedor.buscarProveedor(cuit) != null)
			return true;
		return false;
	}
	
	public boolean existePrenda(String codigo) {
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo)){
				return true;
			}
		if (Prenda.buscarPrenda(codigo) != null)
			return true;
		return false;
	}	
	
	//OBTENER
	
	public Proveedor obtenerProveedor(String cuit){
		Proveedor prov = null;
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)==true)
				prov = p;
		if (prov == null){
			prov = Proveedor.buscarProveedor(cuit);
			this.proveedores.add(prov);
		}
		return prov;
	}
	
	public ProveedorView obtenerProveedorView(String cuit){
		Proveedor prov = obtenerProveedor(cuit);
		if (prov != null) {
			return prov.generarProveedorView();
		}
		return null;
	}
	
	public Material obtenerMaterial(String codigo) {
		Material mat = null;
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)==true){
				mat = m;
			}
		if (mat == null){
			mat = Material.buscarMaterial(codigo);
			this.materiales.add(mat);
		}
		return mat;
	}
	
	public MaterialView obtenerMaterialView(String codigo) {
		Material mat = obtenerMaterial(codigo);
		if (mat != null) {
			return mat.generarMaterialView();
		}
		return null;
	}
	
	public Prenda obtenerPrenda(String codigo) {
		Prenda pre = null;
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo)==true){
				pre = p;
			}
		if (pre == null){
			pre = Prenda.buscarPrenda(codigo);
			this.prendas.add(pre);
		}
		return pre;
	}
	
	//GETTERS

	private Collection<Material> getMateriales() {
		return Material.obtenerMateriales();
	}
	
	public Collection<MaterialView> getMaterialesView() {
		Collection<MaterialView> materialesView = new ArrayList<MaterialView>();
		for (Material material : getMateriales()) {
			materialesView.add(material.generarMaterialView());
		}
		return materialesView;
	}
	
	private Collection<Proveedor> getProveedores() {
		return Proveedor.obtenerProveedores();
	}
	
	public Collection<ProveedorView> getProveedoresView() {
		Collection<ProveedorView> proveedoresView = new ArrayList<ProveedorView>();
		for (Proveedor proveedor : getProveedores()) {
			proveedoresView.add(proveedor.generarProveedorView());
		}
		return proveedoresView;
	}

	private Collection<Prenda> getPrendas() {
		return Prenda.obtenerPrendas();
	}
	
	public Collection<PrendaView> getPrendasView() {
		Collection<PrendaView> prendasView = new ArrayList<PrendaView>();
		for (Prenda prenda : getPrendas()) {
			prendasView.add(prenda.generarPrendaView());
		}
		return prendasView;
	}
}
