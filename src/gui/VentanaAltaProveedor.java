package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.ProveedorView;
import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaAltaProveedor extends javax.swing.JFrame implements ActionListener{
	private JLabel nombre;
	private JTextField tNombre;
	private JButton agregar;
	private JTextField tCuit;
	private JLabel cuit;

	public static void main(String[] args) {
		new VentanaAltaProveedor();
	}
	
	public VentanaAltaProveedor() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Alta Proveedor");		
		this.setSize(316, 138);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void componentes() {
		try {
			getContentPane().setLayout(null);
			{
				nombre = new JLabel();
				getContentPane().add(nombre);
				nombre.setText("Nombre:");
				nombre.setBounds(12, 12, 54, 17);
			}
			{
				cuit = new JLabel();
				getContentPane().add(cuit);
				cuit.setText("CUIT:");
				cuit.setBounds(12, 41, 35, 17);
			}
			{
				tNombre = new JTextField();
				getContentPane().add(tNombre);
				tNombre.setBounds(78, 9, 214, 24);
			}
			{
				tCuit = new JTextField();
				getContentPane().add(tCuit);
				tCuit.setBounds(78, 38, 214, 24);
			}
			{
				agregar = new JButton();
				getContentPane().add(agregar);
				agregar.setText("Confirmar");
				agregar.setBounds(187, 74, 98, 24);
				agregar.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==agregar){
			if (!tNombre.getText().equals("") && !tCuit.getText().equals("")){
				Controlador con = Controlador.getControlador();
				con.altaProveedor(new ProveedorView(tNombre.getText(), tCuit.getText(), true));
			} else {
				JOptionPane.showMessageDialog(null, "Por favor complete correctamente los campos.","Error",JOptionPane.ERROR_MESSAGE);
			}		
		}
	}

}
