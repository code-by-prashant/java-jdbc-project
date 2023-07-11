package SQLProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReadRecord {
    public static void main(String[] args) {
    	
    	// Create Connection object
        Connection connection = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root", "admin");
            Statement statement = connection.createStatement();
           
            ResultSet rs = statement.executeQuery("SELECT * FROM Employee");
            
            while(rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getInt(3) + " : " + rs.getFloat(4) + " : " + rs.getString(5));

            }
            
            System.out.println("Records displayed successfully!");

        } 
        catch (ClassNotFoundException e) {
            // JDBC driver not found exception
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            // Connection error
            System.out.println("Connection failed!");
            e.printStackTrace();
        } 
        
    }
}
