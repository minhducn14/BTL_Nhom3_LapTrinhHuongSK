package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();

	private ConnectDB() {
		
    }
	
	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuayThuoc";
		String user = "sa";
		String pass = "sapassword";

		con = DriverManager.getConnection(url, user, pass);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		if(con==null) {
			try {
				instance.connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}