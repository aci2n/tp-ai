package persistencia;

import implementacion.Prenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AdministradorPersistenciaPrenda extends AdministradorPersistencia {

	private static AdministradorPersistenciaPrenda instancia;
	
	private AdministradorPersistenciaPrenda() {
		
	}
	
	public static AdministradorPersistenciaPrenda getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaPrenda();
		}
		return instancia;
	}
	
	public void insert(Object o) {
		Connection con = Conexion.connect();
		Prenda prenda = (Prenda) o;
		PreparedStatement s = con.prepareStatement("insert into A_Interactivas_01.dbo.Afiliados values (?,?,?,?,?,?,?,?,?,?,?)");
		//agregar campos
		s.setLong(1,a.getCodigo());
		s.setString(2, a.getNombre());
		s.setString(3,a.getDomicilio());
		s.setString(4, a.getTelefono());
		s.setInt(5,a.getTipodoc().getCodigo());
		s.setString(6, a.getNroDoc());
		s.setString(7,a.getSexo());
		s.setTimestamp(8, a.getFechaNac());
		s.setInt(9,a.getDistribuidora().getCodigo());
		s.setTimestamp(10,  a.getFechaAlta());
		s.setInt(11,a.getEstado().getCodigo());
		s.execute();
		
		
		codigo varchar(100) NOT NULL,
	    nombre varchar(100),
	    temporada varchar(100),
	    porcentaje_venta float,
	    activo bit NOT NULL,
	    constraint pk_prendas_simples primary key (codigo)
	}
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Object d) {
		// TODO Auto-generated method stub
		
	}

	public List<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
