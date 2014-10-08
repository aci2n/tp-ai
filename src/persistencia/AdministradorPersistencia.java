package persistencia;

import java.util.List;

public abstract class AdministradorPersistencia {
	private static String database = "TPO";
	
	public abstract void insert(Object o);
	public abstract void update(Object o);
	public abstract void delete(Object o);
	
	public String getDatabase() {
		return this.database;
	}
}
