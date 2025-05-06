package com.adg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.adg.util.SessionUtil;
import com.adg.config.DbConfig;
import com.adg.model.UserModel;

/**
 * Service class for updating user information in the database.
 * 
 * This class provides methods to update user details and fetch program IDs
 * from the database. It manages database connections and handles SQL
 * exceptions.
 */
public class UpdateService {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public UpdateService() {
		try {
			dbConn = DbConfig.getConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public boolean updateUser(UserModel user) throws Exception {
		Connection con = null;
	    boolean result = false;
	    
	    try {
	        System.out.println("Trying to connect...");
	        con = DbConfig.getConnection();
	        con.setAutoCommit(false); // Start transaction
	        System.out.println("Connected!");
	        String query = "UPDATE user SET user_email = ?, user_contact = ?, user_address = ?, user_age = ?, user_dob = ? WHERE user_username = ?";
	        try (PreparedStatement ps = con.prepareStatement(query)) {
	        	ps.setString(1, user.getEmail());
	        	ps.setString(2, user.getContact());
	        	ps.setString(3, user.getAddress());
	        	ps.setInt(4, user.getAge());
	        	ps.setDate(5, user.getDob());
	        	ps.setString(6, user.getUserName()); // ✅ not getUserName()
	       

	            int rowsAffected = ps.executeUpdate();
	            con.commit(); 
	            
	            System.out.println("Rows affected: " + rowsAffected);
	            result = (rowsAffected > 0);
	            
	            // Verify the update by checking if the user still exists
	            try (PreparedStatement verifyStmt = con.prepareStatement("SELECT * FROM user WHERE user_email = ?")) {
	                verifyStmt.setString(1, user.getEmail());
	                ResultSet rs = verifyStmt.executeQuery();
	                System.out.println(rs.next() ? "User updated successfully" : "Update failed");
	            }
	            System.out.println("Updating user: " + user.getUserName());
	            System.out.println("Email: " + user.getEmail());
	            // etc.

	        }
	    } catch (SQLException e) {
	        if (con != null) con.rollback(); // Rollback in case of failure
	        System.err.println("SQL Error: " + e.getMessage());
	        throw e;
	    } finally {
	        if (con != null) con.close();  // Close the connection
	    }
	    return result;
	    
	}
	
	public UserModel getUserByUsername(String username) {
	    String query = "SELECT * FROM user WHERE user_username = ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return new UserModel(
	                rs.getInt("user_id"),
	                rs.getString("user_username"),
	                rs.getString("user_contact"),
	                rs.getString("user_address"),
	                rs.getString("user_email"),
	                rs.getString("user_password"),
	                rs.getInt("user_age"),
	                rs.getString("user_gender"),
	                rs.getDate("user_dob")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}