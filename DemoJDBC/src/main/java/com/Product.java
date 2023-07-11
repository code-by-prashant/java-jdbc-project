package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Product {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "admin");
			System.out.println("Connection established successfully");
			
			String insertQuery = "insert into Product values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			
			int prodId;
			String name;
			int price;
			String desc;
			double rating;
			
			Scanner sc = new Scanner (System.in);
            
            System.out.println("Enter Product Id:");
            prodId = sc.nextInt();
            
            System.out.println("Enter Product Name:");
            name = sc.next();
            
            System.out.println("Enter Product Price:");
            price = sc.nextInt();
            
            System.out.println("Enter Product Description:");
            desc = sc.next();
            
            System.out.println("Enter Product Raings:");
            rating = sc.nextDouble();
            
            ps.setInt(1, prodId);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setString(4, desc);
            ps.setDouble(5, rating);
            
			ps.execute();
			ps.close();
			
		}catch(Exception e) {
			System.out.println("Connection Failed!");
		}
	}

}
