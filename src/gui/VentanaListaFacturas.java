package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.FacturaView;
import view.ItemPrendaView;
import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaListaFacturas extends JFrame{
	
	private JTable tabla;
	DefaultTableModel modelo = new DefaultTableModel();

	public VentanaListaFacturas(){
	
		componentes();
		comportamiento();
	}
	
	
	private void componentes(){
		
		Container c = this.getContentPane();
		final Collection<FacturaView> facturas = Controlador.getControlador().getFacturasView();
		c.setLayout(new BorderLayout());
		
		// COLUMNAS
		
		modelo.addColumn("N° factura");
		modelo.addColumn("Precio");
		
		// FILAS 
		
		for(FacturaView f : facturas){
			Object[] nuevo = {f.getNumeroFactura(), "$"+f.getPrecio()};
			modelo.addRow(nuevo);
		}
		
		tabla = new JTable(){
			public String getToolTipText(MouseEvent e) {
				StringBuffer tip = new StringBuffer();				
				java.awt.Point p = e.getPoint();
				int fila = rowAtPoint(p);

				FacturaView fv = obtenerFacturaViewLocal(modelo.getValueAt(fila, 0).toString());
				tip.append("<html><b>Prendas</b>:");
				for (ItemPrendaView ipv : fv.getPrendas())
					tip.append("<br>"+ipv.getPrenda().getNombre()+"  --  "+ipv.getCantidad());

				return tip.toString();

			}
			
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
			
			final FacturaView obtenerFacturaViewLocal (String codigo){
				for (FacturaView fv : facturas)
					if (fv.sosLaFactura(Integer.parseInt(codigo)))
							return fv;
				return null;
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
		setTitle("Lista Facturas");		
		this.setSize(325, 350);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);	
	}	
	
	public static void main(String args[]) {
		new VentanaListaFacturas();
	}

}