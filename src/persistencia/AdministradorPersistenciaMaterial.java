package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;

public class AdministradorPersistenciaMaterial {
	
	private Conexion conexion;
	private Connection con = conexion.connect();
	
	public ResultSet buscarMaterial(String codigo){
	
		ResultSet rs = null;
		return null;
	}
		
}