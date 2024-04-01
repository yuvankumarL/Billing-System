import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class bill {

	private JFrame frame;
	@SuppressWarnings("rawtypes")
	public JList list;
	JTextArea textArea;
	DateTimeFormatter dtf;
	int second;
	int minutes;
	int hour;
	int ampm;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	Connection conn_2 = null;
	PreparedStatement pst_2 = null;
	ResultSet rs_2 = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bill window = new bill();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/*
	 * TIME
	 */

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
	
	private void billheader() {
		
		textArea.setText("---------------------------------------------------------------------------------------"+"\n"
				+"\t"+ "                       THE HEAVENLY " + "\n"
				+"\t No.163, Gandhi Nagar, Main Road, Gudiyatham."+"\n"
				+"PHONE: 9159929203" + "\n"
				+"GSTNO: " +"\n---------------------------------------------------------------------------------------"+"\n"+
				"FOOD NAME                                         "+" PRICE  "+" QTY  "+" AMOUNT"+
				"\n---------------------------------------------------------------------------------------"+
				"\n");
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
	
	@SuppressWarnings("rawtypes")
	DefaultListModel defaultListModel = new DefaultListModel();
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_4;
	int i = 0;
	private JTextField textField_6;
	private JTextField billAmttxt;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton_1;
	ArrayList food;
	private JTextField textField_2;
	
	public bill() {
		initialize();
		this.binddata();
		billheader();
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

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1383, 739);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(842, 749, 518, -736);
		frame.getContentPane().add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(0, 0, 841, 700);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

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

		JLabel lblNewLabel_5_5 = new JLabel(
				"--------------------------------------------------------------------------------------------------------------------------------------------");
		lblNewLabel_5_5.setFont(new Font("Sitka Heading", Font.PLAIN, 18));
		lblNewLabel_5_5.setBounds(10, 216, 841, 22);
		panel_1.add(lblNewLabel_5_5);

		JLabel lblNewLabel_15 = new JLabel("FOOD NAME : ");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_15.setBounds(35, 298, 121, 38);
		panel_1.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("QUANTITY : ");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_16.setBounds(48, 412, 95, 38);
		panel_1.add(lblNewLabel_16);

		

		int key = 0;
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				searchFilter(textField_3.getText());
				int key = e.getKeyCode();
				if (key == 10) {
					textField_4.requestFocus();
				}
			}
		});
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(152, 298, 279, 38);
		panel_1.add(textField_3);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(440, 280, 391, 409);
		panel_1.add(scrollPane_4);

		list = new JList();
		list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textField_3.setText((String) list.getSelectedValue());
				textField_4.requestFocus();
				
			}
		});
		scrollPane_4.setViewportView(list);
		list.setBackground(Color.WHITE);
		
		textField_4 = new JTextField();
		textField_4.setText(null);
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
					
					
					JOptionPane.showMessageDialog(scrollPane_4, "rate = " + rate);
					
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
		textField_4.setBounds(153, 412, 105, 34);
		panel_1.add(textField_4);
		
		
		JLabel lblNewLabel_17 = new JLabel("TOTAL : ");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_17.setBounds(81, 473, 78, 31);
		panel_1.add(lblNewLabel_17);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == 10) {
					btnNewButton_1.requestFocus();
				}
			}
		});
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(153, 470, 105, 34);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(153, 356, 105, 34);
		panel_1.add(textField_6);
		
		JLabel lblNewLabel_17_1 = new JLabel("PRICE : ");
		lblNewLabel_17_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_17_1.setBounds(83, 359, 67, 31);
		panel_1.add(lblNewLabel_17_1);
		
		JButton btnNewButton = new JButton("PRINT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql = "INSERT INTO billData(billno,foodname,quantity,price,total,billamt)"
							+ "VALUES(?,?,?,?,?,?)";
					pst_2 = billData.ConnectDB().prepareStatement(sql);
					String val1 = textField_2.getText();
					pst_2.setString(1, val1);
					pst_2.setString(2, "");
					pst_2.setString(3, "");
					pst_2.setString(4, "");
					pst_2.setString(5, "");
					String val2 = billAmttxt.getText();
					pst_2.setString(6, val2);
					pst_2.execute();
					
				} catch(Exception ev) {
					JOptionPane.showMessageDialog(null, "Connection Error!");
				}
				
				billNo();
				textArea.setText(textArea.getText() + "---------------------------------------------------------------------------------------"
						+"\n"+"Total: "+ textField.getText() + "                                                                     " 
						+ textField_1.getText()+"          "+ billAmttxt.getText() + "\n"
						+"---------------------------------------------------------------------------------------"+"\n"
						+"Bill Amt :  " + billAmttxt.getText()+ "\n\n" + 
						"Paid Amt :  " + billAmttxt.getText()+"\t\t" + "Returned Amt :  0.00" + "\n\n"
						+""+"\n\n"
						+"	          THANK YOU. VIST AGAIN !!!" + "\n");
				
				Thread clock = new Thread() {
					public void run() {
						try {
							for (;;) {
								Calendar cal = new GregorianCalendar();

								int second = cal.get(Calendar.SECOND);
								int minutes = cal.get(Calendar.MINUTE);
								int hour = cal.get(Calendar.HOUR);
								int ampm = cal.get(Calendar.AM_PM);
								String am_pm;
								if (ampm == 0) {
									am_pm = "AM";
								} else {
									am_pm = "PM";
								}

								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								LocalDateTime now = LocalDateTime.now();
								String datetext_3= "    "+dtf.format(now) + "	                                                              "
								+ "  " + hour + ":" + minutes + ":" + second + " " + am_pm +"\n";
								textArea.append(datetext_3);
								sleep(70000);
								String textDate_2 = datetext_3;
								
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
				clock.start();
				
				try {
					textArea.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				clock.stop();
				
			}
		});
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(62, 536, 111, 38);
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.addActionListener(new ActionListener() {
			int bill_amt = 0, qty_2 = 0;
			int click;
			public void actionPerformed(ActionEvent e) {
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
				
				textArea.append(textField_3.getText());
				
				for(int j = n;j<56-sub;++j) {
					textArea.append(fill);
					System.out.print(fill);
				}
				textArea.append(textField_6.getText()+"          "+textField_4.getText() +"          "+textField_5.getText()+ "\n");
				
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
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.setBounds(249, 536, 111, 38);
		panel_1.add(btnNewButton_1);
		
		billAmttxt = new JTextField();
		billAmttxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		billAmttxt.setBounds(153, 658, 105, 31);
		panel_1.add(billAmttxt);
		billAmttxt.setColumns(10);
		
		textField = new JTextField();
		textField.setBackground(new Color(204, 255, 204));
		textField.setBounds(21, 647, 5, 3);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(204, 255, 204));
		textField_1.setColumns(10);
		textField_1.setBounds(21, 661, 5, 3);
		panel_1.add(textField_1);
		
		JButton resetbtn = new JButton("RESET");
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				billheader();
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				billAmttxt.setText("");
			}
		});
		resetbtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		resetbtn.setBounds(249, 597, 111, 38);
		panel_1.add(resetbtn);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnClear.setBounds(62, 595, 111, 40);
		panel_1.add(btnClear);
		
		textField_2 = new JTextField();
		textField_2.setBounds(20, 243, 95, 24);
		panel_1.add(textField_2);
		textField_2.setText(null);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("VIEW");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add info = new add();
				add.main(null);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setBounds(10, 182, 95, 38);
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(842, 0, 525, 700);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		billNo();
		
	}
}
