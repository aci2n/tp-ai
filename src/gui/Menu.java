package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
public class Menu extends JFrame implements ActionListener{
	
	private JMenuBar menubar;
	private JMenu prendas, materiales, proveedores;
	private JMenuItem altaPrendaTemporada, altaPrendaNoTemporada, altaConjunto, bajaPrenda, modificarPrendaTemporada, modificarPrendaNoTemporada,
	modificarConjunto, listarPrenda, altaProveedor, bajaProveedor, modificarProveedor, listarProveedor, altaMaterial, 
	bajaMaterial, modificarMaterial, listarMaterial;
	private JMenuItem miListarFacturas;
	private JMenuItem miGenerarFactura;
	private JMenu facturas;
	private JLabel imagen;
	
	
	public Menu(){
		componentes();		
		comportamiento();
	}
	
	private void componentes(){
		Controlador.getControlador().inicializar();
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		imagen = new JLabel();
		imagen.setIcon(new ImageIcon( getClass().getResource("/gui/kvn.jpg")));
		
		c.add(imagen,BorderLayout.CENTER);
		
		// PRENDAS
		prendas = new JMenu();
		prendas.setText("Prendas");
		menubar.add(prendas);
		
		altaPrendaTemporada = new JMenuItem();
		altaPrendaTemporada.setText("Alta Prenda con Temporada");
		prendas.add(altaPrendaTemporada);
		altaPrendaTemporada.addActionListener(this);
		
		altaPrendaNoTemporada = new JMenuItem();
		altaPrendaNoTemporada.setText("Alta Prenda sin Temporada");
		prendas.add(altaPrendaNoTemporada);
		altaPrendaNoTemporada.addActionListener(this);
		
		altaConjunto = new JMenuItem();
		altaConjunto.setText("Alta Conjunto");
		prendas.add(altaConjunto);
		altaConjunto.addActionListener(this);
		
		prendas.add(new JSeparator());
		
		modificarPrendaTemporada = new JMenuItem();
		modificarPrendaTemporada.setText("Modificar Prenda de Temporada");
		prendas.add(modificarPrendaTemporada);
		modificarPrendaTemporada.addActionListener(this);
		
		modificarPrendaNoTemporada = new JMenuItem();
		modificarPrendaNoTemporada.setText("Modificar Prenda sin Temporada");
		prendas.add(modificarPrendaNoTemporada);
		modificarPrendaNoTemporada.addActionListener(this);
		
		modificarConjunto = new JMenuItem();
		modificarConjunto.setText("Modificar Conjunto Prenda");
		prendas.add(modificarConjunto);
		modificarConjunto.addActionListener(this);
		
		prendas.add(new JSeparator());
		
		bajaPrenda = new JMenuItem();
		bajaPrenda.setText("Baja Prenda");
		prendas.add(bajaPrenda);
		bajaPrenda.addActionListener(this);
		
		prendas.add(new JSeparator());
		
		listarPrenda = new JMenuItem();
		listarPrenda.setText("Listar Prendas");
		prendas.add(listarPrenda);
		listarPrenda.addActionListener(this);
		
		//MATERIALES
		materiales = new JMenu();
		materiales.setText("Materiales");
		menubar.add(materiales);
		
		altaMaterial = new JMenuItem();
		altaMaterial.setText("Alta Material");
		materiales.add(altaMaterial);
		altaMaterial.addActionListener(this);
		
		materiales.add(new JSeparator());
		
		modificarMaterial = new JMenuItem();
		modificarMaterial.setText("Modificar Material");
		materiales.add(modificarMaterial);
		modificarMaterial.addActionListener(this);
		
		materiales.add(new JSeparator());
		
		bajaMaterial = new JMenuItem();
		bajaMaterial.setText("Baja Material");
		materiales.add(bajaMaterial);
		bajaMaterial.addActionListener(this);
		
		materiales.add(new JSeparator());
		
		listarMaterial = new JMenuItem();
		listarMaterial.setText("Listar Materiales");
		materiales.add(listarMaterial);
		listarMaterial.addActionListener(this);
		
		//PROVEEDORES
		
		proveedores = new JMenu();
		proveedores.setText("Proveedores");
		menubar.add(proveedores);
	
		altaProveedor = new JMenuItem();
		altaProveedor.setText("Alta Proveedor");
		proveedores.add(altaProveedor);
		altaProveedor.addActionListener(this);
		
		proveedores.add(new JSeparator());
		
		modificarProveedor = new JMenuItem();
		modificarProveedor.setText("Modificar Proveedor");
		proveedores.add(modificarProveedor);
		modificarProveedor.addActionListener(this);
		
		proveedores.add(new JSeparator());
		
		bajaProveedor = new JMenuItem();
		bajaProveedor.setText("Baja Proveedor");
		proveedores.add(bajaProveedor);
		bajaProveedor.addActionListener(this);
		
		proveedores.add(new JSeparator());
		
		listarProveedor = new JMenuItem();
		listarProveedor.setText("Listar Proveedores");
		proveedores.add(listarProveedor);
		listarProveedor.addActionListener(this);
		
		//FACTURAS
		
		facturas = new JMenu();
		menubar.add(facturas);
		facturas.setText("Facturas");

		miGenerarFactura = new JMenuItem();
		facturas.add(miGenerarFactura);
		miGenerarFactura.setText("Generar Factura");
		
		miListarFacturas = new JMenuItem();
		facturas.add(miListarFacturas);
		miListarFacturas.setText("Listar Facturas");
		miListarFacturas.addActionListener(this);

		miGenerarFactura.addActionListener(this);
	}
	
	private void comportamiento(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Sistema de Venta y Reposición de Indumentaria");		
		setSize(500,300);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
		
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==altaPrendaTemporada){
			new VentanaAltaPrendaConTemporada();
		}
		if (e.getSource()==altaPrendaNoTemporada){
			new VentanaAltaPrendaSinTemporada();
		}
		if (e.getSource()==altaConjunto){
			new VentanaAltaConjuntoPrenda();
		}
		if (e.getSource()==modificarPrendaTemporada){
			new VentanaModificarPrendaConTemporada();
		}
		if (e.getSource()==modificarPrendaNoTemporada){
			new VentanaModificarPrendaSinTemporada();
		}
		if (e.getSource()==modificarConjunto){
			new VentanaModificarConjuntoPrenda();
		}
		if (e.getSource()==bajaPrenda){
			new VentanaBajaPrenda();
		}
		if (e.getSource()==listarPrenda){
			new VentanaListaPrendas();
		}
		if (e.getSource()==altaMaterial){
			new VentanaAltaMaterial();
		}
		if (e.getSource()==modificarMaterial){
			new VentanaModificarMaterial();
		}
		if (e.getSource()==bajaMaterial){
			new VentanaBajaMaterial();
		}
		if (e.getSource()==listarMaterial){
			new VentanaListaMateriales();
		}
		if (e.getSource()==altaProveedor){
			new VentanaAltaProveedor();
		}
		if (e.getSource()==modificarProveedor){
			new VentanaModificarProveedor();
		}
		if (e.getSource()==bajaProveedor){
			new VentanaBajaProveedor();
		}		
		if (e.getSource()==listarProveedor){
			new VentanaListaProveedor();
		}
		if (e.getSource()==miGenerarFactura){
			new VentanaGenerarFactura();
		}
		if (e.getSource()==miListarFacturas){
			new VentanaListaFacturas();
		}
	}
		
	public static void main(String[] args){
		new Menu();
	}

}
