package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.ConjuntoPrendaView;
import view.ItemMaterialView;
import view.PrendaConTemporadaView;
import view.PrendaSinTemporadaView;
import view.PrendaView;
import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaListaPrendas extends JFrame{
	
	private JTable tabla;
	DefaultTableModel modelo = new DefaultTableModel();

	public VentanaListaPrendas(){
	
		componentes();
		comportamiento();
	}
	
	
	private void componentes(){
		
		Container c = this.getContentPane();
		Collection<PrendaView> prendas = Controlador.getControlador().getPrendasView();
		c.setLayout(new BorderLayout());
		
		// COLUMNAS
		
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		
		// FILAS 
		
		for(PrendaView pr : prendas){
			Object[] nuevo = {pr.getCodigo(), pr.getNombre(),pr.getPrecio()};
			modelo.addRow(nuevo);
		}
		
		tabla = new JTable(){
			public String getToolTipText(MouseEvent e) {
				StringBuffer tip = new StringBuffer();				
				java.awt.Point p = e.getPoint();
				int fila = rowAtPoint(p);
								
				PrendaView prenda = Controlador.getControlador().obtenerPrendaView(modelo.getValueAt(fila, 0).toString());
				if (prenda instanceof PrendaConTemporadaView) {
					PrendaConTemporadaView prendaT = (PrendaConTemporadaView)prenda;
					tip.append("<html><b>Materiales</b>:<br>");
					for (ItemMaterialView i : prendaT.getMateriales())
						tip.append(i.getMaterial().getNombre()+"  --  "+i.getCantidad()+", ");
					tip.setLength(tip.length() - 2);
					tip.append("<br><b>Temporada</b>:<br>"+prendaT.getTemporada());
					tip.append("<br><b>Porcentaje Venta</b>:<br>"+prendaT.getPorcentajeVenta());
				}
				if (prenda instanceof PrendaSinTemporadaView) {
					PrendaSinTemporadaView prendaS = (PrendaSinTemporadaView)prenda;
					tip.append("<html><b>Materiales</b>:<br>");
					for (ItemMaterialView i : prendaS.getMateriales())
						tip.append(i.getMaterial().getNombre()+"  --  "+i.getCantidad()+", ");
					tip.setLength(tip.length() - 2);
				}
				if (prenda instanceof ConjuntoPrendaView) {
					ConjuntoPrendaView conjunto = (ConjuntoPrendaView)prenda;
					tip.append("<html><b>Prendas</b>:<br>");
					for (PrendaView pr : conjunto.getPrendas())
						tip.append(pr.getNombre()+", ");
					tip.setLength(tip.length() - 2);
					tip.append("<html><br><b>Descuento</b>:<br>"+conjunto.getDescuento());
				}	 

				return tip.toString();

			}
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setModel(modelo);
		tabla.setAutoCreateRowSorter(true);
		
		c.add(tabla.getTableHeader(), BorderLayout.NORTH);
		c.add(new JScrollPane(tabla), BorderLayout.CENTER);
		
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Lista Prendas");		
		this.setSize(325, 350);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);	
	}
	
	public static void main(String args[]) {
		new VentanaListaPrendas();
	}

}