package persistencia;

import implementacion.ItemMaterial;
import implementacion.Material;
import implementacion.PrendaConTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controlador.Controlador;


public class AdministradorPersistenciaPrendaConTemporada extends AdministradorPersistencia{

	private static AdministradorPersistenciaPrendaConTemporada instancia;
	
	private AdministradorPersistenciaPrendaConTemporada() {
		
	}
	
	public static AdministradorPersistenciaPrendaConTemporada getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaPrendaConTemporada();
		}
		return instancia;
	}

	public void insert(Object o) {
		Connection con = Conexion.connect();
		PrendaConTemporada p = (PrendaConTemporada)o;
		try{
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,temporada,porcentaje_venta,activo,tipo_prenda) VALUES (?,?,?,?,?,?)");
			ps.setString(1, p.getCodigo());
			ps.setString(2,p.getNombre());
			ps.setString(3, p.getTemporada());
			ps.setFloat(4, p.getPorcentajeVenta());
			ps.setInt(5,1);
			ps.setString(6,"contemporada");
			
			ps.execute();
			
			for (ItemMaterial i : p.getMateriales()){
				PreparedStatement psItemMaterial = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?)");
				psItemMaterial.setString(1, i.getMaterial().getCodigo());
				psItemMaterial.setString(2, p.getCodigo());
				psItemMaterial.setFloat(3, i.getCantidad());
				psItemMaterial.execute();
			}
			
			con.close();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void update(Object o) {
		Connection con = Conexion.connect();
		PrendaConTemporada p = (PrendaConTemporada)o;
		try{
			
			PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set nombre = ?, temporada = ?, porcentaje_venta = ? where codigo = ?");
			ps.setString(1,p.getNombre());
			ps.setString(2, p.getTemporada());
			ps.setFloat(3, p.getPorcentajeVenta());
			ps.setString(4, p.getCodigo());
			
			ps.execute();
			
			ps = con.prepareStatement("DELETE FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
			ps.setString(1,p.getCodigo());	
			
			ps.execute();
			
			ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?,?)");
			for (ItemMaterial i : p.getMateriales()){
				PreparedStatement psItemMaterial = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?)");
				psItemMaterial.setString(1, i.getMaterial().getCodigo());
				psItemMaterial.setString(2, p.getCodigo());
				psItemMaterial.setFloat(3, i.getCantidad());
				psItemMaterial.execute();
			}
			
			con.close();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		Connection con = Conexion.connect();
		PrendaConTemporada p = (PrendaConTemporada)o;
		try{
			PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set activo = 0 where codigo = ?");
			ps.setString(1, p.getCodigo());
			
			ps.execute();
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	public Collection<PrendaConTemporada> obtenerPrendasConTemporada(){
		Connection con = Conexion.connect();
		Collection<PrendaConTemporada> prendasConTemporada = new ArrayList<PrendaConTemporada>();
		Collection <ItemMaterial> itemsMaterial = new ArrayList<ItemMaterial>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where activo = 1 AND tipo_prenda = 'contemporada'");
			ResultSet res = ps.executeQuery();
			while (res.next()){
				PrendaConTemporada prendaConTemporada = new PrendaConTemporada();
				PreparedStatement psObtenerMateriales = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				psObtenerMateriales.setString(1, res.getString("codigo"));
				ResultSet resMateriales = psObtenerMateriales.executeQuery();
				while (resMateriales.next()){
					ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(resMateriales.getString("codigo_material")), resMateriales.getFloat("cantidad"));
					itemsMaterial.add(item);
				}
				prendaConTemporada.setCodigo(res.getString("codigo"));
				prendaConTemporada.setNombre(res.getString("nombre"));
				prendaConTemporada.setActivo(res.getBoolean("activo"));
				prendaConTemporada.setTemporada(res.getString("temporada"));
				prendaConTemporada.setPorcentajeVenta(res.getFloat("porcentaje_venta"));
				prendaConTemporada.setMateriales(itemsMaterial);
				prendasConTemporada.add(prendaConTemporada);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return prendasConTemporada;
	}

	
	public PrendaConTemporada buscarPrenda(String codigo){
		Connection con = Conexion.connect();
		Collection <ItemMaterial> itemsMaterial = new ArrayList<ItemMaterial>();
		PrendaConTemporada prendaConTemporada = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where tipo_prenda = 'contemporada' AND codigo = ?");
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while (res.next()){
				prendaConTemporada = new PrendaConTemporada();
				PreparedStatement psObtenerMateriales = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				psObtenerMateriales.setString(1, res.getString("codigo"));
				ResultSet resMateriales = psObtenerMateriales.executeQuery();
				while (resMateriales.next()){
					ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(resMateriales.getString("codigo_material")), resMateriales.getFloat("cantidad"));
					itemsMaterial.add(item);
				}
				prendaConTemporada.setCodigo(res.getString("codigo"));
				prendaConTemporada.setNombre(res.getString("nombre"));
				prendaConTemporada.setActivo(res.getBoolean("activo"));
				prendaConTemporada.setTemporada(res.getString("temporada"));
				prendaConTemporada.setPorcentajeVenta(res.getFloat("porcentaje_venta"));
				prendaConTemporada.setMateriales(itemsMaterial);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return prendaConTemporada;
	}
	
	public PrendaConTemporada buscarPrendaFactura(String codigo){
		Connection con = Conexion.connect();
		Collection <ItemMaterial> itemsMaterial = new ArrayList<ItemMaterial>();
		PrendaConTemporada prendaConTemporada = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where tipo_prenda = 'contemporada' AND codigo = ?");
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while (res.next()){
				prendaConTemporada = new PrendaConTemporada();
				PreparedStatement psObtenerMateriales = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				psObtenerMateriales.setString(1, res.getString("codigo"));
				ResultSet resMateriales = psObtenerMateriales.executeQuery();
				while (resMateriales.next()){
					ItemMaterial item = new ItemMaterial(Material.buscarMaterial(resMateriales.getString("codigo_material")), resMateriales.getFloat("cantidad"));
					itemsMaterial.add(item);
				}
				prendaConTemporada.setCodigo(res.getString("codigo"));
				prendaConTemporada.setNombre(res.getString("nombre"));
				prendaConTemporada.setActivo(res.getBoolean("activo"));
				prendaConTemporada.setTemporada(res.getString("temporada"));
				prendaConTemporada.setPorcentajeVenta(res.getFloat("porcentaje_venta"));
				prendaConTemporada.setMateriales(itemsMaterial);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return prendaConTemporada;
	}
}