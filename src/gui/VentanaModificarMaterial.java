package gui;
import implementacion.Material;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.MaterialView;
import controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaModificarMaterial extends javax.swing.JFrame implements ActionListener{
	private JComboBox materiales;
	private JLabel nombre;
	private JTextField tNombre;
	private JTextField tCuit;
	private JTextField tCosto;
	private JTextField tCantidad;
	private JTextField tPuntoPedido;
	private JButton modificar;
	private JLabel cuit;
	private JLabel costo;
	private JLabel cantidad;
	private JLabel puntoPedido;
	private JLabel codigo;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		new VentanaModificarMaterial();
	}
	
	public VentanaModificarMaterial() {
		comportamiento();
		componentes();
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Modificar Material");		
		this.setSize(338, 271);
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
				for (MaterialView m : Controlador.getControlador().getMaterialesView())
					materiales.addItem(m.getCodigo());
				materiales.setBounds(112, 16, 204, 24);
				materiales.addActionListener(this);
				materiales.setSelectedIndex(-1);
			}
			{
				nombre = new JLabel();
				getContentPane().add(nombre);
				nombre.setText("Nombre:");
				nombre.setBounds(12, 48, 54, 17);
			}
			{
				codigo = new JLabel();
				getContentPane().add(codigo);
				codigo.setText("Código:");
				codigo.setBounds(12, 19, 54, 17);
			}
			{
				puntoPedido = new JLabel();
				getContentPane().add(puntoPedido);
				puntoPedido.setText("Punto pedido:");
				puntoPedido.setBounds(12, 77, 106, 17);
			}
			{
				cantidad = new JLabel();
				getContentPane().add(cantidad);
				cantidad.setText("Cantidad:");
				cantidad.setBounds(12, 106, 59, 17);
			}
			{
				costo = new JLabel();
				getContentPane().add(costo);
				costo.setText("Costo unitario:");
				costo.setBounds(12, 135, 100, 17);
			}
			{
				cuit = new JLabel();
				getContentPane().add(cuit);
				cuit.setText("CUIT del proveedor:");
				cuit.setBounds(12, 164, 124, 17);
			}
			{
				modificar = new JButton();
				getContentPane().add(modificar);
				modificar.setText("Modificar");
				modificar.setBounds(220, 204, 96, 24);
				modificar.addActionListener(this);
			}
			{
				tNombre = new JTextField();
				getContentPane().add(tNombre);
				tNombre.setBounds(112, 45, 204, 24);
			}
			{
				tPuntoPedido = new JTextField();
				getContentPane().add(tPuntoPedido);
				tPuntoPedido.setBounds(112, 74, 204, 24);
			}
			{
				tCantidad = new JTextField();
				getContentPane().add(tCantidad);
				tCantidad.setBounds(112, 103, 204, 24);
			}
			{
				tCosto = new JTextField();
				getContentPane().add(tCosto);
				tCosto.setBounds(112, 132, 204, 24);
			}
			{
				tCuit = new JTextField();
				getContentPane().add(tCuit);
				tCuit.setBounds(148, 161, 168, 24);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==materiales){
			if (materiales.getSelectedItem()!=null){
				Material material = Controlador.getControlador().obtenerMaterial(materiales.getSelectedItem().toString());
				//MaterialView material = m.generarMaterialView();
				tNombre.setText(material.getNombre());
				tPuntoPedido.setText(Float.toString(material.getPuntoPedido()));
				tCantidad.setText(Float.toString(material.getCantidad()));
				tCosto.setText(Float.toString(material.getCosto()));
				tCuit.setText(material.getProveedor().getCuit());				
			}
		}
		if (e.getSource()==modificar){
			if (materiales.getSelectedItem()!=null && !tNombre.getText().equals("") && !tPuntoPedido.getText().equals("") && !tCantidad.getText().equals("") && !tCosto.getText().equals("") && !tCuit.getText().equals("")){
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
				MaterialView matView = new MaterialView(materiales.getSelectedItem().toString(), tNombre.getText(), Float.parseFloat(tPuntoPedido.getText()), tCuit.getText(), Float.parseFloat(tCantidad.getText()), Float.parseFloat(tCosto.getText()));
				Controlador.getControlador().modificarMaterial(matView);
			}
			else 					
				JOptionPane.showMessageDialog(this.getComponent(0), "Por favor seleccione un material y complete correctamente los campos.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
