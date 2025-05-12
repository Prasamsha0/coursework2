package com.adg.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database configuration utility for establishing MySQL connections.
 */
public class DbConfig {

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a valid database connection
     * @throws SQLException if the database connection fails
     * @throws ClassNotFoundException if the JDBC driver is not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/artgallery";
        String username = "root";
        String password = ""; // Leave empty if no password is set

        try {
            // Inform that a connection attempt is starting
            System.out.println("Attempting to connect to the database...");

            // Load the MySQL JDBC driver 
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Success message
            System.out.println("Database connection established successfully.");

            return connection;

        } catch (Exception e) {
            // Log error details for debugging
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();

            // Rethrow so calling code can handle the exception
            throw e;
        }
    }
}
