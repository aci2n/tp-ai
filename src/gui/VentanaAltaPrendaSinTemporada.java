package gui;
import implementacion.ItemMaterial;
import implementacion.Material;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

public class VentanaAltaPrendaSinTemporada extends javax.swing.JFrame implements ActionListener{
	private JLabel codigo;
	private JLabel nombre;
	private JTextField tCodigo;
	private JTextField tNombre;
	private JTable tablaMateriales;
	private JComboBox materialesComboBox;
	private JButton agregarMaterial;
	private JSpinner cantidadMaterial;
	private JButton confirmar;
	Collection<ItemMaterial> itemMateriales = new ArrayList<ItemMaterial>();
	DefaultTableModel modelo;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		new VentanaAltaPrendaSinTemporada();
	}
	
	public VentanaAltaPrendaSinTemporada() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Alta Prenda sin Temporada");
		this.setSize(355, 461);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void componentes() {
		try {

			getContentPane().setLayout(null);
			{
				codigo = new JLabel();
				getContentPane().add(codigo);
				codigo.setText("Codigo:");
				codigo.setBounds(12, 12, 48, 17);
			}
			{
				nombre = new JLabel();
				getContentPane().add(nombre);
				nombre.setText("Nombre:");
				nombre.setBounds(12, 41, 54, 17);
			}
			{
				tCodigo = new JTextField();
				getContentPane().add(tCodigo);
				tCodigo.setBounds(96, 9, 239, 24);
			}
			{
				tNombre = new JTextField();
				getContentPane().add(tNombre);
				tNombre.setBounds(96, 38, 239, 24);
			}
			{
				confirmar = new JButton();
				getContentPane().add(confirmar);
				confirmar.setText("Confirmar");
				confirmar.setBounds(232, 393, 103, 24);
				confirmar.addActionListener(this);
			}
			{
				materialesComboBox = new JComboBox();
				getContentPane().add(materialesComboBox);
				for (Material m : Controlador.getControlador().getMateriales())
					materialesComboBox.addItem(m.getCodigo());
				materialesComboBox.setBounds(12, 128, 168, 24);
				materialesComboBox.setSelectedIndex(-1);
			}
			{
				cantidadMaterial = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
				getContentPane().add(cantidadMaterial);
				cantidadMaterial.setBounds(186, 129, 47, 24);
			}
			{
				agregarMaterial = new JButton();
				getContentPane().add(agregarMaterial);
				agregarMaterial.setBounds(239, 126, 96, 27);
				agregarMaterial.setText("Añadir");
				agregarMaterial.addActionListener(this);
			}
			{

				modelo = 
						new DefaultTableModel(
								new String[][] {},
								new String[] { "Nombre", "Cantidad" });
				tablaMateriales = new JTable(modelo);
				JScrollPane scrollPaneTabla = new JScrollPane(tablaMateriales);
				tablaMateriales.setFillsViewportHeight(true); 
				tablaMateriales.getTableHeader().setReorderingAllowed(false);
				getContentPane().add(scrollPaneTabla);	
				scrollPaneTabla.setBounds(12, 158, 323, 223);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==agregarMaterial){
			if (Controlador.getControlador().existeMaterial(tCodigo.getText())){
				ItemMaterial i = new ItemMaterial(Controlador.getControlador().obtenerMaterial(tCodigo.getText()),(Float)cantidadMaterial.getValue());
				itemMateriales.add(i);
				Object [] fila = {i.getMaterial().getNombre(),i.getCantidad()};
				modelo.addRow (fila);
			}
			JOptionPane.showMessageDialog(this.getComponent(0), "Por favor seleccione un material e ingrese una cantidad.","Error",JOptionPane.ERROR_MESSAGE);
		}
		if (e.getSource()==confirmar){
			if (!tCodigo.getText().equals("") && !tNombre.getText().equals("")){
				Controlador.getControlador().altaPrendaSinTemporada(tCodigo.getText(), tNombre.getText(),itemMateriales);
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor complete correctamente los campos.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
