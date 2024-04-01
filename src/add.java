import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class add {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	JTable table;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add window = new add();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getData(String val1, String val2, String val3, String val4, String val5, String val6) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {
				val1, val2, val3, val4, val5, val6
		});
		table.setModel(model);
	}
	
	public void updateTable() {
		if (conn != null) {
			try {
				String sql = "SELECT billno,foodname,quantity,price,total,billAmt FROM billData";
				pst = billData.ConnectDB().prepareStatement(sql);
				rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	
	public void fillTextArea() {
		String sql = "SELECT * FROM foodList";
		try {
			
			pst = foodData.ConnectDB().prepareStatement(sql);
			rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next()) {
				DLM.addElement(rs.getString("foodname"));
			}
			pst.close();
			rs.close();
			
		} catch(Exception ev) {
			ev.printStackTrace();
		}
	}
	
	/**
	 * Create the application.
	 */
	public add() {
		initialize();
		conn = foodData.ConnectDB();
		fillTextArea();
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1383, 739);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(0, 0, 669, 700);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_13 = new JLabel("THE HEAVENLY");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_13.setBackground(Color.WHITE);
		lblNewLabel_13.setBounds(252, 26, 346, 53);
		panel_1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_1_1 = new JLabel("TN23 SPOT");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblNewLabel_1_1.setBounds(388, 68, 268, 31);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel address_1 = new JLabel("No. 163, Gandhi Nagar, Main Road, Gudiyatham.");
		address_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		address_1.setBounds(168, 99, 523, 53);
		panel_1.add(address_1);
		
		JLabel phone_1 = new JLabel(" Phone:  +91 91599 29203");
		phone_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		phone_1.setBackground(Color.WHITE);
		phone_1.setBounds(319, 146, 193, 38);
		panel_1.add(phone_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("GSTIN:  ");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(319, 182, 193, 38);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_5_5 = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------");
		lblNewLabel_5_5.setFont(new Font("Sitka Heading", Font.PLAIN, 18));
		lblNewLabel_5_5.setBounds(10, 216, 841, 22);
		panel_1.add(lblNewLabel_5_5);
		
		JLabel lblNewLabel_15 = new JLabel("FOOD NAME : ");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_15.setBounds(35, 298, 121, 38);
		panel_1.add(lblNewLabel_15);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(152, 298, 279, 38);
		panel_1.add(textField);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(153, 375, 105, 34);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_17_1 = new JLabel("PRICE : ");
		lblNewLabel_17_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_17_1.setBounds(83, 378, 67, 31);
		panel_1.add(lblNewLabel_17_1);
		
		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = textField.getText();
				int rate = Integer.parseInt(textField_3.getText().toString());
				String sql = "INSERT INTO foodList(foodname,rate) "
						+ "VALUES(?,?)";
				
				try {
					pst = foodData.ConnectDB().prepareStatement(sql);
					pst.setString(1, Name);
					pst.setInt(2, rate);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Added Sucessfully!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.setBounds(252, 465, 111, 38);
		panel_1.add(btnNewButton_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(153, 658, 105, 31);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(204, 255, 255));
		textField_5.setBounds(21, 636, 5, 14);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBackground(new Color(204, 255, 255));
		textField_6.setBounds(21, 661, 5, 14);
		panel_1.add(textField_6);
		
		JButton resetbtn = new JButton("RESET");
		resetbtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		resetbtn.setBounds(70, 465, 111, 38);
		panel_1.add(resetbtn);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = textField.getText();
				int rate = Integer.parseInt(textField_3.getText().toString());
				String sql = "UPDATE foodList SET foodname =  " +"\""+Name
						+"\""+ "price = " +"\""+rate+"\""+"sub = 0 WHERE foodname = v";
				
				try {
					pst = foodData.ConnectDB().prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Added Sucessfully!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUpdate.setBounds(252, 532, 111, 38);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = textField.getText();
				int rate = Integer.parseInt(textField_3.getText().toString());
				String sql = "INSERT INTO foodList(foodname,rate) "
						+ "VALUES(?,?)";
				
				try {
					pst = foodData.ConnectDB().prepareStatement(sql);
					pst.setString(1, Name);
					pst.setInt(2, rate);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Added Sucessfully!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setBounds(70, 532, 111, 38);
		panel_1.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(668, 0, 699, 700);
		frame.getContentPane().add(scrollPane);
		
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"BILL_NO", "FOOD NAME", "QUANTITY", "PRICE", "TOTAL", "BILL_AMT"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(57);
		table.getColumnModel().getColumn(4).setPreferredWidth(53);
		table.getColumnModel().getColumn(5).setPreferredWidth(68);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
	}
}
