package gui;

import implementacion.Proveedor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaListaProveedor extends JFrame{
	
	private JTable tabla;
	
	public VentanaListaProveedor(){
	
		componentes();
		comportamiento();
	}
	
	
	private void componentes(){
		
		Container c = this.getContentPane();
		DefaultTableModel modelo = new DefaultTableModel();
		Collection<Proveedor> proveedores = Controlador.getControlador().getProveedores();
		c.setLayout(new BorderLayout());
		
		// COLUMNAS
		
		modelo.addColumn("Nombre");
		modelo.addColumn("CUIT");
		modelo.addColumn("Activo");
		
		// FILAS 
		
		for(Proveedor p : proveedores){
			String activo;
			if (p.isActivo())
				activo="Sí";
			else
				activo="No";
			Object[] nuevo = {p.getNombre(), p.getCuit(),activo};
			modelo.addRow(nuevo);
		}
		
		tabla = new JTable(){public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;
		}};
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setModel(modelo);
		tabla.setAutoCreateRowSorter(true);
		
		c.add(tabla.getTableHeader(), BorderLayout.NORTH);
		c.add(new JScrollPane(tabla), BorderLayout.CENTER);
		
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Lista Proveedores");		
		this.setSize(325, 350);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);	
	}
	
	public static void main(String args[]) {
		new VentanaListaProveedor();
	}

}