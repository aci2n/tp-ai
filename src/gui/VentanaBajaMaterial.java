package gui;
import implementacion.Material;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaBajaMaterial extends javax.swing.JFrame implements ActionListener{
	private JButton eliminar;
	private JComboBox materiales;
	private JLabel codigo;

	public static void main(String[] args) {
		new VentanaBajaMaterial();
	}
	
	public VentanaBajaMaterial() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Baja Material");		
		this.setSize(335, 117);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
	}
	
	private void componentes() {
		try {
			{
				materiales = new JComboBox();
				getContentPane().add(materiales);
				materiales.setBounds(70, 12, 243, 24);
				for (Material m : Controlador.getControlador().getMateriales())
					materiales.addItem(m.getCodigo());
				materiales.setSelectedIndex(-1);
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
			if (materiales.getSelectedItem()!=null){
				
				Material m = Controlador.getControlador().obtenerMaterial(materiales.getSelectedItem().toString());
				if(m != null){
					Controlador.getControlador().eliminarMaterial(m.getCodigo());
					materiales.removeItemAt(materiales.getSelectedIndex());
				}
			}
			else
				JOptionPane.showMessageDialog(this.getComponent(0),"Por favor elija un material a eliminar.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
