package implementacion;

import java.util.Collection;

public class PrendaConTemporada extends PrendaSimple{
	private String temporada;
	private float porcentajeVenta;
	
	public PrendaConTemporada(String codigo, String nombre, String temporada, float porcentajeVenta, Collection<ItemMaterial> items) {
		setCodigo(codigo); //fijarse si esto no jode con la persistencia despues
		setNombre(nombre);
		this.temporada=temporada;
		this.porcentajeVenta=porcentajeVenta;
		setMateriales(items);
	}


	public float calcularPrecio(){
				return 0;
	}
}
