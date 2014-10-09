package persistencia;

import implementacion.ConjuntoPrenda;
import implementacion.Prenda;
import implementacion.PrendaSinTemporada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import controlador.Controlador;

public class AdministradorPersistenciaConjunto extends AdministradorPersistencia {
	private static AdministradorPersistenciaConjunto instancia;
	
	private AdministradorPersistenciaConjunto(){
	}
	
	public AdministradorPersistenciaConjunto getInstance(){
		if (instancia == null)
			instancia = new AdministradorPersistenciaConjunto();
		return instancia;
	}
	
	public void insert(Object o) {
		ConjuntoPrenda conjunto = (ConjuntoPrenda)o;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos (codigo, nombre, descuento, activo) VALUES (?,?,?,?)");
			ps.setString(1, conjunto.getCodigo());
			ps.setString(2, conjunto.getNombre());
			ps.setFloat(3, conjunto.getDescuento());
			ps.setInt(4,1);
			
			ps.execute();
			
			for (Prenda p : conjunto.getPrendas()){
				ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas_Simples (codigo_conjunto,codigo_prenda_simple,activo) VALUES (?,?,?)");
				ps.setString(1, conjunto.getCodigo());
				ps.setString(2, p.getCodigo());
				ps.setInt(3, 1);
				
				ps.execute();
			}
				
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void update(Object o) {
		ConjuntoPrenda conjunto = (ConjuntoPrenda)o;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement("UPDATE "+super.getDatabase()+".dbo.Conjuntos SET nombre = ?, descuento = ?, activo = ? WHERE codigo = ?");
			ps.setString(1, conjunto.getNombre());
			ps.setFloat(2, conjunto.getDescuento());
			int myInt = (conjunto.isActivo()) ? 1 : 0;
			ps.setInt(3,myInt);
			ps.setString(4, conjunto.getCodigo());
			
			ps.execute();
			
			ps = con.prepareStatement("DELETE FROM "+super.getDatabase()+".dbo.Conjuntos_Prendas_Simples WHERE codigo_conjunto = ?");
			ps.setString(1, conjunto.getCodigo());
			
			ps.execute();
				
			
			for (Prenda p : conjunto.getPrendas()){
				ps = con.prepareStatement("INSERT INTO "+super.getDatabase()+".dbo.Conjuntos_Prendas_Simples (codigo_conjunto,codigo_prenda_simple,activo) VALUES (?,?,?)");
				ps.setString(1, conjunto.getCodigo());
				ps.setString(2, p.getCodigo());
				ps.setInt(3, 1);
				
				ps.execute();
			}
			
			con.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void delete(Object o) {
		ConjuntoPrenda conjunto = (ConjuntoPrenda)o;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "UPDATE "+super.getDatabase()+".dbo.Conjuntos SET activo = ? WHERE cuit = ?");
			ps.setInt(1, 0);
			ps.setString(2, conjunto.getCodigo());
			
			ps.execute();
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	public ConjuntoPrenda buscarConjuntoPrenda(String codigo){
		ConjuntoPrenda conjunto = null;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+super.getDatabase()+".dbo.Conjuntos WHERE codigo_conjunto = ?");
			ps.setString(1, codigo);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				conjunto = new ConjuntoPrenda();
				conjunto.setCodigo(res.getString("codigo_conjunto"));
				conjunto.setActivo(res.getBoolean("activo"));
				conjunto.setNombre(res.getString("nombre"));
			}
			
			ps = con.prepareStatement("SELECT * FROM "+super.getDatabase()+" .dbo.Conjuntos_Prendas_Simples WHERE codigo_conjunto = ?");
			res = ps.executeQuery();
			
			
			
			Collection<Prenda> prendas = new ArrayList<Prenda>();
			while (res.next()){
				Prenda p = Controlador.getControlador().obtenerPrenda(res.getString("codigo_prenda")); //falta implementar
				prendas.add(p);				
			}
			
			conjunto.setPrendas(prendas);			
			con.close();
			
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
		return conjunto;
	}
	
	// falta el obtener collection conjuntos, consultar con Moe
	
	public Collection<ConjuntoPrenda> obtenerConjuntos (){
		Collection<ConjuntoPrenda> conjuntos = new ArrayList<ConjuntoPrenda>();
		try{
			Connection con = Conexion.connect();
			PreparedStatement ps = con.prepareStatement("select * from " + super.getDatabase() + ".Conjuntos");

			ResultSet result = ps.executeQuery();
			
			while (result.next()){			
				ConjuntoPrenda conjunto = this.buscarConjuntoPrenda(result.getString("codigo")); //negrada intensifies
				conjuntos.add(conjunto);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return conjuntos;
	}

}
