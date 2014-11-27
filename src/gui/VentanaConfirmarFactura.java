package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class VentanaConfirmarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public VentanaConfirmarFactura(final int nroFactura, final float totalFactura) {
		this.setModal(true);
		setTitle("Confirmar Factura");		
		setSize(450,134);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel totalLabel = new JLabel("Total: $" + Float.toString(totalFactura));
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
						Controlador.getControlador().confirmarFactura(nroFactura, true);
						dispose();
					}
				});
				buttonPane.add(confirmarButton);
				getRootPane().setDefaultButton(confirmarButton);
			}
		}
		
		WindowListener exitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	Controlador.getControlador().confirmarFactura(nroFactura, false);
				dispose();
            }
        };
        this.addWindowListener(exitListener);
		this.setVisible(true);
	}
}
