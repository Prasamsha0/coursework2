package com.adg.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

public class DbConfig {
	public static Connection getConnection() throws SQLException, ClassNotFoundException  {
	    try {
	        String url = "jdbc:mysql://localhost:3306/artgallery";
	        String username = "root";
	        String password = "";
	        
	        // Log the connection attempt
	        System.out.println("Attempting to connect to database...");
	        
	        // Load the MySQL driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Establish the connection
	        Connection con = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected successfully!");
	        return con;
	    } catch (Exception e) {
	        // Log the error details
	        System.err.println("Error connecting to database: " + e.getMessage());
	        e.printStackTrace();
	        throw e;  // Rethrow the exception
	    }
	}

}