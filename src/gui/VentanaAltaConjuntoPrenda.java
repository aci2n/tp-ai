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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import view.ConjuntoPrendaView;
import view.PrendaView;
import controlador.Controlador;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class VentanaAltaConjuntoPrenda extends javax.swing.JFrame implements ActionListener{
	private JLabel codigo;
	private JLabel nombre;
	private JTextField tCodigo;
	private JTextField tNombre;
	private JTextField tDescuento;
	private JLabel descuento;
	private JTable tablaMateriales;
	private JComboBox<String> prendasComboBox;
	private JButton agregarPrenda;
	private JButton confirmar;
	Collection<PrendaView> prendas = new ArrayList<PrendaView>();
	DefaultTableModel modelo;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		new VentanaAltaConjuntoPrenda();
	}
	
	public VentanaAltaConjuntoPrenda() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Alta Conjunto Prenda");
		this.setSize(355, 461);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void componentes() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			getContentPane().setLayout(null);
			{
				codigo = new JLabel();
				getContentPane().add(codigo);
				codigo.setText("Código:");
				codigo.setBounds(12, 12, 48, 17);
			}
			{
				nombre = new JLabel();
				getContentPane().add(nombre);
				nombre.setText("Nombre:");
				nombre.setBounds(12, 41, 54, 17);
			}
			{
				descuento = new JLabel();
				getContentPane().add(descuento);
				descuento.setText("Descuento:");
				descuento.setBounds(12, 70, 71, 17);
			}
			{
				tDescuento = new JTextField();
				getContentPane().add(tDescuento);
				tDescuento.setBounds(96, 67, 239, 24);
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
				prendasComboBox = new JComboBox<String>();
				getContentPane().add(prendasComboBox);
				for (PrendaView p : Controlador.getControlador().getPrendasView())
					prendasComboBox.addItem(p.getCodigo());
				prendasComboBox.setBounds(12, 128, 168, 24);
				prendasComboBox.setSelectedIndex(-1);
			}
			{
				agregarPrenda= new JButton();
				getContentPane().add(agregarPrenda);
				agregarPrenda.setBounds(239, 126, 96, 27);
				agregarPrenda.setText("Añadir");
				agregarPrenda.addActionListener(this);
			}
			{

				modelo = 
						new DefaultTableModel(
								new String[][] {},
								new String[] { "Nombre Prenda"});
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
		if (e.getSource()==agregarPrenda){
			if (prendasComboBox.getSelectedItem()!= null && Controlador.getControlador().existePrenda(prendasComboBox.getSelectedItem().toString())){
					if (!yaExiste(prendasComboBox.getSelectedItem().toString())){
						PrendaView p = Controlador.getControlador().obtenerPrendaView(prendasComboBox.getSelectedItem().toString());
						prendas.add(p);
						Object [] fila = {p.getNombre()};
						modelo.addRow (fila);
					}
					else
						JOptionPane.showMessageDialog(this.getComponent(0), "No ingrese prendas duplicadas.","Error",JOptionPane.ERROR_MESSAGE);
				}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor seleccione una prenda.","Error",JOptionPane.ERROR_MESSAGE);
			}			
		if (e.getSource()==confirmar){
			if (!tCodigo.getText().equals("") && !tNombre.getText().equals("") && !tDescuento.getText().equals("") && !prendas.isEmpty()){
				try{
					Float.parseFloat(tDescuento.getText());
				} catch(Exception exep){
					JOptionPane.showMessageDialog(this.getComponent(0), "Descuento incorrecto.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				ConjuntoPrendaView conjuntoVw = new ConjuntoPrendaView(tCodigo.getText(),tNombre.getText(), Float.parseFloat(tDescuento.getText()),prendas);
				Controlador.getControlador().altaConjuntoPrenda(conjuntoVw);
				modelo.setRowCount(0);
				this.prendas = new ArrayList<PrendaView>();
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor complete correctamente los campos y/o agregue al menos 1 prenda al conjunto.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean yaExiste(String codigoPrendaAInsertar){
		for (PrendaView p : this.prendas)
			if (p.sosLaPrenda(codigoPrendaAInsertar))
				return true;
		return false;
	}
}
