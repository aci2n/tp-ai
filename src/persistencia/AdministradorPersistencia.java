package persistencia;

import java.util.List;

public abstract class AdministradorPersistencia {
	private static String database = "A_Interactivas_01.dbo";
	
	public abstract void insert(Object o);
	public abstract void update(Object o);
	public abstract void delete(Object o);
	public abstract List<Object> select(Object o);
	
	public String getDatabase() {
		return this.database;
	}
}
