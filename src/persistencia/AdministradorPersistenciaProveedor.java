package persistencia;

import implementacion.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AdministradorPersistenciaProveedor extends AdministradorPersistencia{

	private static AdministradorPersistenciaProveedor instancia;
	
	private AdministradorPersistenciaProveedor() {
		
	}
	
	public static AdministradorPersistenciaProveedor getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaProveedor();
		}
		return instancia;
	}
	public void insert(Object o) {
		Proveedor p = (Proveedor)o;
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement( "INSERT INTO "+super.getDatabase()+".dbo.Proveedores (cuit, nombre, activo) VALUES (?,?,?)");
			ps.setString(1, p.getCuit());
			ps.setString(2, p.getNombre());
			ps.setInt(3, 1);
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void update(Object o) {
		Proveedor p = (Proveedor)o;
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".dbo.Proveedores SET nombre = ? WHERE cuit = ?");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getCuit());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
	}

	public void delete(Object o) {
		Proveedor p = (Proveedor)o;
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".dbo.Proveedores SET activo = ? WHERE cuit = ?");
			ps.setInt(1, 0);
			ps.setString(2, p.getCuit());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
	}
	
	public Proveedor buscarProveedor(String cuit){
		Proveedor p = null;
		try{
			Connection con = Conexion.connect();

			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".dbo.Proveedores WHERE cuit = ?");
			ps.setString(1, cuit);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				p = new Proveedor();
				p.setCuit(res.getString("cuit"));
				p.setNombre(res.getString("nombre"));
				p.setActivo(res.getBoolean("activo"));
			}
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
		return p;
	}
	
	public Collection<Proveedor> obtenerProveedores(){
		Collection<Proveedor> proveedores = new ArrayList<Proveedor>();
		try{
			Connection con = Conexion.connect();

			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".dbo.Proveedores where activo = 1");
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				Proveedor p = new Proveedor();
				p.setCuit(res.getString("cuit"));
				p.setNombre(res.getString("nombre"));
				p.setActivo(res.getBoolean("activo"));				
				proveedores.add(p);
			}
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
		return proveedores;
	}
}