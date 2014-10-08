package gui;

import implementacion.Material;
import implementacion.Prenda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class VentanaListaPrendas extends JFrame{
	
	private JTable tabla;
	DefaultTableModel modelo = new DefaultTableModel();

	public VentanaListaPrendas(){
	
		componentes();
		comportamiento();
	}
	
	
	private void componentes(){
		
		Container c = this.getContentPane();
		Collection<Prenda> prendas = Controlador.getControlador().getPrendas();
		c.setLayout(new BorderLayout());
		
		// COLUMNAS
		
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		
		// FILAS 
		
		for(Prenda pr : prendas){
			Object[] nuevo = {pr.getCodigo(), pr.getNombre()};
			modelo.addRow(nuevo);
		}
		
		tabla = new JTable(){
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