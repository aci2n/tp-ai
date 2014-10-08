package persistencia;

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
		// TODO Auto-generated method stub
		
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
