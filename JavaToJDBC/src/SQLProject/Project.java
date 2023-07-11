package SQLProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
	
	int id;
	String name;
	int age;
	float salary;
	String designation;
	Scanner sc = new Scanner(System.in);

	Employee() {
		try {

			System.out.print("\nEnter Id: ");
			id = sc.nextInt();

			System.out.print("Enter the Name: ");
			name = sc.next();

			System.out.print("Enter Age: ");
			age = sc.nextInt();
			System.out.println("");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void display() {
		System.out.println("\nID:" + id);
		System.out.println("NAME:" + name);
		System.out.println("AGE:" + age);
		System.out.println("SALARY:" + salary);
		System.out.println("DESIGNATION:" + designation);
	}

	public abstract void riseSalary();
}

final class Tester extends Employee {
	public Tester() {
		salary = 40000;
		designation = "Tester";
	}

	public void riseSalary() {
		salary = salary + 10000;
		System.out.println("incremented Salary for Tester:" + salary);
	}
}

final class Developer extends Employee {
	public Developer() {
		salary = 40000;
		designation = "Developer";
	}

	public void riseSalary() {
		salary = salary + 10000;
		System.out.println("incremented Salary for Developer:" + salary);
	}
}

final class Manager extends Employee {
	public Manager() {
		salary = 90000;
		designation = "Manager";
	}

	public void riseSalary() {
		salary = salary + 20000;
		System.out.println("incremented Salary for Manager:" + salary);
	}
}

public class Project {
	public static void main(String args[]) {
		int mainMenu = 0, subMenu = 0;

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root", "admin");

			System.out.println("Connection established successfully!");

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?)");

			do {
				System.out.println("\n--------------------------------");
				System.out.println("1. CREATE");
				System.out.println("2. READ");
				System.out.println("3. UPDATE");
				System.out.println("4. DELETE");
				System.out.println("5. Exit");
				System.out.println("--------------------------------\n");

				System.out.print("Enter your Choice: ");
				Scanner mainMenuInput = new Scanner(System.in);
				mainMenu = mainMenuInput.nextInt();
				
				if (mainMenu == 1) {
					do {
						System.out.println("\n--------------------------------");
						System.out.println("1. Tester");
						System.out.println("2. Developer");
						System.out.println("3. Manager");
						System.out.println("4. Exit");
						System.out.println("--------------------------------\n");

						System.out.print("Enter your Choice: ");
						Scanner subMenuInput = new Scanner(System.in);
						subMenu = subMenuInput.nextInt();

						switch (subMenu) {
						case 1:
							Tester tester = new Tester();
							insertEmployeeData(pstmt, tester);
							break;
						case 2:
							Developer developer = new Developer();
							insertEmployeeData(pstmt, developer);
							break;
						case 3:
							Manager manager = new Manager();
							insertEmployeeData(pstmt, manager);
							break;
						}
					} while (subMenu != 4);
				}
				if (mainMenu == 2) {
					System.out.println("");
					readAndDisplayRecords(connection);
				}

				if (mainMenu == 3) {
					System.out.println("");
					updateRecord(connection);
				}
				if (mainMenu == 4) {
					System.out.println("");
					deleteRecord(connection);
				}
				if (mainMenu == 5) {
					System.out.println("\nThank you for using our application!");
					System.exit(0);
				}
			} while (mainMenu != 5);

			pstmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
	}

	public static void insertEmployeeData(PreparedStatement pstmt, Employee employee) throws SQLException {
		pstmt.setInt(1, employee.id);
		pstmt.setString(2, employee.name);
		pstmt.setInt(3, employee.age);
		pstmt.setFloat(4, employee.salary);
		pstmt.setString(5, employee.designation);

		pstmt.execute();
		System.out.println("\nData inserted successfully!");
	}

	public static void readAndDisplayRecords(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Employee");

			while (rs.next()) {
				System.out.println(" " + rs.getInt(1) + " | " + rs.getString(2) 
								+ " 	| " + rs.getInt(3) + "	| "
								+ rs.getFloat(4) + "   | " + rs.getString(5));
			}

			rs.close();
			statement.close();
            System.out.println("\nRecords displayed successfully!");

		} catch (SQLException e) {
			System.out.println("Failed to read records from the database!");
			e.printStackTrace();
		}
	}

	public static void updateRecord(Connection connection) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE Employee SET Salary = ? WHERE Id = ?");

			int id;
			float salary;

			Scanner sc = new Scanner(System.in);

			System.out.print("Enter Id:");
			id = sc.nextInt();

			System.out.print("Enter Salary:");
			salary = sc.nextFloat();

			pstmt.setFloat(1, salary);
			pstmt.setInt(2, id);

			pstmt.execute();
			pstmt.close();
            System.out.println("\nData updated successfully!");

		} catch (SQLException e) {
			System.out.println("Failed to update record in the database!");
			e.printStackTrace();
		}
	}

	public static void deleteRecord(Connection connection) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Employee WHERE Id = ?");

			int id;

			Scanner sc = new Scanner(System.in);

			System.out.print("Enter Id:");
			id = sc.nextInt();

			pstmt.setInt(1, id);

			pstmt.execute();
			pstmt.close();
			
            System.out.println("\nData deleted successfully!");

			
		} catch (SQLException e) {
			System.out.println("Failed to delete record from the database!");
			e.printStackTrace();
		}
	}

}
