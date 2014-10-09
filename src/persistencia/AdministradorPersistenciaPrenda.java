package persistencia;

import implementacion.ConjuntoPrenda;
import implementacion.ItemMaterial;
import implementacion.Prenda;
import implementacion.PrendaConTemporada;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controlador.Controlador;


public class AdministradorPersistenciaPrenda extends AdministradorPersistencia{

	private static AdministradorPersistenciaPrenda instancia;
	
	private AdministradorPersistenciaPrenda() {
		
	}
	
	public static AdministradorPersistenciaPrenda getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaPrenda();
		}
		return instancia;
	}

	public void insert(Object o) {
		Connection con = Conexion.connect();
		if (o instanceof PrendaConTemporada){
			try{
				PrendaConTemporada p = (PrendaConTemporada)o;
				
				PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,temporada,porcentaje_venta,activo) VALUES (?,?,?,?,?)");
				ps.setString(1, p.getCodigo());
				ps.setString(2,p.getNombre());
				ps.setString(3, p.getTemporada());
				ps.setFloat(4, p.getPorcentajeVenta());
				ps.setInt(5,1);
				
				ps.execute();
				
				for (ItemMaterial i : p.getMateriales()){
					insertarItemMaterial(i, p.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		if (o instanceof PrendaSinTemporada){
			try{
				PrendaSinTemporada p = (PrendaSinTemporada)o;
				
				PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,activo) VALUES (?,?,?)");
				ps.setString(1, p.getCodigo());
				ps.setString(2,p.getNombre());
				ps.setInt(3,1);
				
				ps.execute();
				
				ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?,?)");
				for (ItemMaterial i : p.getMateriales()){
					insertarItemMaterial(i, p.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		if (o instanceof ConjuntoPrenda){
			try{
				ConjuntoPrenda c = (ConjuntoPrenda)o;
				
				PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,descuento,activo) VALUES (?,?,?,?)");
				ps.setString(1, c.getCodigo());
				ps.setString(2,c.getNombre());
				ps.setFloat(3, c.getDescuento());
				ps.setInt(4,1);
				
				ps.execute();
				
				for (Prenda p : c.getPrendas()){
					insertarPrenda(p, c.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update(Object o) {
		Connection con = Conexion.connect();
		if (o instanceof PrendaConTemporada){
			try{
				PrendaConTemporada p = (PrendaConTemporada)o;
				
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
					insertarItemMaterial(i, p.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		if (o instanceof PrendaSinTemporada){
			try{
				PrendaSinTemporada p = (PrendaSinTemporada)o;
				
				PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set nombre = ? where codigo = ?");
				ps.setString(1,p.getNombre());
				ps.setString(2, p.getCodigo());
				
				ps.execute();
				
				ps = con.prepareStatement("DELETE FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
				ps.setString(1,p.getCodigo());	
				
				ps.execute();
				
				ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?,?)");
				for (ItemMaterial i : p.getMateriales()){
					insertarItemMaterial(i, p.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}			
		}
		if (o instanceof ConjuntoPrenda){
			try{
				ConjuntoPrenda c = (ConjuntoPrenda)o;
				
				PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set nombre = ?, descuento = ? where codigo = ?");
				ps.setString(1, c.getNombre());
				ps.setFloat(2,c.getDescuento());
				ps.setString(3, c.getCodigo());
				
				ps.execute();
				
				ps = con.prepareStatement("DELETE FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas where codigo_conjunto = ?");
				ps.setString(1,c.getCodigo());	
				
				ps.execute();
				
				ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas VALUES (?,?,?)");
				for (Prenda p : c.getPrendas()){
					insertarPrenda(p, c.getCodigo());
				}
				
				con.close();
				
			}
			catch (SQLException e){
				e.printStackTrace();
			}			
		}
	}

	public void delete(Object o) {
		Connection con = Conexion.connect();
		Prenda p = (Prenda)o;
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
	
	
	
	public Collection<Prenda> obtenerPrendas(){
		Connection con = Conexion.connect();
		Collection<Prenda> prendas = new ArrayList<Prenda>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where activo = 1");
			ResultSet res = ps.executeQuery();
			while (res.next()){
				res.getString("temporada");
				if (!res.wasNull()){ //es prendacontemporada
					PrendaConTemporada prenda = new PrendaConTemporada();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
					ps2.setString(1, res.getString("codigo"));
					Collection <ItemMaterial> items = new ArrayList<ItemMaterial>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(res2.getString("codigo_material")), res2.getFloat("cantidad"));
						items.add(item);
					}
					prenda.construirDesdeDB(res.getString("codigo"), res.getString("nombre"), res.getString("temporada"), res.getFloat("porcentaje_venta"), items);
					prendas.add(prenda);
					continue;
				}
				res.getFloat("descuento");
				if (!res.wasNull()){ //es conjuntoprenda
					ConjuntoPrenda conj = new ConjuntoPrenda();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas where codigo_conjunto = ?");
					ps2.setString(1, res.getString("codigo"));
					Collection<Prenda> prendasC = new ArrayList<Prenda>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						Prenda pr = Controlador.getControlador().obtenerPrenda(res2.getString("codigo_prenda"));
						prendasC.add(pr);
					}
					conj.construirDesdeDB(res.getString("codigo"),res.getString("nombre"),res.getFloat("descuento"),prendasC);
					prendas.add(conj);
					continue;
				}
				else{ //es prendasintemporada
					PrendaSinTemporada prenda = new PrendaSinTemporada();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
					ps2.setString(1, res.getString("codigo"));
					Collection <ItemMaterial> items = new ArrayList<ItemMaterial>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(res2.getString("codigo_material")), res2.getFloat("cantidad"));
						items.add(item);
					}
					prenda.construirDesdeDB(res.getString("codigo"), res.getString("nombre"), items);
					prendas.add(prenda);
					continue;
				}
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return prendas;
	}

	
	public Prenda buscarPrenda(String codigo){
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where codigo = ? AND activo = 1");
			ps.setString(1, codigo);
			
			ResultSet res = ps.executeQuery();
			while (res.next()){
				res.getString("temporada");
				if (!res.wasNull()){ //es prendacontemporada
					PrendaConTemporada prenda = new PrendaConTemporada();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
					ps2.setString(1, codigo);
					Collection <ItemMaterial> items = new ArrayList<ItemMaterial>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(res2.getString("codigo_material")), res2.getFloat("cantidad"));
						items.add(item);
					}
					prenda.construirDesdeDB(res.getString("codigo"), res.getString("nombre"), res.getString("temporada"), res.getFloat("porcentaje_venta"), items);
					con.close();
					return prenda;				
				}
				
				res.getFloat("descuento");
				if (!res.wasNull()) //es conjuntoprenda
				{
					ConjuntoPrenda conj = new ConjuntoPrenda();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas where codigo_conjunto = ?");
					ps2.setString(1, codigo);
					Collection<Prenda> prendas = new ArrayList<Prenda>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						Prenda pr = Controlador.getControlador().obtenerPrenda(res2.getString("codigo_prenda"));
						prendas.add(pr);
					}
					conj.construirDesdeDB(res.getString("codigo"),res.getString("nombre"),res.getFloat("descuento"),prendas);
					con.close();
					return conj;				
				}
				else{ //es prendasintemporada
					PrendaSinTemporada prenda = new PrendaSinTemporada();
					PreparedStatement ps2 = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas_Materiales where codigo_prenda = ?");
					ps2.setString(1, codigo);
					Collection <ItemMaterial> items = new ArrayList<ItemMaterial>();
					ResultSet res2 = ps2.executeQuery();
					while (res2.next()){
						ItemMaterial item = new ItemMaterial(Controlador.getControlador().obtenerMaterial(res2.getString("codigo_material")), res2.getFloat("cantidad"));
						items.add(item);
					}
					prenda.construirDesdeDB(res.getString("codigo"), res.getString("nombre"), items);
					con.close();
					return prenda;	
				}
			}	
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return null;
	}

	private void insertarItemMaterial(ItemMaterial i, String codigo){
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas_Materiales VALUES (?,?,?,?)");
			ps.setString(1, i.getMaterial().getCodigo());
			ps.setString(2, codigo);
			ps.setFloat(3, i.getCantidad());
			ps.setInt(4, 1);
			ps.execute();
			
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	private void insertarPrenda(Prenda prenda, String codigo){
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas VALUES (?,?,?)");
			ps.setString(1, codigo);
			ps.setString(2, prenda.getCodigo());
			ps.setInt(3, 1);
			ps.execute();
			
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
}