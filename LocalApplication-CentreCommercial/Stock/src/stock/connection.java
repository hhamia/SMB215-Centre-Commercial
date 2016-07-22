package stock;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
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
    public static void doit() {
    try{
      Properties p = new Properties();
      p.load(new FileInputStream("files/begin.ini"));
      System.out.println("user = " + p.getProperty("url"));
      System.out.println("password = " + p.getProperty("username"));
      System.out.println("location = " + p.getProperty("password"));
      p.list(System.out);
      }
    catch (Exception e) {
      System.out.println(e);
      }
    }
    public static void main(String[] argv) {
        
        
    doit();
        
    }
}