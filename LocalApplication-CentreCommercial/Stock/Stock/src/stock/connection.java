package stock;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;

public class connection {
    
    static String url;
    static String username;
    static String password ;
    
    
    public static Connection getDBConnection() {
        doit();
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
      p.load(new FileInputStream("C:\\Users\\skynete10\\Documents\\NetBeansProjects\\Stock\\src\\stock\\connection.ini"));
      url= p.getProperty("url");
      username= p.getProperty("username");
      password=p.getProperty("password");
      
      }
    catch (Exception e) {
      System.out.println(e);
      }
    }
    public static void main(String[] argv) {
        
        
    doit();
        
    }
}