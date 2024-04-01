import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;

public class Order extends Customer{

	private JFrame frame;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order window = new Order();
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
	
	public static Order Instance;
	public JTextArea textArea1;
	public Order() {
		initialize();
		Instance = this;
		textArea1 = textArea;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea("---------------------------------------------------------------------------------------\n\t"
				+ "                       THE HEAVENLY \n\t No.163, Gandhi Nagar, Main Road, Gudiyatham.\nPHONE: "
				+ "9159929203\nGSTNO: \n---------------------------------------------------------------------------------------"
				+ "\nFOOD NAME                                          PRICE   QTY   AMOUNT\n"
				+ "---------------------------------------------------------------------------------------\n");
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textArea.setBounds(0, 0, 527, 706);
		frame.getContentPane().add(textArea);
	}
}