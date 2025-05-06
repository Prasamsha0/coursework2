package com.adg.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;
import com.adg.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
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
	 * Validates the user credentials against the database records.
	 *
	 * @param userModel the UserModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT user_username, user_password FROM user WHERE user_username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getUserName());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username and password from
	 *                     the database
	 * @param userModel the UserModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */

	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
	    String dbUsername = result.getString("user_username");
	    String dbPassword = result.getString("user_password");
	    String decrypted = PasswordUtil.decrypt(dbPassword, dbUsername);
	    
	    System.out.println("DB Username: " + dbUsername);
	    System.out.println("Input Username: " + userModel.getUserName());
	    System.out.println("Decrypted Password: " + decrypted);
	    System.out.println("Input Password: " + userModel.getPassword());
	    
	    return dbUsername.equalsIgnoreCase(userModel.getUserName())
	            && decrypted != null
	            && decrypted.equals(userModel.getPassword());
	}
}