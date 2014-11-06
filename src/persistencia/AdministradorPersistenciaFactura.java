package persistencia;

import implementacion.Factura;

import java.util.Collection;

public class AdministradorPersistenciaFactura extends AdministradorPersistencia {
	
	private static AdministradorPersistenciaFactura instancia;
	
	private AdministradorPersistenciaFactura() {
		
	}
	
	public static AdministradorPersistenciaFactura getInstancia() {
		if (instancia == null) {
			instancia = new AdministradorPersistenciaFactura();
		}
		return instancia;
	}
	
	public void insert(Object o) {

	}

	public void update(Object o) {

	}

	public void delete(Object o) {

	}
	
	public Collection<Factura> obtenerFacturas(){
		return null;
	}

}
