package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion 
{
	private Connection con;
	
	public Connection connect()
	{
		try
		{
            String userName = "alvaro"; //CAMBIAR
            String password = "password"; //mi password es password
            String url = "jdbc:sqlserver://localhost";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }
		return con;
	}
}