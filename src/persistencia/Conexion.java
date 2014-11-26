package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public static Connection connect() {
		Connection con = null;
		try {
            String userName = "alvaroai";
            String password = ""; 
            String url = "jdbc:sqlserver://localhost";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
        } catch (Exception e) {
            System.err.println ("No se puede conectar al server de la base de datos");
            return null;
        }
		return con;
	}
}