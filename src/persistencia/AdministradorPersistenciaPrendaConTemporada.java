package persistencia;

import implementacion.ItemMaterial;
import implementacion.PrendaSimple;
import implementacion.PrendaConTemporada;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministradorPersistenciaPrendaConTemporada extends AdministradorPersistencia {

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
		try {
			PrendaConTemporada prenda = (PrendaConTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("INSERT INTO (codigo, nombre, temporada, porcentajeVenta) " + super.getDatabase() + ".Prendas_Simples values (?, ?, ?, ?)");
			s.setString(1, prenda.getCodigo());
			s.setString(2, prenda.getNombre());
			s.setString(3, prenda.getTemporada());
			s.setFloat(4, prenda.getPorcentajeVenta());
			s.execute();
			insertarItemMateriales(prenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Object o) {
		try {
			PrendaConTemporada prenda = (PrendaConTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("UPDATE " + super.getDatabase() + ".Prendas_Simples SET nombre = ?, SET temporada = ? SET porcentaje_venta = ? WHERE codigo = ?");
			s.setString(1, prenda.getNombre());
			s.setString(2, prenda.getTemporada());
			s.setFloat(3, prenda.getPorcentajeVenta());
			s.setString(4, prenda.getCodigo());
			
			s = con.prepareStatement("DELETE FROM " + super.getDatabase() + ".Prendas_Simples_Materiales WHERE codigo = ?");
			s.setString(1, prenda.getCodigo());
			
			insertarItemMateriales(prenda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		try {
			PrendaConTemporada prenda = (PrendaConTemporada) o;
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("update " + super.getDatabase() + ".Prendas_Simples set activo = 0 where codigo = ?");
			s.setString(1, prenda.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertarItemMateriales(PrendaSimple prenda) {
		try {
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("INSERT INTO " + super.getDatabase() + ".Prendas_Simples_Materiales (?, ?, ?)");
			
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