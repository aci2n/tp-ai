package persistencia;

import java.util.List;

public abstract class AdministradorPersistencia {
	public abstract void insert(Object o);
	public abstract void update(Object o);
	public abstract void delete(Object o);
	public abstract List<Object> select(Object o);
}
