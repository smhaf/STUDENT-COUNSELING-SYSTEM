package util; // Suggest putting this in a 'util' package

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Update these values to match your NetBeans Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/counseling_db"; 
    // If using Java DB (Derby): "jdbc:derby://localhost:1527/counseling_db"
    
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection createConnection() {
        Connection con = null;
        try {
            // 1. Load Driver
            Class.forName("com.mysql.jdbc.Driver"); 
            // If Derby: Class.forName("org.apache.derby.jdbc.ClientDriver");

            // 2. Establish Connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB Connection Success!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("DB Connection Failed: " + e.getMessage());
        }
        return con;
    }
}