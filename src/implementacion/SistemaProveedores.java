package implementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class SistemaProveedores implements Observer{
	
	private Collection<Material> materiales;

	public SistemaProveedores(){
		materiales = new ArrayList<Material>();
	}
	@Override
	public void update(Observable o, Object arg1) {
		
		Material m = (Material) o;
		materiales.add(m);
		System.out.println(m.getCodigo());
	}
	
	
}
