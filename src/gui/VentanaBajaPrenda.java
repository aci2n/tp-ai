package gui;

import implementacion.Prenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
public class VentanaBajaPrenda extends javax.swing.JFrame implements ActionListener{

	private JButton eliminar;
	private JComboBox prendas;
	private JLabel codigo;

	public static void main(String[] args) {
		new VentanaBajaPrenda();
	}
	
	public VentanaBajaPrenda() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Baja Prenda");		
		this.setSize(335, 117);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
	}
	
	private void componentes() {
		try {
			{
				prendas = new JComboBox();
				getContentPane().add(prendas);
				prendas.setBounds(70, 12, 243, 24);
				for (Prenda pr : Controlador.getControlador().getPrendas())
					prendas.addItem(pr.getCodigo());
				prendas.setSelectedIndex(-1);
			}
			{
				eliminar = new JButton();
				getContentPane().add(eliminar);
				eliminar.setText("Eliminar");
				eliminar.setBounds(222, 48, 91, 24);
				eliminar.addActionListener(this);
			}
			{
				codigo = new JLabel();
				getContentPane().add(codigo);
				codigo.setText("Código:");
				codigo.setBounds(12, 12, 54, 24);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==eliminar){
			if (prendas.getSelectedItem()!=null){
				Controlador.getControlador().eliminarPrenda(prendas.getSelectedItem().toString());
				prendas.removeItemAt(prendas.getSelectedIndex());
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0),"Por favor elija una prenda a eliminar.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
