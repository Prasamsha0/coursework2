package com.adg.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;

/**
 * Service class to interact with the user data in the database.
 * Provides methods for retrieving user details.
 */
public class UserService {

    /**
     * Fetches all users from the database.
     * 
     * @return List of UserModel objects representing all users in the database.
     */
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();  // List to store all retrieved users

        // SQL query to retrieve all users from the database
        String sql = "SELECT * FROM user";

        // Use try-with-resources to ensure proper closing of database resources
        try (Connection con = DbConfig.getConnection(); // Open connection to DB
             PreparedStatement ps = con.prepareStatement(sql); // Prepare the SQL statement
             ResultSet rs = ps.executeQuery()) { // Execute the query

            // Loop through the ResultSet to create UserModel objects and populate the users list
            while (rs.next()) {
                // Create a new UserModel object for each row in the ResultSet
                UserModel user = new UserModel();
                
                // Set each field of the UserModel object based on the current row's data
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_username"));
                user.setContact(rs.getString("user_contact"));
                user.setAddress(rs.getString("user_address"));
                user.setEmail(rs.getString("user_email"));
                user.setAge(rs.getInt("user_age"));
                user.setGender(rs.getString("user_gender"));
                user.setDob(rs.getDate("user_dob"));

                // Add the populated UserModel object to the list of users
                users.add(user);
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions 
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging purposes
        } catch (ClassNotFoundException e) {
            // Handle the case where the JDBC driver class is not found
            System.err.println("Database driver not found: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging purposes
        }

        // Return the list of users (may be empty if no records were found)
        return users;
    }
}
