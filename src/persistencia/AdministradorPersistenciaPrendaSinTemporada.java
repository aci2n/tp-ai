package persistencia;

import implementacion.ItemMaterial;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class AdministradorPersistenciaPrendaSinTemporada extends AdministradorPersistencia{

	private static AdministradorPersistenciaPrendaSinTemporada instancia;
	
	private AdministradorPersistenciaPrendaSinTemporada() {
		
	}
	
	public static AdministradorPersistenciaPrendaSinTemporada getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaPrendaSinTemporada();
		}
		return instancia;
	}

	public void insert(Object o) {
		Connection con = Conexion.connect();
		PrendaSinTemporada p = (PrendaSinTemporada)o;
		try{
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,activo,tipo_prenda) VALUES (?,?,?,?)");
			ps.setString(1, p.getCodigo());
			ps.setString(2,p.getNombre());
			ps.setInt(3,1);
			ps.setString(4,"sintemporada");
			
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
		PrendaSinTemporada p = (PrendaSinTemporada)o;
		try{
			
			PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set nombre = ? where codigo = ?");
			ps.setString(1,p.getNombre());
			ps.setString(2, p.getCodigo());
			
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
		PrendaSinTemporada p = (PrendaSinTemporada)o;
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
	
	
	
	public Collection<PrendaSinTemporada> obtenerPrendasSinTemporada(){
		Connection con = Conexion.connect();
		Collection<PrendaSinTemporada> prendasSinTemporada = new ArrayList<PrendaSinTemporada>();
		Collection <ItemMaterial> itemsMaterial = new ArrayList<ItemMaterial>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where tipo_prenda = 'sintemporada'");
			ResultSet res = ps.executeQuery();
			while (res.next()){
				PrendaSinTemporada prendaSinTemporada = new PrendaSinTemporada();
				PreparedStatement psObtenerMateriales = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				psObtenerMateriales.setString(1, res.getString("codigo"));
				ResultSet resMateriales = psObtenerMateriales.executeQuery();
				while (resMateriales.next()){
					ItemMaterial item = new ItemMaterial(AdministradorPersistenciaMaterial.getInstance().buscarMaterial(resMateriales.getString("codigo_material")), resMateriales.getFloat("cantidad"));
					itemsMaterial.add(item);
				}
				prendaSinTemporada.setCodigo(res.getString("codigo"));
				prendaSinTemporada.setNombre(res.getString("nombre"));
				prendaSinTemporada.setMateriales(itemsMaterial);
				prendaSinTemporada.setActivo(res.getBoolean("activo"));
				prendasSinTemporada.add(prendaSinTemporada);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return prendasSinTemporada;
	}

	
	public PrendaSinTemporada buscarPrenda(String codigo){
		Connection con = Conexion.connect();
		Collection <ItemMaterial> itemsMaterial = new ArrayList<ItemMaterial>();
		PrendaSinTemporada prendaSinTemporada = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where activo = 1 AND tipo_prenda = 'sintemporada' AND codigo = ?");
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while (res.next()){
				prendaSinTemporada = new PrendaSinTemporada();
				PreparedStatement psObtenerMateriales = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				psObtenerMateriales.setString(1, res.getString("codigo"));
				ResultSet resMateriales = psObtenerMateriales.executeQuery();
				while (resMateriales.next()){
					ItemMaterial item = new ItemMaterial(AdministradorPersistenciaMaterial.getInstance().buscarMaterial(resMateriales.getString("codigo_material")), resMateriales.getFloat("cantidad"));
					itemsMaterial.add(item);
				}
				prendaSinTemporada.setCodigo(res.getString("codigo"));
				prendaSinTemporada.setActivo(res.getBoolean("activo"));
				prendaSinTemporada.setNombre(res.getString("nombre"));
				prendaSinTemporada.setMateriales(itemsMaterial);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return prendaSinTemporada;
	}
}