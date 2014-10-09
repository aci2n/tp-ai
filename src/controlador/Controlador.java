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
	
	public void altaMaterial(String codigo, String nombre, float puntoPedido, String cuit, float cantidad, float costo){
		if (!existeMaterial(codigo)){
			Proveedor proveedor = obtenerProveedor(cuit);
			if (proveedor!=null){
				Material material = new Material(codigo, nombre, puntoPedido, proveedor, cantidad, costo);	
				materiales.add(material);
				JOptionPane.showMessageDialog(null, "Material agregado correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe un material con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);			
	}
	
	public void altaProveedor(String nombre, String cuit){
		if (!existeProveedor(cuit)){
			Proveedor proveedor = new Proveedor(nombre, cuit);
			proveedores.add(proveedor);
			JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe un proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
	}
	
	public void altaPrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> items) {
		if (!existePrenda(codigo)){
			Prenda p = new PrendaConTemporada(codigo,nombre, temporada, porcentajeVenta, items);
			prendas.add(p);
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
	}
	
	public void altaConjuntoPrenda(String codigo, String nombre, float descuento, Collection<Prenda> prendas) {
		if (!existePrenda(codigo)){
			Prenda p = new ConjuntoPrenda(codigo,nombre, descuento, prendas);
			this.prendas.add(p);
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);					
	}
	
	public void altaPrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> items){
		if (!existePrenda(codigo)){
			Prenda p = new PrendaSinTemporada(codigo,nombre, items);
			prendas.add(p);
			JOptionPane.showMessageDialog(null, "Prenda agregada correctamente.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"Ya existe una prenda con el código ingresado.","Error",JOptionPane.ERROR_MESSAGE);	
	}
	
	
	//MODIFICAR
	
	public void modificarMaterial(String codigo, String nombre, float puntoPedido, String cuit, float cantidad, float costo){
		if (existeMaterial(codigo)==true){
			Proveedor proveedor = obtenerProveedor(cuit);
			if (proveedor != null){
				obtenerMaterial(codigo).setNombre(nombre);
				obtenerMaterial(codigo).setPuntoPedido(puntoPedido);
				obtenerMaterial(codigo).setCantidad(cantidad);
				obtenerMaterial(codigo).setCosto(costo);
				obtenerMaterial(codigo).setProveedor(proveedor);
				JOptionPane.showMessageDialog(null, "Material modificado.","OK",JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT ingresado.","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe material con el código seleccionado.","Error",JOptionPane.ERROR_MESSAGE);	//no se tendría que llegar nunca aca pero bue
	}
	
	public void modificarProveedor(String cuit, String nombre) {
		if (existeProveedor(cuit)==true){
			obtenerProveedor(cuit).setNombre(nombre);
			JOptionPane.showMessageDialog(null, "Proveedor modificado.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe proveedor con el CUIT seleccionado.","Error",JOptionPane.ERROR_MESSAGE);	//no se tendría que llegar nunca aca pero bue
	}
	
	public void ModificarPrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> itemMateriales) {
		if (existePrenda(codigo)==true){
			PrendaConTemporada prenda = (PrendaConTemporada)obtenerPrenda(codigo);
			prenda.setCodigo(codigo);
			prenda.setMateriales(itemMateriales);
			prenda.setNombre(nombre);
			prenda.setPorcentajeVenta(porcentajeVenta);
			prenda.setTemporada(temporada);
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe la prenda.","Error",JOptionPane.ERROR_MESSAGE);		
	}	
	
	public void ModificarPrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> itemMateriales) {
		if (existePrenda(codigo)==true){
			PrendaSinTemporada prenda = (PrendaSinTemporada)obtenerPrenda(codigo);
			prenda.setCodigo(codigo);
			prenda.setMateriales(itemMateriales);
			prenda.setNombre(nombre);
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe la prenda.","Error",JOptionPane.ERROR_MESSAGE);		
		
	}
	
	public void ModificarConjuntoPrenda(String codigo, String nombre,	float descuento, Collection<Prenda> prendas) {
		if (existePrenda(codigo)==true){
			ConjuntoPrenda prenda = (ConjuntoPrenda)obtenerPrenda(codigo);
			prenda.setCodigo(codigo);
			prenda.setPrendas(prendas);
			prenda.setNombre(nombre);
			prenda.setDescuento(descuento);
			JOptionPane.showMessageDialog(null, "Prenda modificada.","OK",JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"No existe el conjunto.","Error",JOptionPane.ERROR_MESSAGE);			
	}
	
	//BAJAS
	
	public void eliminarMaterial(String codigo){
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)==true){
				m.eliminar();
				JOptionPane.showMessageDialog(null,"Material eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		for(Material m : Material.obtenerMateriales())
			if (m.sosElMaterial(codigo)==true){
				m.eliminar();
				JOptionPane.showMessageDialog(null,"Material eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}
	
	public void eliminarProveedor(String cuit) {
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)==true){
				p.eliminar();
				JOptionPane.showMessageDialog(null,"Proveedor eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}		
		for(Proveedor p : Proveedor.obtenerProveedores())
			if (p.sosElProveedor(cuit)==true){
				p.eliminar();
				JOptionPane.showMessageDialog(null,"Proveedor eliminado.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}

	public void eliminarPrenda(String codigo) {
		for (Prenda pr : prendas)
			if (pr.sosLaPrenda(codigo)==true){
				pr.eliminar();
				JOptionPane.showMessageDialog(null,"Prenda eliminada.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}	
		for(Prenda pr : Prenda.obtenerPrendas())
			if (pr.sosLaPrenda(codigo)==true){
				pr.eliminar();
				JOptionPane.showMessageDialog(null,"Prenda eliminada.","OK",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	}
	
	//EXISTE
	
	public boolean existeMaterial(String codigo){
		for (Material m : materiales)
			if (m.sosElMaterial(codigo)==true){
				return true;
			}
		if (Material.buscarMaterial(codigo)!=null)
			return true;
		return false;
	}
	
	public boolean existeProveedor(String cuit){
		for (Proveedor p : proveedores)
			if (p.sosElProveedor(cuit)==true){
				return true;
			}
		if (Proveedor.buscarProveedor(cuit)!=null)
			return true;
		return false;
	}
	
	public boolean existePrenda(String codigo) {
		for (Prenda p : prendas)
			if (p.sosLaPrenda(codigo)==true){
				return true;
			}
		if (Prenda.buscarPrenda(codigo)!=null)
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

	public Collection<Material> getMateriales() {
			return Material.obtenerMateriales();
	}
	
	public Collection<Proveedor> getProveedores() {
		
			return Proveedor.obtenerProveedores();
	}

	public Collection<Prenda> getPrendas() {

			return Prenda.obtenerPrendas();
	}
}
