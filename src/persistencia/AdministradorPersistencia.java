package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AdministradorPersistencia 
{
	private Connection con;
	
	public void connect()
	{
		try
		{
            String userName = "A_Interactivas_01";
            String password = "A_Interactivas_01";
            String url = "jdbc:sqlserver://192.168.6.202:1433";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
           
        }
        catch (Exception e)
        {
			JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
        }
		
	}
		
	public ResultSet buscarRegistro(String dni)
	{
		try
		{
			PreparedStatement ps = con.prepareStatement("select * from A_Interactivas_01.dbo.persona where dni = ?");
			ps.setString(1, dni);
			
			ResultSet rs = ps.executeQuery();
		    return rs;
		    			
		   			   
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}


}
