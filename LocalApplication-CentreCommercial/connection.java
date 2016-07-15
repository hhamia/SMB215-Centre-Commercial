package stock;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

public class connection {
    static String url = "jdbc:mysql://localhost:3306/stock";
    static String username = "root";
    static String password = "";
    
    
    static Connection getDBConnection() {
        
        Connection dbConnection = null;
        
        try {
            
            Class.forName(url);
            
        } catch (ClassNotFoundException e) {
            
            System.out.println(e.getMessage());
            
        }
        
        try {
            
            dbConnection = DriverManager.getConnection(
                    url, username,password);
            return dbConnection;
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }
        
        return dbConnection;
        
    }
    
    public static void main(String[] argv) {
        
        
        
    }
}