package com.adg.service;

import java.sql.*;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;
import com.adg.util.ValidationUtil;

/**
 * Service class for updating user information in the database.
 */
public class UpdateService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Initializes the connection to the database.
     */
    public UpdateService() {
        try {
            dbConn = DbConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Updates the user details in the database.
     * If password is provided, it will also be updated.
     * 
     * @param user The UserModel object containing the updated user information.
     * @return true if the update was successful, false otherwise.
     * @throws Exception if an error occurs during the update process.
     */
    public boolean updateUser(UserModel user) throws Exception {
        // Default result flag
        boolean result = false;

        try (Connection con = DbConfig.getConnection()) {
            // Start a transaction for consistency
            con.setAutoCommit(false);

            // Determine if password update is needed
            boolean updatePassword = !ValidationUtil.isNullOrEmpty(user.getPassword());

            // SQL query depending on whether the password is updated
            String query = updatePassword
                ? "UPDATE user SET user_email = ?, user_contact = ?, user_address = ?, user_age = ?, user_dob = ?, user_password = ? WHERE user_username = ?"
                : "UPDATE user SET user_email = ?, user_contact = ?, user_address = ?, user_age = ?, user_dob = ? WHERE user_username = ?";

            // Prepare the statement
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getContact());
                ps.setString(3, user.getAddress());
                ps.setInt(4, user.getAge());
                ps.setDate(5, user.getDob());

                // Set the password if it's being updated
                if (updatePassword) {
                    ps.setString(6, user.getPassword());
                    ps.setString(7, user.getUserName());
                } else {
                    ps.setString(6, user.getUserName());
                }

                // Execute the update and commit transaction if successful
                int rowsAffected = ps.executeUpdate();
                con.commit();
                result = (rowsAffected > 0);
            }

        } catch (SQLException e) {
            // Rollback in case of error to ensure no partial changes
            if (dbConn != null) dbConn.rollback();
            e.printStackTrace();
            throw e;
        }

        return result;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserModel object if found, null otherwise.
     */
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
            		    rs.getDate("user_dob"),
            		    rs.getString("user_image")
            		);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
