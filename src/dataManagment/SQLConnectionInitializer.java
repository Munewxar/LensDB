package dataManagment;

import java.sql.*;

public class SQLConnectionInitializer {
    private static final String url = "jdbc:mysql://localhost:3306/lens_db";
    private static final String password = "rootroot";
    private static final String user = "root";

    private Connection connection;

    private void establishConnection(){
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public void initialize() {
        establishConnection();
    }

    public Connection getConnection() {
        return connection;
    }
}
