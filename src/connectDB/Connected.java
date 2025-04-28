package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connected {
	
	private static String dbName = "Cinema2";
	private static final String url = "jdbc:sqlserver://localhost\\CHITOAN:1433;databaseName=" + dbName + ";encrypt=false";
	private static final String user = "sa";
	private static final String password = "md140705";
	
	private static Connection con = null;
	
	//kết nối tới database
	public static Connection getConnection() {
		if(con == null) {
			try {
				con = DriverManager.getConnection(url,user,password);
				System.out.println("✅ Successfully 	connected to " + dbName + " database!");
			} catch (SQLException e) {
				System.err.println("❌ Connection Failed to " + dbName + " database: " + e.getMessage());
			}
		}
		return con;
	}
	
	//đóng kết nối
	public static void closeConnection() {
		if(con != null) {
			try {
				con.close();
				con = null;
				System.out.println("✅ Disconnected!");
			} catch (SQLException e) {
				System.err.println("❌ Disconnect failed: " + e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		new Connected().getConnection();
	}
}
