package persistencia;

import implementacion.ItemMaterial;
import implementacion.Material;
import implementacion.Prenda;
import implementacion.PrendaSimple;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

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
			PreparedStatement s = con.prepareStatement("insert into " + super.getDatabase() + ".Prendas (codigo, nombre) values (?, ?)");
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
			PreparedStatement s = con.prepareStatement("update " + super.getDatabase() + ".Prendas set nombre = ? where codigo = ?");
			s.setString(1, prenda.getNombre());
			s.setString(2, prenda.getCodigo());
			
			// Se eliminan los ItemMateriales previamente asociados y luego se hace insert de la nueva lista
			s = con.prepareStatement("delete from " + super.getDatabase() + ".Prendas_Materiales where codigo_prenda = ?");
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
			PreparedStatement s = con.prepareStatement("update " + super.getDatabase() + ".Prendas set activo = 0 where codigo = ?");
			s.setString(1, prenda.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PrendaSinTemporada buscarPrendaSinTemporada(String codigo) {
		PrendaSinTemporada prenda = null;
		try {
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("select * from " + super.getDatabase() + ".Prendas where codigo = ?");
			ps.setString(1,codigo);
			
			ResultSet result = ps.executeQuery();
			
			while (result.next()){			
				prenda.setCodigo(result.getString("codigo"));
				prenda.setNombre(result.getString("nombre"));
			}
			
			Collection<ItemMaterial> items = new ArrayList<ItemMaterial>();
						
			ps = con.prepareStatement("select * from "+super.getDatabase()+".Prendas_Materiales where codigo_prenda = ?");
			ps.setString(1, prenda.getCodigo());
			
			result = ps.executeQuery();
			while (result.next()) {
				Material m = AdministradorPersistenciaMaterial.getInstance().buscarMaterial(result.getString("codigo_material"));
				ItemMaterial item = new ItemMaterial(m,(result.getFloat("cantidad")));
				items.add(item);
			}
			prenda.setMateriales(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prenda;
	}
	
	public Collection<PrendaSinTemporada> obtenerPrendasSinTemporada() {
		Collection<PrendaSinTemporada> prendas = new ArrayList<PrendaSinTemporada>();
		try {
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("select * from " + super.getDatabase() + ".Prendas p where activo = 1 and temporada is null and not exists(select * from Conjuntos_Prendas c where c.codigo_conjunto = p.codigo)");

			ResultSet result = ps.executeQuery();
			
			while (result.next()){			
				Prenda prenda = buscarPrendaSinTemporada(result.getString("codigo")); //negrada intensifies
				prendas.add((PrendaSinTemporada) prenda);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return prendas;

	}

	private void insertarItemMateriales(PrendaSimple prenda) {
		try {
			Connection con = Conexion.connect();
			PreparedStatement s = con.prepareStatement("insert into " + super.getDatabase() + ".Prendas_Materiales (codigo_material, codigo_prenda, cantidad) values (?, ?, ?)");
			
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
