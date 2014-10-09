package gui;
import implementacion.Material;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import persistencia.AdministradorPersistenciaMaterial;
import controlador.Controlador;

public class VentanaAltaMaterial extends javax.swing.JFrame implements ActionListener{
	private JLabel nombre;
	private JTextField tNombre;
	private JLabel codigo;
	private JTextField tCuit;
	private JLabel cuit;
	private JTextField tCodigo;
	private JButton agregar;
	private JTextField tCosto;
	private JTextField tCantidad;
	private JTextField tPuntoPedido;
	private JLabel costo;
	private JLabel cantidad;
	private JLabel puntoPedido;


	public static void main(String[] args) {
		new VentanaAltaMaterial();
	}
	
	public VentanaAltaMaterial() {
		comportamiento();
		componentes();
	}
	
	private void componentes() {
		try {
			{
				nombre = new JLabel();
				getContentPane().add(nombre);
				nombre.setText("Nombre:");
				nombre.setBounds(12, 45, 54, 17);
			}
			{
				puntoPedido = new JLabel();
				getContentPane().add(puntoPedido);
				puntoPedido.setText("Punto pedido:");
				puntoPedido.setBounds(12, 74, 87, 17);
			}
			{
				cantidad = new JLabel();
				getContentPane().add(cantidad);
				cantidad.setText("Cantidad inicial:");
				cantidad.setBounds(12, 103, 93, 17);
			}
			{
				costo = new JLabel();
				getContentPane().add(costo);
				costo.setText("Costo unitario:");
				costo.setBounds(12, 132, 90, 17);
			}
			{
				tNombre = new JTextField();
				getContentPane().add(tNombre);
				tNombre.setBounds(111, 42, 181, 24);
			}
			{
				tPuntoPedido = new JTextField();
				getContentPane().add(tPuntoPedido);
				tPuntoPedido.setBounds(111, 70, 181, 26);
			}
			{
				tCantidad = new JTextField();
				getContentPane().add(tCantidad);
				tCantidad.setBounds(111, 100, 181, 24);
			}
			{
				tCosto = new JTextField();
				getContentPane().add(tCosto);
				tCosto.setBounds(111, 129, 181, 24);
			}
			{
				agregar = new JButton();
				getContentPane().add(agregar);
				agregar.setText("Confirmar");
				agregar.setBounds(190, 199, 103, 24);
				agregar.addActionListener(this);
			}
			{
				tCodigo = new JTextField();
				getContentPane().add(tCodigo);
				tCodigo.setBounds(111, 13, 181, 24);
			}
			{
				codigo = new JLabel();
				getContentPane().add(codigo);
				codigo.setText("Código:");
				codigo.setBounds(12, 16, 54, 17);
			}
			{
				cuit = new JLabel();
				getContentPane().add(cuit);
				cuit.setText("CUIT del proveedor:");
				cuit.setBounds(12, 161, 124, 17);
			}
			{
				tCuit = new JTextField();
				getContentPane().add(tCuit);
				tCuit.setBounds(148, 158, 144, 24);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Alta Material");		
		setSize(310,265);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		getContentPane().setLayout(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==agregar){
			if (!tCodigo.getText().equals("") && !tNombre.getText().equals("") && !tPuntoPedido.getText().equals("") && !tCantidad.getText().equals("") && !tCosto.getText().equals("") && !tCuit.getText().equals("")){
				try{
					Float.parseFloat(tCantidad.getText());
				} catch(Exception exep){
					JOptionPane.showMessageDialog(this.getComponent(0), "Cantidad inicial incorrecta.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					Float.parseFloat(tCosto.getText());
				} catch(Exception exep){
					JOptionPane.showMessageDialog(this.getComponent(0), "Costo unitario incorrecto.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					Float.parseFloat(tPuntoPedido.getText());
				} catch(Exception exep){
					JOptionPane.showMessageDialog(this.getComponent(0), "Punto de pedido incorrecto.","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Controlador.getControlador().altaMaterial(tCodigo.getText(), tNombre.getText(), Float.parseFloat(tPuntoPedido.getText()), tCuit.getText(), Float.parseFloat(tCantidad.getText()), Float.parseFloat(tCosto.getText()));
				Material m = Controlador.getControlador().obtenerMaterial(tCodigo.getText());
			
			}
			else
				JOptionPane.showMessageDialog(null, "Por favor complete correctamente los campos.","Error",JOptionPane.ERROR_MESSAGE);

		}
	}
}
