package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import controlador.Controlador;

import java.awt.Font;

public class VentanaConfirmarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public VentanaConfirmarFactura(final int nroFactura, final float totalFactura) {
		setBounds(100, 100, 450, 134);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel totalLabel = new JLabel("Total: " + Float.toString(totalFactura));
			totalLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			totalLabel.setBounds(103, 26, 107, 27);
			contentPanel.add(totalLabel);
		}
		
		JLabel confirmarLabel = new JLabel("¿Confirmar?");
		confirmarLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		confirmarLabel.setBounds(241, 31, 107, 16);
		contentPanel.add(confirmarLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmarButton = new JButton("Confirmar");
				confirmarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Controlador.getControlador().confirmarFactura(nroFactura);
					}
				});
				buttonPane.add(confirmarButton);
				getRootPane().setDefaultButton(confirmarButton);
			}
		}
	}
}
