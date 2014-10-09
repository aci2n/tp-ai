package persistencia;

import implementacion.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

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
		Connection con = Conexion.connect();
		try{
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
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".dbo.Proveedores SET nombre = ?, activo = ? WHERE cuit = ?");
			int myInt = (p.isActivo()) ? 1 : 0;
			ps.setString(1, p.getNombre());
			ps.setInt(2, myInt);
			ps.setString(3, p.getCuit());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
	}

	public void delete(Object o) {
		Proveedor p = (Proveedor)o;
		Connection con = Conexion.connect();
		try{
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
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".dbo.Proveedores WHERE cuit = ?");
			ps.setString(1, cuit);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				p = new Proveedor();
				p.setActivo(res.getBoolean("activo"));
				p.setNombre(res.getString("nombre"));
				p.setCuit(res.getString("cuit"));
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
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".dbo.Proveedores");
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				Proveedor p = new Proveedor();
				p.setActivo(res.getBoolean("activo"));
				p.setNombre(res.getString("nombre"));
				p.setCuit(res.getString("cuit"));
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