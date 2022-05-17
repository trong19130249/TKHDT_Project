package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
	private static Connection instance = null;

	private ConnectionDao() {

	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			String url = "jdbc:mysql://localhost:3306/quanlyvlxd?zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String pass = "";
			instance = DriverManager.getConnection(url, user, pass);

		}
		return instance;
	}
}
