package modelo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Sesion extends JFrame {

	private static final long serialVersionUID=1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sesion frame=new Sesion();
					frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sesion() {
		setTitle("Apex Depósitos S.A. - Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,320);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane=new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo=new JLabel("INICIO DE SESIÓN");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,18));
		lblTitulo.setBounds(0,30,384,30);
		contentPane.add(lblTitulo);

		JLabel lblUsuario=new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		lblUsuario.setBounds(90,90,200,14);
		contentPane.add(lblUsuario);

		txtUsuario=new JTextField();
		txtUsuario.setBounds(90,110,200,25);
		contentPane.add(txtUsuario);

		JLabel lblPassword=new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		lblPassword.setBounds(90,150,200,14);
		contentPane.add(lblPassword);

		txtPassword=new JPasswordField();
		txtPassword.setBounds(90,170,200,25);
		contentPane.add(txtPassword);

		JButton btnIngresar=new JButton("Ingresar");
		btnIngresar.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,13));
		btnIngresar.setFocusPainted(false);
		btnIngresar.setBounds(130,225,120,35);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user=txtUsuario.getText();
				String pass=new String(txtPassword.getPassword());

				if(user.equals("admin")&&pass.equals("1616")) {
					Ventana_Principal principal=new Ventana_Principal();
					principal.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos inetenta denuevo.");
				}
			}
		});
		contentPane.add(btnIngresar);
	}
}