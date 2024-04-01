
import java.sql.*;
import javax.swing.*;

public class foodData {
	public static Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:foodList.db");
			return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}
}
