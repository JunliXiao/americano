package junli.jdbc;

import static junli.jdbc.common.Configuration.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableBatch {

	public static void main(String[] args) {
		
		String createCustomer = "CREATE TABLE customer (\r\n"
				+ "	customer_id VARCHAR(40) NOT NULL COMMENT '客戶編號' PRIMARY KEY,\r\n"
				+ "    password VARCHAR(40) NOT NULL COMMENT '密碼',\r\n"
				+ "    customer_name VARCHAR(40) NOT NULL COMMENT '客戶姓名',\r\n"
				+ "    phone VARCHAR(40) NOT NULL COMMENT '電話號碼',\r\n"
				+ "    address VARCHAR(200) NOT NULL COMMENT '地址'\r\n"
				+ ") COMMENT '客戶資料表';";
		
		String createOrderMaster = "CREATE TABLE order_master (\r\n"
				+ "	order_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '訂單編號' PRIMARY KEY,\r\n"
				+ "	customer_id VARCHAR(40) NOT NULL COMMENT '客戶編號',\r\n"
				+ "    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '時間戳記', \r\n"
				+ "    CONSTRAINT FK_order_master_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id)\r\n"
				+ ");";
		
		String createOrderDetail = "CREATE TABLE order_detail (\r\n"
				+ "	order_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '訂單編號',\r\n"
				+ "    isbn CHAR(13) NOT NULL COMMENT 'ISBN',\r\n"
				+ "    quantity INT NOT NULL,\r\n"
				+ "    CONSTRAINT FK_order_detail_order_id FOREIGN KEY (order_id) REFERENCES order_master(order_id),\r\n"
				+ "    CONSTRAINT FK_order_detail_isbn FOREIGN KEY (isbn) REFERENCES bookshop_jdbc.book(isbn),\r\n"
				+ "    PRIMARY KEY (order_id, isbn)\r\n"
				+ ");";
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement()) {
			DatabaseMetaData dbMetaData = connection.getMetaData();
			System.out.println("supports batch updates: " + dbMetaData.supportsBatchUpdates());
			if (dbMetaData.supportsBatchUpdates()) {
				statement.addBatch(createCustomer);
				statement.addBatch(createOrderMaster);
				statement.addBatch(createOrderDetail);
				statement.executeBatch();
				System.out.println("Tables created successfully...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}