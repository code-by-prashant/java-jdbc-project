package SQLProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRecord {
    public static void main(String[] args) {
    	
    	// Create Connection object
        Connection connection = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root", "admin");
            
            PreparedStatement pstmt = connection.prepareStatement("UPDATE Employee SET Salary = ? WHERE Id = ?");
            
            int id;
            float salary;
            
            Scanner sc = new Scanner (System.in);
            
            System.out.println("Enter Id:");
            id = sc.nextInt();
      
            System.out.println("Enter Salary:");
            salary = sc.nextInt();
                       
            pstmt.setInt(2, id);
            pstmt.setFloat(1, salary);
            
            pstmt.execute();
            pstmt.close();
            
            System.out.println("Data updated successfully!");

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
