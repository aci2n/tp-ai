package persistencia;


public abstract class AdministradorPersistencia {
	private static String database = "A_Interactivas_14";
	
	public abstract void insert(Object o);
	public abstract void update(Object o);
	public abstract void delete(Object o);
	
	public String getDatabase() {
		return AdministradorPersistencia.database;
	}
}
