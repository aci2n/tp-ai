package persistencia;

import implementacion.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			PreparedStatement ps = con.prepareStatement( "INSERT INTO "+super.getDatabase()+".Proveedores (cuit, nombre, activo) VALUES (?,?,?)");
			ps.setString(1, p.getCuit());
			ps.setString(2, p.getNombre());
			ps.setInt(3, 1);
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
	}

	public void update(Object o) {
		Proveedor p = (Proveedor)o;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".Proveedores SET nombre = ? WHERE cuit = ?");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getCuit());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
	}

	public void delete(Object o) {
		Proveedor p = (Proveedor)o;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".Proveedores SET activo = ? WHERE cuit = ?");
			ps.setInt(1, 0);
			ps.setString(2, p.getCuit());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	public Proveedor buscarProveedor(String cuit){
		Proveedor p = null;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".Proveedores WHERE cuit = ?");
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
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
		return p;
	}
}