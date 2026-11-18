package modelo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaEmpleados extends JFrame implements ActionListener {

	private static final long serialVersionUID=1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JComboBox<String> cmbTipo;
	private JButton btnRegistrar;
	private JButton btnFiltro1;
	private JButton btnFiltro2;
	private JButton btnVolver;
	private JTextArea txtLista;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleados frame=new VentanaEmpleados();
					frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaEmpleados() {
		setTitle("Registro y Filtrado de Empleados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,620,370); 
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane=new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre=new JLabel("Nombre del Empleado:");
		lblNombre.setFont(new Font("Tahoma",Font.BOLD,11));
		lblNombre.setBounds(15,25,160,14);
		contentPane.add(lblNombre);

		txtNombre=new JTextField();
		txtNombre.setBounds(15,43,200,23);
		contentPane.add(txtNombre);

		JLabel lblCodigo=new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma",Font.BOLD,11));
		lblCodigo.setBounds(15,78,140,14);
		contentPane.add(lblCodigo);

		txtCodigo=new JTextField();
		txtCodigo.setBounds(15,95,200,23);
		contentPane.add(txtCodigo);

		JLabel lblTipo=new JLabel("Tipo de Empleado:");
		lblTipo.setFont(new Font("Tahoma",Font.BOLD,11));
		lblTipo.setBounds(15,130,140,14);
		contentPane.add(lblTipo);

		cmbTipo=new JComboBox<>(new String[]{"1","2"});
		cmbTipo.setBounds(15,147,200,23);
		contentPane.add(cmbTipo);

		btnRegistrar=new JButton("Registrar");
		btnRegistrar.setBounds(15,195,200,30);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		btnFiltro1=new JButton("Gerente");
		btnFiltro1.setBounds(15,235,95,30);
		btnFiltro1.addActionListener(this);
		contentPane.add(btnFiltro1);

		btnFiltro2=new JButton("Empleado");
		btnFiltro2.setBounds(120,235,95,30);
		btnFiltro2.addActionListener(this);
		contentPane.add(btnFiltro2);

		btnVolver=new JButton("Volver");
		btnVolver.setBounds(15,275,200,30);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		JLabel lblLista=new JLabel("Lista de Empleados:");
		lblLista.setFont(new Font("Tahoma",Font.BOLD,11));
		lblLista.setBounds(235,25,350,14);
		contentPane.add(lblLista);

		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(235,43,355,262);
		contentPane.add(scrollPane);

		txtLista=new JTextArea();
		txtLista.setEditable(false);
		txtLista.setFont(new Font("Monospaced",Font.PLAIN,11));
		scrollPane.setViewportView(txtLista);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrar) {
			String nom=txtNombre.getText();
			String cod=txtCodigo.getText();
			
			if(nom.isEmpty()||cod.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Complete los campos.");
				return;
			}
			
			int tip=Integer.parseInt(cmbTipo.getSelectedItem().toString());
			
			try {
				Connection cn=Conexion.conectar();
				if(cn!=null) {
					PreparedStatement check=cn.prepareStatement("SELECT codigo FROM empleados WHERE codigo=?");
					check.setString(1,cod);
					ResultSet rs=check.executeQuery();
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(this,"Error: El código de empleado ya existe.");
					} else {
						PreparedStatement pst=cn.prepareStatement("INSERT INTO empleados VALUES(?,?,?)");
						pst.setString(1,cod);
						pst.setString(2,nom);
						pst.setInt(3,tip);
						pst.executeUpdate();
						
						txtNombre.setText("");
						txtCodigo.setText("");
						JOptionPane.showMessageDialog(this,"Empleado registrado con éxito.");
					}
					cn.close();
				}
			} catch(Exception ex) {
				System.out.println("Error al guardar empleado");
			}
			
		} else if(e.getSource()==btnFiltro1) {
			filtrarLista(1);
		} else if(e.getSource()==btnFiltro2) {
			filtrarLista(2);
		} else if(e.getSource()==btnVolver) {
			Ventana_Principal principal=new Ventana_Principal();
			principal.setVisible(true);
			dispose();
		}
	}

	private void filtrarLista(int tipo) {
		StringBuilder sb=new StringBuilder();
		try {
			Connection cn=Conexion.conectar();
			if(cn!=null) {
				PreparedStatement pst=cn.prepareStatement("SELECT nombre, codigo FROM empleados WHERE tipo=?");
				pst.setInt(1,tipo);
				ResultSet rs=pst.executeQuery();
				
				while(rs.next()) {
					sb.append(rs.getString("nombre")).append(" - Código: ").append(rs.getString("codigo")).append("\n");
				}
				txtLista.setText(sb.toString());
				cn.close();
			}
		} catch(Exception ex) {
			System.out.println("Error al leer empleados");
		}
	}
}