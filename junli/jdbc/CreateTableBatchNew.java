package junli.jdbc;

import static junli.jdbc.common.Configuration.*;
import static junli.jdbc.common.Script.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateTableBatchNew {

    public static void main(String[] args) {
        // Construct a array list from a MySQL script file
        ArrayList<String> commands = parseCommand("/Users/xiaojunli/MySQL_Homework/JDBC/DDL_1.sql");
        // Use each command(statement) in ihe script
        String createCustomer = commands.get(0);
        String createOrderMaster = commands.get(1);
        String createOrderDetail = commands.get(2);

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