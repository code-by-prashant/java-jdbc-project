package SQLProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CompanySchema {
    public static void main(String[] args) {
    	
    	// Create Connection object
        Connection connection = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root", "admin");
            
            Statement statement = connection.createStatement();
            
            System.out.println("Connection established successfully!");
            
//            statement.execute("INSERT INTO Employee (id, name, age, salary, designation) "
//            		+ "VALUES (1, 'Prashant', 23, 40000, 'Developer'), "
//            		+ "(2, 'Purva', 22, 40000, 'Developer'), "
//            		+ "(3, 'Parvesh', 23, 40000, 'Tester')");
            
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?)");
            
            int id;
            String name;
            int age;
            float salary;
            String designation;
            
            Scanner sc = new Scanner (System.in);
            
            System.out.println("Enter Id:");
            id = sc.nextInt();
            
            System.out.println("Enter Name:");
            name = sc.next();

            System.out.println("Enter Age:");
            age = sc.nextInt();
            
            System.out.println("Enter Salary:");
            salary = sc.nextInt();
            
            System.out.println("Enter Designation:");
            designation = sc.next();
            
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setFloat(4, salary);
            pstmt.setString(5, designation);
            
            pstmt.execute();
            pstmt.close();
            
            System.out.println("Data inserted successfully!");

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
