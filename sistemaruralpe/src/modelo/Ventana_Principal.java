package modelo;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Ventana_Principal extends JFrame {

	private static final long serialVersionUID=1L;
	private JPanel panelPrincipal;

	

	public Ventana_Principal() {
		ConfiguracionDeLaVentana();
		inicializarComponentes();
	}

	private void ConfiguracionDeLaVentana() {
		setTitle("Apex Depósitos S.A. - Sistema de Gestión v1.2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,480,430); // Aumenté el alto para que quepa el 3er botón
		setLocationRelativeTo(null);
		setResizable(false); 

		panelPrincipal=new JPanel();
		panelPrincipal.setBackground(Color.WHITE); 
		panelPrincipal.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	}

	private void inicializarComponentes() {
		JLabel etiquetaTituloEmpresa=new JLabel("APEX DEPÓSITOS S.A.");
		etiquetaTituloEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaTituloEmpresa.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,18));
		etiquetaTituloEmpresa.setBounds(0,20,464,30);
		panelPrincipal.add(etiquetaTituloEmpresa);
		
		JLabel etiquetaLogoApex=new JLabel("");
		etiquetaLogoApex.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLogoApex.setBounds(0,60,464,130);
		
		try {
			ImageIcon iconoBase=new ImageIcon(getClass().getResource("logo.png"));
			Image logoEscalado=iconoBase.getImage().getScaledInstance(220,120,Image.SCALE_SMOOTH);
			etiquetaLogoApex.setIcon(new ImageIcon(logoEscalado));
		} catch(Exception e) {
			System.err.println("Aviso:No se encontró la imagen del logo en la ruta esperada.");
			etiquetaLogoApex.setText("Apex Depósitos");
		}
		panelPrincipal.add(etiquetaLogoApex);
		
		JButton btnAccesoModuloInventario=new JButton("Ir a Gestión de Inventario");
		btnAccesoModuloInventario.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		btnAccesoModuloInventario.setFocusPainted(false); 
		btnAccesoModuloInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirInventario();
			}
		});
		btnAccesoModuloInventario.setBounds(112,205,240,38);
		panelPrincipal.add(btnAccesoModuloInventario);

		JButton btnAccesoModuloEmpleados=new JButton("Ir a Gestión de Empleados");
		btnAccesoModuloEmpleados.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		btnAccesoModuloEmpleados.setFocusPainted(false); 
		btnAccesoModuloEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirEmpleados();
			}
		});
		btnAccesoModuloEmpleados.setBounds(112,255,240,38);
		panelPrincipal.add(btnAccesoModuloEmpleados);

		JButton btnCerrarSesion=new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		btnCerrarSesion.setFocusPainted(false); 
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CerrarSesion();
			}
		});
		btnCerrarSesion.setBounds(112,305,240,38);
		panelPrincipal.add(btnCerrarSesion);
	}

	private void AbrirInventario() {
		Ventana_de_Inventario ventanaInv=new Ventana_de_Inventario();
		ventanaInv.setVisible(true);
		this.dispose();
	}

	private void AbrirEmpleados() {
		VentanaEmpleados ventanaEmp=new VentanaEmpleados(); 
		ventanaEmp.setVisible(true);
		this.dispose();
	}

	private void CerrarSesion() {
		Sesion ventanaSesion=new Sesion(); 
		ventanaSesion.setVisible(true); 
		this.dispose();
	}
}