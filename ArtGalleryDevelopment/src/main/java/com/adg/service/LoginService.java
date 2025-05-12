package com.adg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;
import com.adg.util.PasswordUtil;

/**
 * Service class responsible for user login logic.
 * It handles verifying credentials from the database and decrypting stored passwords.
 */
public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes a database connection.
     * If connection fails, a flag is set to indicate the issue.
     */
    public LoginService() {
        try {
            dbConn = DbConfig.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Verifies the user’s credentials by checking the database.
     *
     * @param userModel The user model containing login input.
     * @return true if login is successful, false if invalid credentials,
     *         or null if a database connection issue occurs.
     */
    public Boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT user_username, user_password FROM user WHERE user_username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUserName());
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return validatePassword(result, userModel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // No matching user found
        return false;
    }

    /**
     * Validates the input password by comparing it to the decrypted password from DB.
     *
     * @param result    Result set from query containing encrypted password
     * @param userModel User input model with username and password
     * @return true if the password is valid, false otherwise
     * @throws SQLException If data cannot be retrieved from the result set
     */
    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("user_username");
        String dbPassword = result.getString("user_password");

        // Decrypt stored password using the username as key/salt
        String decrypted = PasswordUtil.decrypt(dbPassword, dbUsername);

        // Logging for debugging (can be removed or replaced with logger in production)
        System.out.println("DB Username: " + dbUsername);
        System.out.println("Input Username: " + userModel.getUserName());
        System.out.println("Decrypted Password: " + decrypted);
        System.out.println("Input Password: " + userModel.getPassword());

        // Compare case-insensitive username and exact password
        return dbUsername.equalsIgnoreCase(userModel.getUserName())
                && decrypted != null
                && decrypted.equals(userModel.getPassword());
    }
}
