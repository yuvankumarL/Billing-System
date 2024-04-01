import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList food;
	Connection conn_2 = null;
	PreparedStatement pst_2 = null;
	ResultSet rs_2 = null;
	JList list;
	DefaultListModel defaultListModel = new DefaultListModel();
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_6;
	public JTextField textField_5;
	public JTextField textField_2;
	public JTextField billAmttxt;
	private JTextField textField_1;
	private JTextField textField;
	
	public void fillTextArea() {
		String sql = "SELECT * FROM foodList";
		try {
			pst = foodData.ConnectDB().prepareStatement(sql);
			rs = pst.executeQuery();
			DefaultListModel DLM = new DefaultListModel();
			while(rs.next()) {
				DLM.addElement(rs.getString("foodname"));
			}
			list.setModel(DLM);
			pst.close();
			rs.close();
		} catch(Exception ev) {
			ev.printStackTrace();
		}
	}
	
	private ArrayList getFood() {
		String sql = "SELECT * FROM foodList";
		try {
			pst = foodData.ConnectDB().prepareStatement(sql);
			rs = pst.executeQuery();
			food = new ArrayList();
			while(rs.next()) {
				food.add(rs.getString("foodname"));
			}
			pst.close();
			rs.close();
		} catch(Exception ev) {
			ev.printStackTrace();
		}
		return food;
	}
	
	@SuppressWarnings("unchecked")
	private void binddata() {
		// TODO Auto-generated method stub
		getFood().stream().forEach((foods) -> {
			defaultListModel.addElement(foods);
		});

		list.setModel(defaultListModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@SuppressWarnings("unchecked")
	private void searchFilter(String searchTerm) {
		@SuppressWarnings("rawtypes")
		DefaultListModel filteredItems = new DefaultListModel();
		@SuppressWarnings("rawtypes")
		ArrayList food = getFood();

		food.stream().forEach((foods) -> {
			String foodName = foods.toString().toLowerCase();
			if (foodName.contains(searchTerm.toLowerCase())) {
				filteredItems.addElement(foods);
			}
		});
		defaultListModel = filteredItems;
		list.setModel(defaultListModel);
	}
	
	public void billNo() {
		String sql = "SELECT MAX(billno) FROM billData;";
		try {
			pst_2 = billData.ConnectDB().prepareStatement(sql);
			rs_2 = pst_2.executeQuery();
			rs_2.next();
			rs_2.getString("MAX(billno)");
			
			if(rs_2.getString("MAX(billno)") == null) {
				textField_2.setText("000001");
			} else {
				int id = Integer.parseInt(rs_2.getString("MAX(billno)"));
				id = id + 1;
				textField_2.setText(String.valueOf(id));
			}
			pst_2.close();
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}
	/**
	 * Create the application.
	 */
	public Customer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1383, 739);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(0, 0, 760, 700);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(101, 123, 649, 158);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel Label = new JLabel("");
		Label.setBounds(10, 11, 187, 140);
		panel_2.add(Label);
		Image img = new ImageIcon(this.getClass().getResource("/images/hotel.jpeg")).getImage();
		Label.setIcon(new ImageIcon(img));
		
		JLabel lblNewLabel_13 = new JLabel("     THE HEAVENLY");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_13.setBackground(Color.WHITE);
		lblNewLabel_13.setBounds(220, 11, 346, 53);
		panel_2.add(lblNewLabel_13);
		
		JTextArea txtrNoGandhi = new JTextArea();
		txtrNoGandhi.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrNoGandhi.setText("No. 163, Gandhi Nagar, Main Road, Gudiyatham.");
		txtrNoGandhi.setBounds(220, 62, 419, 25);
		panel_2.add(txtrNoGandhi);
		
		JTextArea txtrBriyanichinesenorth = new JTextArea();
		txtrBriyanichinesenorth.setText("Briyani  *Chinese  *North Indian");
		txtrBriyanichinesenorth.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrBriyanichinesenorth.setBounds(220, 90, 419, 31);
		panel_2.add(txtrBriyanichinesenorth);
		
		JButton btnNewButton = new JButton("ORDER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binddata();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(544, 122, 95, 25);
		panel_2.add(btnNewButton);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(101, 297, 649, 163);
		panel.add(panel_2_1);
		
		JLabel Label_1 = new JLabel("");
		Label_1.setIcon(new ImageIcon(img));
		Label_1.setBounds(10, 11, 187, 140);
		panel_2_1.add(Label_1);
		
		JLabel lblNewLabel_13_1 = new JLabel("     TASTE & FUN");
		lblNewLabel_13_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_13_1.setBackground(Color.WHITE);
		lblNewLabel_13_1.setBounds(220, 21, 346, 53);
		panel_2_1.add(lblNewLabel_13_1);
		
		JTextArea txtraCongress = new JTextArea();
		txtraCongress.setText("#51, 21a, Congress House Rd, Pudupet, Gudiyattam");
		txtraCongress.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtraCongress.setBounds(207, 71, 442, 25);
		panel_2_1.add(txtraCongress);
		
		JTextArea txtrMulticuisinechinesenorth = new JTextArea();
		txtrMulticuisinechinesenorth.setText("Multicuisine  *Chinese  *North Indian");
		txtrMulticuisinechinesenorth.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrMulticuisinechinesenorth.setBounds(220, 100, 419, 25);
		panel_2_1.add(txtrMulticuisinechinesenorth);
		
		JButton btnNewButton_2 = new JButton("ORDER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binddata();
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_2.setBounds(544, 126, 95, 25);
		panel_2_1.add(btnNewButton_2);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(Color.WHITE);
		panel_2_2.setBounds(101, 482, 649, 158);
		panel.add(panel_2_2);
		
		JLabel Label_2 = new JLabel("");
		Label_2.setIcon(new ImageIcon(img));
		Label_2.setBounds(10, 11, 187, 140);
		panel_2_2.add(Label_2);
		
		JLabel lblNewLabel_13_2 = new JLabel("     MEAT AND EAT");
		lblNewLabel_13_2.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_13_2.setBackground(Color.WHITE);
		lblNewLabel_13_2.setBounds(220, 25, 346, 53);
		panel_2_2.add(lblNewLabel_13_2);
		
		JTextArea txtraRsRoad = new JTextArea();
		txtraRsRoad.setText("18A, R.S road, near Ambedkar Statue, Gudiyattam");
		txtraRsRoad.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtraRsRoad.setBounds(207, 76, 432, 25);
		panel_2_2.add(txtraRsRoad);
		
		JTextArea txtrBurgerfriedChicken = new JTextArea();
		txtrBurgerfriedChicken.setText("Burger  *Fried chicken  *Pizza");
		txtrBurgerfriedChicken.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrBurgerfriedChicken.setBounds(220, 104, 287, 31);
		panel_2_2.add(txtrBurgerfriedChicken);
		
		JButton btnNewButton_3 = new JButton("ORDER");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binddata();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_3.setBounds(544, 126, 95, 25);
		panel_2_2.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(758, 0, 609, 700);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		list.setBackground(Color.WHITE);
		list.setBounds(90, 62, 429, 307);
		panel_1.add(list);
		
		JLabel lblNewLabel_15 = new JLabel("FOOD NAME : ");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_15.setBounds(113, 394, 121, 38);
		panel_1.add(lblNewLabel_15);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				searchFilter(textField_3.getText());
				int key = e.getKeyCode();
				if (key == 10) {
					textField_4.requestFocus();
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(230, 394, 279, 38);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_16 = new JLabel("QUANTITY : ");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_16.setBounds(126, 508, 95, 38);
		panel_1.add(lblNewLabel_16);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int rate = 0;
				int key = e.getKeyCode();
				if (key == 10) {
					textField_5.requestFocus();
					String slt = (String) list.getSelectedValue();
					System.out.println(slt);
					System.out.println(list.getModel().getSize());
					try {
						String sql = "SELECT * FROM foodList WHERE foodname = '"
								+ slt + "';";
						pst = foodData.ConnectDB().prepareStatement(sql);
						rs = pst.executeQuery();
						while(rs.next()) {
							rate = rs.getInt("rate");
						}
					} catch(Exception ev) {
						JOptionPane.showMessageDialog(null, "Error rate!");
					}
					JOptionPane.showMessageDialog(null, "rate = " + rate);
					String rt = String.valueOf(rate);
					textField_6.setText(rt);
					int qty = Integer.parseInt(textField_4.getText().toString());
					int price = qty * rate;
					String pr = String.valueOf(price);
					textField_5.setText(pr);
				}
			}
		});
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(231, 508, 105, 34);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_17_1 = new JLabel("PRICE : ");
		lblNewLabel_17_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_17_1.setBounds(161, 455, 67, 31);
		panel_1.add(lblNewLabel_17_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(231, 452, 105, 34);
		panel_1.add(textField_6);
		
		JLabel lblNewLabel_17 = new JLabel("TOTAL : ");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_17.setBounds(159, 569, 78, 31);
		panel_1.add(lblNewLabel_17);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(231, 566, 105, 34);
		panel_1.add(textField_5);
		
		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order info = new Order();
				info.main(null);
				System.out.println(info.textArea1.getText());
				int bill_amt = 0, qty_2 = 0;
				int click = 0;
					System.out.println(textField_2);
					String slt = (String) list.getSelectedValue();
					int sub = 0;
					try {
						String sql = "SELECT * FROM foodList WHERE foodname = '"
								+ slt + "';";
						pst = foodData.ConnectDB().prepareStatement(sql);
						rs = pst.executeQuery();

						while(rs.next()) {
							sub = rs.getInt("sub");
							System.out.println(sub);
						}
					} catch(Exception ev) {
						ev.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error in Adding data!");
					}
					
					String fill = " ";
					int n = textField_3.getText().length();
					try {
					String str = info.textArea1.getText();
					info.textArea1.setText(str + textField_3.getText());
					System.out.println(str + textField_3.getText());
					String str_2 = info.textArea1.getText();
					for(int j = n;j<56-sub;++j) {
						info.textArea1.setText(str_2 + fill);
						System.out.print(fill);
					}
					} catch(Exception ev) {
						ev.printStackTrace();
					}
					
					try {
					String str_3 = info.textArea1.getText();
					info.textArea1.setText(str_3 + textField_6.getText()+"          "+textField_4.getText() +
							"          "+textField_5.getText()+ "\n");
					} catch(Exception ev) {
						ev.printStackTrace();
					}
					String v3 = textField_3.getText();
					String v4 = textField_4.getText();
					String v5 = textField_5.getText();
					String v6 = textField_6.getText();
				
					try{
						if(v3.equals("") || v4.equals("") || v5.equals("") || v6.equals("")) {
							JOptionPane.showMessageDialog(null, "Enter the complete data");
						} else if (v3.equals("")) {
							JOptionPane.showMessageDialog(null, "Enter Food Name");
						} else if(v4.equals("")) {
							JOptionPane.showMessageDialog(null, "Enter the Quantity");
						} else {
							click += 1;
							int total = Integer.parseInt(textField_5.getText());
							bill_amt = bill_amt + total;
							
							int quantity_1 = Integer.parseInt(textField_4.getText());
							qty_2 = qty_2 + quantity_1;
							
							String ba = String.valueOf(bill_amt);
							billAmttxt.setText(ba);
							String clk = String.valueOf(click);
							textField.setText(clk);
							String qt = String.valueOf(qty_2);
							textField_1.setText(qt);
						}
					} catch(Exception ev) {
						ev.printStackTrace();				
					}
					
					try {
						
						String sql_2 = "INSERT INTO billData(billno,foodname,quantity,price,total) "
								+ "VALUES(?,?,?,?,?);";
						pst_2 = billData.ConnectDB().prepareStatement(sql_2);
						String val1 = textField_2.getText();
						pst_2.setString(1, val1);
						String val2 =  textField_3.getText();
						pst_2.setString(2,val2);
						String val3 = textField_4.getText();
						pst_2.setString(3, val3);
						String val4 = textField_6.getText();
						pst_2.setString(4, val4);
						String val5 = textField_5.getText();
						pst_2.setString(5, val5);
						String val6 = billAmttxt.getText();
						pst_2.execute();
						System.out.println(val6);
						textField_3.setText(null);
						textField_4.setText(null);
						textField_5.setText(null);
						textField_6.setText(null);
					} catch(Exception ev) {
						ev.printStackTrace();
					}
				}
			}
		);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.setBounds(327, 632, 111, 38);
		panel_1.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_2.setText((String) null);
		textField_2.setColumns(10);
		textField_2.setBounds(588, 518, 11, 7);
		panel_1.add(textField_2);
		
		billAmttxt = new JTextField();
		billAmttxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		billAmttxt.setBounds(476, 482, 7, 20);
		panel_1.add(billAmttxt);
		billAmttxt.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_1.setBounds(508, 540, 11, 6);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setBounds(544, 575, 7, 7);
		panel_1.add(textField);
		textField.setColumns(10);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_3.setText((String) list.getSelectedValue());
				textField_4.requestFocus();				
			}
		});
		billNo();
	}
}