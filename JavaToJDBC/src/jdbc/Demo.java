package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
	1 Load the driver and include the corresponding JAR file in project's classpath.

		- Use Class.forName() to dynamically load the driver class.

	2. Create a connection to the database.

		- Provide the connection details such as the URL, username, and password.
		- Use DriverManager.getConnection() to establish the connection.

	3. Create a statement object.

		- Use the createStatement() method of Connection object to create a statement.

	4. Execute the SQL statement to the corresponding method.
		
		- Use methods like executeQuery() for queries or executeUpdate() for updates.
		
	5. Close the connection.
		
		-Use the close() method of Connection object to close the Connection.
*/

public class Demo {
    public static void main(String[] args) {
    	
    	// Create Connection object
        Connection connection = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javahub", "root", "admin");
            
            Statement statement = connection.createStatement();
            
            System.out.println("Connection established successfully!");
            
            statement.execute("INSERT INTO Employee VALUES(2,'Purva')");
            
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
