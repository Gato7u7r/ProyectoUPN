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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Ventana_de_Inventario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textFieldMaterial;
	private JButton btnNewButton;
	private JButton btnVolver;
	private JLabel lblNewLabel_1;
	private JTextField textFieldCode;
	private JLabel lblNewLabel_2;
	private JTextField textFieldStock;
	private JTextArea textAreaReporte;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_de_Inventario frame = new Ventana_de_Inventario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana_de_Inventario() {
		setTitle("Apex Depósitos S.A. - Control y Reporte de Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 360);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Descripción del Material:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(15, 25, 160, 14);
		contentPane.add(lblNewLabel);

		textFieldMaterial = new JTextField();
		textFieldMaterial.setBounds(15, 43, 200, 23);
		contentPane.add(textFieldMaterial);
		textFieldMaterial.setColumns(10);

		lblNewLabel_1 = new JLabel("Código (5 carac.):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(15, 78, 140, 14);
		contentPane.add(lblNewLabel_1);

		textFieldCode = new JTextField();
		textFieldCode.setBounds(15, 95, 134, 23);
		contentPane.add(textFieldCode);
		textFieldCode.setColumns(10);

		lblNewLabel_2=new JLabel("Stock Inicial:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(15, 130, 100, 14);
		contentPane.add(lblNewLabel_2);

		textFieldStock = new JTextField();
		textFieldStock.setBounds(15, 147, 86, 23);
		contentPane.add(textFieldStock);
		textFieldStock.setColumns(10);

		btnNewButton=new JButton("Registrar");
		btnNewButton.setBounds(15, 195, 95, 30);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		btnVolver=new JButton("Volver");
		btnVolver.setBounds(120, 195, 95, 30);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		JLabel lblReporte=new JLabel("Reporte de Inventario en Tiempo Real:");
		lblReporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReporte.setBounds(235, 25, 350, 14);
		contentPane.add(lblReporte);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 43, 355, 260);
		contentPane.add(scrollPane);

		textAreaReporte = new JTextArea();
		textAreaReporte.setEditable(false);
		textAreaReporte.setFont(new Font("Monospaced", Font.PLAIN, 11));
		scrollPane.setViewportView(textAreaReporte);

		actualizarReporteFuncional();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		} else if (e.getSource() == btnVolver) {
			Ventana_Principal principal = new Ventana_Principal();
			principal.setVisible(true);
			dispose();
		}
	}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		String codigo = textFieldCode.getText();
		String nombreProd = textFieldMaterial.getText();
		int stock = Integer.parseInt(textFieldStock.getText());

		try {
			Connection cn = Conexion.conectar();
			if(cn != null) {
				PreparedStatement pst = cn.prepareStatement("INSERT INTO inventario VALUES(?,?,?)");
				pst.setString(1, codigo);
				pst.setString(2, nombreProd);
				pst.setInt(3, stock);
				pst.executeUpdate();
				cn.close();

				JOptionPane.showMessageDialog(this, "¡Material " + nombreProd + " registrado con éxito!");
				textFieldCode.setText("");
				textFieldMaterial.setText("");
				textFieldStock.setText("");
				
				actualizarReporteFuncional();
			}
		} catch (Exception ex) {
			System.out.println("Error al guardar en inventario: " + ex.getMessage());
			JOptionPane.showMessageDialog(this, "Error: El código ya existe o los datos son inválidos.");
		}
	}

	private void actualizarReporteFuncional() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== CATÁLOGO DE MATERIALES (INVENTARIO) ===\n");
		
		int cantidadStockAdecuado = 0;
		int stockTotal = 0;

		try {
			Connection cn = Conexion.conectar();
			if(cn != null) {
				PreparedStatement pst = cn.prepareStatement("SELECT * FROM inventario");
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					String cod = rs.getString("codigo");
					String desc = rs.getString("descripcion");
					int stock = rs.getInt("stock");
					
					sb.append("• ").append(cod).append(" - ").append(desc.toUpperCase()).append(" (").append(stock).append(" und.)\n");
					
					if (stock > 50) {
						cantidadStockAdecuado++;
					}
					stockTotal += stock;
				}
				cn.close();
			}
		} catch (Exception ex) {
			System.out.println("Error al leer el inventario");
		}

		sb.append("\n=== FILTRADO DEL STOCK (CANTIDAD MINIMA 50) ===\n");
		sb.append("Productos con stock > 50: ").append(cantidadStockAdecuado).append("\n");
		sb.append("\n=== STOCK TOTAL DENTRO DEL ALMACÉN ===\n");
		sb.append("Stock Total Acumulado: ").append(stockTotal).append(" unidades\n");

		textAreaReporte.setText(sb.toString());
	}
}