package persistencia;

import implementacion.ItemMaterial;
import implementacion.PrendaSimple;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministradorPersistenciaPrendaSinTemporada extends AdministradorPersistencia {

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
		try {
			PrendaSinTemporada prenda = (PrendaSinTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("insert into (codigo, nombre) " + super.getDatabase() + ".Prendas_Simples values (?, ?)");
			s.setString(1, prenda.getCodigo());
			s.setString(2, prenda.getNombre());
			s.execute();
			insertarItemMateriales(prenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(Object o) {
		try {
			PrendaSinTemporada prenda = (PrendaSinTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("update " + super.getDatabase() + ".Prendas_Simples set nombre = ? where codigo = ?");
			s.setString(1, prenda.getNombre());
			s.setString(2, prenda.getCodigo());
			
			// Se eliminan los ItemMateriales previamente asociados y luego se hace insert de la nueva lista
			s = con.prepareStatement("delete from " + super.getDatabase() + ".Prendas_Simples_Materiales where codigo = ?");
			s.setString(1, prenda.getCodigo());
			
			insertarItemMateriales(prenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		try {
			PrendaSinTemporada prenda = (PrendaSinTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("update " + super.getDatabase() + ".Prendas_Simples set activo = 0 where codigo = ?");
			s.setString(1, prenda.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PrendaSinTemporada buscarPrendaSinTemporada(String codigo) {
		try {
			PrendaSinTemporada prenda = null;
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("select nombre from " + super.getDatabase() + ".Prendas_Simples where codigo = ?");
			ps.setString(1,prenda.getCodigo());
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				prenda = new PrendaSinTemporada();
				prenda.setCodigo(prenda.getCodigo());
				prenda.setNombre(result.getString(1));
			}
			
			List<ItemMaterial> materiales = new ArrayList<ItemMaterial>();
			ps = con.prepareStatement("select i.codigo_material, i.cantidad,  from " + super.getDatabase() + ".Prendas_Simples_Materiales where codigo = ?");
			ps.setString(1, prenda.getCodigo());
			result = ps.executeQuery();
			while (result.next()) {
				ItemMaterial item = new ItemMaterial();
				item.setCantidad(result.getFloat("cantidad"));
				PreparedStatement ps2 = con.prepareStatement("select ")
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertarItemMateriales(PrendaSimple prenda) {
		try {
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("insert into " + super.getDatabase() + ".Prendas_Simples_Materiales (?, ?, ?)");
			
			for (ItemMaterial itemMat : prenda.getMateriales()) {
				s.setString(1, itemMat.getMaterial().getCodigo());
				s.setString(2, prenda.getCodigo());
				s.setFloat(3, itemMat.getCantidad());
				s.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
