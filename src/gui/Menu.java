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

public class Menu extends JFrame implements ActionListener{
	
	private JMenuBar menubar;
	private JMenu prendas, materiales, proveedores;
	private JMenuItem altaPrendaTemporada, altaPrendaNoTemporada, altaConjunto, bajaPrenda, modificarPrendaTemporada, modificarPrendaNoTemporada,
	modificarConjunto, listarPrenda, altaProveedor, bajaProveedor, modificarProveedor, listarProveedor, altaMaterial, 
	bajaMaterial, modificarMaterial, listarMaterial;
	private JLabel imagen;
	
	
	public Menu(){
		componentes();		
		comportamiento();
	}
	
	private void componentes(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		imagen = new JLabel();
		imagen.setIcon(new ImageIcon( getClass().getResource("/img/kvn.jpg")));
		
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
			
		}
		if (e.getSource()==modificarPrendaTemporada){
			
		}
		if (e.getSource()==modificarPrendaNoTemporada){
			
		}
		if (e.getSource()==modificarConjunto){
			
		}
		if (e.getSource()==bajaPrenda){
			new VentanaBajaPrenda();
		}
		if (e.getSource()==listarPrenda){
			new VentanaListaPrendas(); //anda pero le faltarian unas cosas
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
	}
		
	public static void main(String[] args){
		new Menu();
	}

}
