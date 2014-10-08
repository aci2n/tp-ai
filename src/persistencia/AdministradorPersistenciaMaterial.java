package persistencia;

import implementacion.Material;
import implementacion.MaterialView;
import implementacion.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministradorPersistenciaMaterial extends
		AdministradorPersistencia {

	private Connection con;
	private static AdministradorPersistenciaMaterial instancia;

	private AdministradorPersistenciaMaterial() {

	}

	public static AdministradorPersistenciaMaterial getInstance() {

		if (instancia == null)
			instancia = new AdministradorPersistenciaMaterial();
		return instancia;
	}

	@Override
	public void insert(Object o) {
		// TODO Auto-generated method stub
		try {
			Material m = (Material) o;
			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("insert into "
					+ super.getDatabase()
					+ ".dbo.Materiales values (?,?,?,?,?,?,?)");
			s.setString(1, m.getCodigo());
			s.setString(2, m.getProveedor().getCuit());
			s.setString(3, m.getNombre());
			s.setFloat(4, m.getCantidad());
			s.setFloat(5, m.getPuntoPedido());
			s.setFloat(6, m.getCosto()); // FALTA VER LO DEL ATRIBUTO ACTIVO
			s.setBoolean(7, true);
			s.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		try {

			Material m = (Material) o;
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

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		try {

			Material m = (Material) o;
			con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("delete from "
					+ super.getDatabase() + ".dbo.Materiales where codigo = ?");
			s.setString(1, m.getCodigo());
			s.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public List<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	public Material buscarMaterial(String codigo) {

		try {
			con = Conexion.connect();
			Material m = null;
			PreparedStatement s = con.prepareStatement("select * from "
					+ super.getDatabase() + ".dbo.Materiales where codigo = ?");
			s.setString(1, codigo);
			ResultSet rs = s.executeQuery();

			while (rs.next()) {

				String cod = rs.getString(1);
				String cuit = rs.getString(2);
				String nom = rs.getString(3);
				float cantidad = rs.getFloat(4);
				float puntoPedido = rs.getFloat(5);
				float costo = rs.getFloat(6);

				m = new Material(cod, nom, puntoPedido, new Proveedor("jose",
						"1"), cantidad, costo); // FALTA BUSCAR PROVEEDOR Y
												// PASARLO AL CONSTRUCTOR
			}
			return m;
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/*
	public ArrayList<MaterialView> listarMateriales() {

		try {

			ArrayList<MaterialView> materiales = new ArrayList<MaterialView>();
			con = Conexion.connect();
			Material m = null;
			PreparedStatement s = con.prepareStatement("select * from "
					+ super.getDatabase() + ".dbo.Materiales");
			ResultSet rs = s.executeQuery();

			while (rs.next()) {

				String cod = rs.getString(1);
				String cuit = rs.getString(2);
				String nom = rs.getString(3);
				float cantidad = rs.getFloat(4);
				float puntoPedido = rs.getFloat(5);
				float costo = rs.getFloat(6);
				m = new Material(cod, nom, puntoPedido, new Proveedor("jose",
						"1"), cantidad, costo);
				MaterialView mv = m.generarMaterialView();
				materiales.add(mv); // FALTA BUSCAR PROVEEDOR Y PASARLO AL
									// CONSTRUCTOR
			}

			return materiales;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
*/
	
	public ArrayList<Material> obtenerMateriales() {

		try {

			ArrayList<Material> materiales = new ArrayList<Material>();
			con = Conexion.connect();
			Material m = null;
			PreparedStatement s = con.prepareStatement("select * from "
					+ super.getDatabase() + ".dbo.Materiales");
			ResultSet rs = s.executeQuery();

			while (rs.next()) {

				String cod = rs.getString(1);
				String cuit = rs.getString(2);
				String nom = rs.getString(3);
				float cantidad = rs.getFloat(4);
				float puntoPedido = rs.getFloat(5);
				float costo = rs.getFloat(6);
				m = new Material(cod, nom, puntoPedido, new Proveedor("jose",
						"1"), cantidad, costo);
				materiales.add(m); // FALTA BUSCAR PROVEEDOR Y PASARLO AL
									// CONSTRUCTOR
			}

			return materiales;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}