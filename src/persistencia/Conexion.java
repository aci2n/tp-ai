package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public static Connection connect() {
		Connection con = null;
		try {
            String userName = "A_Interactivas_14";
            String password = "A_Interactivas_14"; 
            String url = "jdbc:sqlserver://192.168.6.202";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
        } catch (Exception e) {
            System.err.println ("No se puede conectar al server de la base de datos");
            return null;
        }
		return con;
	}
}