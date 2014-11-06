package gui;
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

import view.ItemMaterialView;
import view.MaterialView;
import view.PrendaSinTemporadaView;
import controlador.Controlador;

@SuppressWarnings("serial")
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
	Collection<ItemMaterialView> itemMateriales = new ArrayList<ItemMaterialView>();
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
				for (MaterialView m : Controlador.getControlador().getMaterialesView())
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
			if (materialesComboBox.getSelectedItem()!= null && Controlador.getControlador().existeMaterial(materialesComboBox.getSelectedItem().toString())){
				if(!contieneA(itemMateriales,materialesComboBox.getSelectedItem().toString())){
					ItemMaterialView i = new ItemMaterialView((float)(Integer)cantidadMaterial.getValue(), Controlador.getControlador().obtenerMaterialView(materialesComboBox.getSelectedItem().toString()));
					itemMateriales.add(i);
					Object [] fila = {i.getMaterial().getNombre(),i.getCantidad()};
					modelo.addRow (fila);
				}
				else
					JOptionPane.showMessageDialog(this.getComponent(0), "No ingrese materiales duplicados.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor seleccione un material e ingrese una cantidad.","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getSource()==confirmar){
			if (!tCodigo.getText().equals("") && !tNombre.getText().equals("") && !itemMateriales.isEmpty()){
				PrendaSinTemporadaView prendaSTVw = new PrendaSinTemporadaView(tCodigo.getText(), tNombre.getText(), itemMateriales, true);
				Controlador.getControlador().altaPrendaSinTemporada(prendaSTVw);
				modelo.setRowCount(0);
				this.itemMateriales = new ArrayList<ItemMaterialView>();
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor complete correctamente los campos y/o ingrese al menos 1 material.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean contieneA(Collection<ItemMaterialView> items, String codigo){
		for (ItemMaterialView i : items)
			if (i.getMaterial().sosElMaterial(codigo))
				return true;
		return false;
	}
}
