package persistencia;

import implementacion.ConjuntoPrenda;
import implementacion.Prenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import controlador.Controlador;

public class AdministradorPersistenciaPrenda {
	private static AdministradorPersistenciaPrenda instancia;
	
	private AdministradorPersistenciaPrenda() {
		
	}
	
	public static AdministradorPersistenciaPrenda getInstancia() {
		if (instancia == null)
			instancia = new AdministradorPersistenciaPrenda();
		return instancia;
	}
	
	public Prenda buscarPrenda(String codigo) {
		Prenda prenda = null;
		Connection con = Conexion.connect();
		try{
			PreparedStatement ps = con.prepareStatement( "SELECT * FROM "+AdministradorPersistencia.getDatabase()+".dbo.Prenda p WHERE codigo = ? and not exists(select * from "+AdministradorPersistencia.getDatabase()+".dbo.Conjuntos_Prendas c where c.codigo_conjunto = p.codigo)");
			ps.setString(1, codigo);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()){
				prenda = AdministradorPersistenciaPrendaSinTemporada.getInstancia().buscarPrendaSinTemporada(res.getString("codigo"));
				if (prenda == null)
					prenda = AdministradorPersistenciaPrendaConTemporada.getInstancia().buscarPrendaConTemporada(res.getString("codigo"));
				if (prenda == null)
					prenda = AdministradorPersistenciaConjunto.getInstance().buscarConjuntoPrenda(res.getString("codigo"));
			}
						
			con.close();
			
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);			
		}
		return prenda;
	}
}
