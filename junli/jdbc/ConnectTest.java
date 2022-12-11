package junli.jdbc;

import static junli.jdbc.common.Configuration.URL;
import static junli.jdbc.common.Configuration.USER;
import static junli.jdbc.common.Configuration.PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectTest {
	public static void main(String[] args) {
		// MySQL 8之後連線URL需加上SSL與時區設定
		String url = "jdbc:mysql://localhost:1433?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
		// MySQL 8之前
		// String url = "jdbc:mysql://localhost:3306";

		//		String sql = "create schema bookshop_jdbc;";

//		String driverClass = "com.mysql.cj.jdbc.Driver";
//		try {
//			// JDBC4.0之前載入JDBC Driver的方式，現在可以省略
//			Class.forName(driverClass);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			return;
//		}
		
//		// Test connection
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("Connecting to MySQL successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Create database
//		try (Connection connection = DriverManager.getConnection(url, user, password);
//				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.executeUpdate();
//			System.out.println("Database created successfully...");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		String sql = "create table employee("
//				+ "employee_id int unsigned primary key not null auto_increment, "
//				+ "employee_name varchar(40), "
//				+ "image mediumblob);";
//		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.executeUpdate();
//			System.out.println("Tables created successfully...");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
