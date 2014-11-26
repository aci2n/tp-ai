package persistencia;

import implementacion.ConjuntoPrenda;
import implementacion.Prenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controlador.Controlador;


public class AdministradorPersistenciaConjuntoPrenda extends AdministradorPersistencia{

	private static AdministradorPersistenciaConjuntoPrenda instancia;
	
	private AdministradorPersistenciaConjuntoPrenda() {
		
	}
	
	public static AdministradorPersistenciaConjuntoPrenda getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaConjuntoPrenda();
		}
		return instancia;
	}

	public void insert(Object o) {
		ConjuntoPrenda c = (ConjuntoPrenda)o;
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Prendas(codigo,nombre,descuento,activo,tipo_prenda) VALUES (?,?,?,?,?)");
			ps.setString(1, c.getCodigo());
			ps.setString(2,c.getNombre());
			ps.setFloat(3, c.getDescuento());
			ps.setInt(4,1);
			ps.setString(5, "conjunto");
			
			ps.execute();
			
			for (Prenda p : c.getPrendas()){
				PreparedStatement psInsertarPrenda = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas VALUES (?,?)");
				psInsertarPrenda.setString(1, c.getCodigo());
				psInsertarPrenda.setString(2, p.getCodigo());
				psInsertarPrenda.execute();
			}
			
			con.close();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}		
	}
	
	public void update(Object o) {
		Connection con = Conexion.connect();
		ConjuntoPrenda c = (ConjuntoPrenda)o;
		try{
			
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
				PreparedStatement psInsertarPrenda = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas VALUES (?,?)");
				psInsertarPrenda.setString(1, c.getCodigo());
				psInsertarPrenda.setString(2, p.getCodigo());
				psInsertarPrenda.execute();
			}
			
			con.close();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		ConjuntoPrenda p = (ConjuntoPrenda)o;
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Prendas set activo = 0 where codigo = ?");
			ps.setString(1, p.getCodigo());
			
			ps.execute();
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	public Collection<ConjuntoPrenda> obtenerConjuntosPrenda(){
		Collection<ConjuntoPrenda> conjuntosPrenda = new ArrayList<ConjuntoPrenda>();
		try {
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where activo = 1 AND tipo_prenda = 'conjunto'");
			ResultSet res = ps.executeQuery();
			while (res.next()){
				ConjuntoPrenda conjuntoPrenda = new ConjuntoPrenda();
				Collection <Prenda> prendas = new ArrayList<Prenda>();
				PreparedStatement psPrendas = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas where codigo_conjunto = ?");
				psPrendas.setString(1, res.getString("codigo"));
				ResultSet resPrendas = psPrendas.executeQuery();
				while (resPrendas.next()){
					Prenda pr = Controlador.getControlador().obtenerPrenda(resPrendas.getString("codigo_prenda"));
					prendas.add(pr);
				}
				conjuntoPrenda.setCodigo(res.getString("codigo"));
				conjuntoPrenda.setNombre(res.getString("nombre"));
				conjuntoPrenda.setDescuento(res.getFloat("descuento"));
				conjuntoPrenda.setActivo(res.getBoolean("activo"));
				conjuntoPrenda.setPrendas(prendas);
				conjuntosPrenda.add(conjuntoPrenda);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}	
		return conjuntosPrenda;
	}

	
	public ConjuntoPrenda buscarPrenda(String codigo){
		Connection con = Conexion.connect();
		ConjuntoPrenda conjuntoPrenda = null;
		Collection <Prenda> prendas = new ArrayList<Prenda>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Prendas where tipo_prenda = 'conjunto' AND codigo = ?");
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while (res.next()){
				conjuntoPrenda = new ConjuntoPrenda();
				PreparedStatement psPrendas = con.prepareStatement("SELECT * FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas where codigo_conjunto = ?");
				psPrendas.setString(1, res.getString("codigo"));
				ResultSet resPrendas = psPrendas.executeQuery();
				while (resPrendas.next()){
					Prenda pr = Controlador.getControlador().obtenerPrenda(resPrendas.getString("codigo_prenda"));
					prendas.add(pr);
				}
				conjuntoPrenda.setCodigo(res.getString("codigo"));
				conjuntoPrenda.setNombre(res.getString("nombre"));
				conjuntoPrenda.setDescuento(res.getFloat("descuento"));
				conjuntoPrenda.setActivo(res.getBoolean("activo"));
				conjuntoPrenda.setPrendas(prendas);
			}
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return conjuntoPrenda;
	}
}