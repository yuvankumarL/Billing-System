
import java.sql.*;
import javax.swing.*;

public class billData {
	public static Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn_2 = DriverManager.getConnection("jdbc:sqlite:billData.db");
			JOptionPane.showMessageDialog(null, "Connection Made");
			return conn_2;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}
}
