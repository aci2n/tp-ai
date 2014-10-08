package persistencia;

import java.util.List;

public abstract class AdministradorPersistencia {
	public static String database = "A_Interactivas_01.dbo";
	
	public abstract void insert(Object o);
	public abstract void update(Object o);
	public abstract void delete(Object o);
	public abstract List<Object> select(Object o);
}
