package persistencia;

import implementacion.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AdministradorPersistenciaMaterial extends
		AdministradorPersistencia {

	private static AdministradorPersistenciaMaterial instancia;

	private AdministradorPersistenciaMaterial() {

	}

	public static AdministradorPersistenciaMaterial getInstance() {

		if (instancia == null)
			instancia = new AdministradorPersistenciaMaterial();
		return instancia;
	}

	public void insert(Object o) {
		Connection con;
		Material m = (Material) o;
		try {
			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("insert into "
					+ super.getDatabase()
					+ ".dbo.Materiales(codigo, cuit, nombre, cantidad, punto_pedido, costo, activo) values (?,?,?,?,?,?,?)");
			s.setString(1, m.getCodigo());
			s.setString(2, m.getProveedor().getCuit());
			s.setString(3, m.getNombre());
			s.setFloat(4, m.getCantidad());
			s.setFloat(5, m.getPuntoPedido());
			s.setFloat(6, m.getCosto());
			s.setInt(7, 1);
			s.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Object o) {
		Connection con;
		Material m = (Material) o;
		try {
			con = Conexion.connect();
			PreparedStatement s = con
					.prepareStatement("update "
							+ super.getDatabase()
							+ ".dbo.Materiales set cuit = ?, nombre = ?, cantidad = ?, " +
							"punto_Pedido = ?, costo = ? where codigo = ?");

			s.setString(1, m.getProveedor().getCuit());
			s.setString(2, m.getNombre());
			s.setFloat(3, m.getCantidad());
			s.setFloat(4, m.getPuntoPedido());
			s.setFloat(5, m.getCosto());
			s.setString(6, m.getCodigo());
			s.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		Connection con;
		Material m = (Material) o;
		try {
			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("update "+super.getDatabase()+".dbo.Materiales set activo = ? where codigo = ?");
			s.setInt(1, 0);
			s.setString(2, m.getCodigo());
			s.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Material buscarMaterial(String codigo) {
		Connection con;
		Material m = null;
		try {
			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("select * from "+super.getDatabase()+ ".dbo.Materiales where codigo = ?");
			s.setString(1, codigo);
			ResultSet rs = s.executeQuery();

			while (rs.next()) {				
				m = new Material();
	
				m.setCodigo(rs.getString("codigo"));
				m.setProveedor(AdministradorPersistenciaProveedor.getInstancia().buscarProveedor(rs.getString("cuit")));
				m.setNombre(rs.getString("nombre")); 
				m.setCantidad(rs.getFloat("cantidad")); 
				m.setPuntoPedido(rs.getFloat("punto_pedido")); 
				m.setCosto(rs.getFloat("costo"));
				m.setActivo(rs.getBoolean("activo"));			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Collection<Material> obtenerMateriales() {
		Connection con;
		Collection<Material> materiales = new ArrayList<Material>();
		try {

			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("select * from "+ super.getDatabase() +".dbo.Materiales where activo = 1");
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				Material m = new Material();
				
				m.setCodigo(rs.getString("codigo"));
				m.setProveedor(AdministradorPersistenciaProveedor.getInstancia().buscarProveedor(rs.getString("cuit")));
				m.setNombre(rs.getString("nombre")); 
				m.setCantidad(rs.getFloat("cantidad")); 
				m.setPuntoPedido(rs.getFloat("punto_pedido")); 
				m.setCosto(rs.getFloat("costo"));
				m.setActivo(rs.getBoolean("activo"));					
				
				materiales.add(m);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materiales;
	}

}