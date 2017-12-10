package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankDAOConnection {

    private static final String connectionURL = "jdbc:mysql://localhost:3306/bank_mysql?useSSL=false";
    private static Connection sqlConnection;
    private static BankDAOConnection instance;

    private BankDAOConnection() {
    }

    public static BankDAOConnection getInstance() throws SQLException {
        if (instance == null) {
            Properties property = new Properties();
            property.put("user", "root");
            property.put("password", "021929");
            property.put("characterEncoding", "UTF-8");
            property.put("useUnicode", "true");

            sqlConnection = DriverManager.getConnection(connectionURL, property);
        }
        return instance;
    }

    public static Connection getConnection() {
        return sqlConnection;
    }
}
