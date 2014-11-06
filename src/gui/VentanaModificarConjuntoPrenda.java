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
public class VentanaModificarConjuntoPrenda extends javax.swing.JFrame implements ActionListener{
	private JLabel codigo;
	private JLabel nombre;
	private JTextField tNombre;
	private JComboBox comboCodigo;
	private JTable tablaPrendas;
	private JComboBox prendasComboBox;
	private JButton agregarPrenda;
	private JButton confirmar;
	private JTextField tDescuento;
	private JLabel descuento;
	Collection<PrendaView> prendas = new ArrayList<PrendaView>();
	DefaultTableModel modelo;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		new VentanaModificarConjuntoPrenda();
	}
	
	public VentanaModificarConjuntoPrenda() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Modificar Conjunto Prenda");
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
				descuento = new JLabel();
				getContentPane().add(descuento);
				descuento.setText("Descuento:");
				descuento.setBounds(12, 70, 75, 17);
			}
			{
				tNombre = new JTextField();
				getContentPane().add(tNombre);
				tNombre.setBounds(96, 38, 239, 24);
			}
			{
				tDescuento = new JTextField();
				getContentPane().add(tDescuento);
				tDescuento.setBounds(96, 67, 239, 24);
			}
			{
				confirmar = new JButton();
				getContentPane().add(confirmar);
				confirmar.setText("Confirmar");
				confirmar.setBounds(232, 393, 103, 24);
				confirmar.addActionListener(this);
			}
			{
				prendasComboBox = new JComboBox();
				getContentPane().add(prendasComboBox);
				for (PrendaView p : Controlador.getControlador().getPrendasView())
					prendasComboBox.addItem(p.getCodigo());
				prendasComboBox.setBounds(12, 128, 168, 24);
				prendasComboBox.setSelectedIndex(-1);
			}
			{
				comboCodigo = new JComboBox();
				getContentPane().add(comboCodigo);
				for (PrendaView p : Controlador.getControlador().getPrendasView())
					if(p instanceof ConjuntoPrendaView) //ni nos vimos patrones GRASP
						comboCodigo.addItem(p.getCodigo());
				comboCodigo.setBounds(96, 8, 239, 24);
				comboCodigo.setSelectedIndex(-1);
				comboCodigo.addActionListener(this);
			}
			{
				agregarPrenda = new JButton();
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
				tablaPrendas= new JTable(modelo);
				JScrollPane scrollPaneTabla = new JScrollPane(tablaPrendas);
				tablaPrendas.setFillsViewportHeight(true); 
				tablaPrendas.getTableHeader().setReorderingAllowed(false);
				getContentPane().add(scrollPaneTabla);	
				scrollPaneTabla.setBounds(12, 158, 323, 223);
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==comboCodigo){
			if (comboCodigo.getSelectedItem()!=null){
				ConjuntoPrendaView p = (ConjuntoPrendaView)Controlador.getControlador().obtenerPrendaView(comboCodigo.getSelectedItem().toString());
				tNombre.setText(p.getNombre());
				tDescuento.setText(Float.toString(p.getDescuento()));
			}
		}
		if (e.getSource()==agregarPrenda){
			if (prendasComboBox.getSelectedItem()!= null && Controlador.getControlador().existePrenda(prendasComboBox.getSelectedItem().toString())){
				if(!seContieneASiMismo(prendasComboBox.getSelectedItem().toString(),comboCodigo.getSelectedItem().toString())){
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
					JOptionPane.showMessageDialog(this.getComponent(0), "Un conjunto no puede contenerse a sí mismo.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor seleccione una prenda.","Error",JOptionPane.ERROR_MESSAGE);
		}
		if (e.getSource()==confirmar){
			if (comboCodigo.getSelectedItem() != null && !tNombre.getText().equals("") && !tDescuento.getText().equals("") && !prendas.isEmpty()){
				try{
					Float.parseFloat(tDescuento.getText());
				} catch(Exception exep){
					JOptionPane.showMessageDialog(this.getComponent(0), "Descuento incorrecto.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				ConjuntoPrendaView conjuntoView = new ConjuntoPrendaView(comboCodigo.getSelectedItem().toString(), tNombre.getText(), Float.parseFloat(tDescuento.getText()), prendas);
				Controlador.getControlador().ModificarConjuntoPrenda(conjuntoView);
				modelo.setRowCount(0);
				this.prendas = new ArrayList<PrendaView>();
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor complete correctamente los campos y/o agregue al menos 1 prenda al conjunto.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean seContieneASiMismo(String codigoPrendaAInsertar, String codigoPrendaActual){ //calabria estaria orgulloso
		if (codigoPrendaAInsertar.equals(codigoPrendaActual))
			return true;
		PrendaView p = Controlador.getControlador().obtenerPrendaView(codigoPrendaAInsertar);
		if(p!=null && p instanceof ConjuntoPrendaView){
			ConjuntoPrendaView conjunto = (ConjuntoPrendaView)p;
			for (PrendaView pre : conjunto.getPrendas())
				if(seContieneASiMismo (pre.getCodigo(),codigoPrendaActual))
					return true;
		}
		return false;
	}
	
	private boolean yaExiste(String codigoPrendaAInsertar){
		for (PrendaView p : this.prendas)
			if (p.sosLaPrenda(codigoPrendaAInsertar))
				return true;
		return false;
	}
}
