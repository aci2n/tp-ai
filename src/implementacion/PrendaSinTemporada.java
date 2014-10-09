package implementacion;

import java.util.Collection;

public class PrendaSinTemporada extends PrendaSimple{

	public PrendaSinTemporada(String codigo, String nombre, Collection<ItemMaterial> items) {
		setActivo(true);
		setCodigo(codigo);
		setNombre(nombre);
		setMateriales(items);
	}

	public float calcularPrecio() {
		return 0;
	}
	
}
