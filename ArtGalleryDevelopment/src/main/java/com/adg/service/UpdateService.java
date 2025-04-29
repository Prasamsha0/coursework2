package com.adg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	/**
	 * Updates user information in the database.
	 * 
	 * @param user The UserModel object containing the updated user data.
	 * @return Boolean indicating the success of the update operation. Returns null
	 *         if there is a connection error or an exception occurs.
	 */
	public Boolean updateUserInfo(UserModel user) {
		if (isConnectionError) {
			return null;
		}

		int Id = getUserId(user.getUserName());
		if (Id == 0) {
			// Handle case where the program is not found in the database
			System.out.println("Invalid program: " + user.getUserName());
			return false; // or return null if you want to handle this in the controller
		}

		String updateSQL = "UPDATE user SET user_username = ?, user_contact = ?, "
				+ "user_address = ?, user_email = ?, user_password = ?, user_age = ?, user_gender = ?, user_dob = ? WHERE user_id = ?";

		try (PreparedStatement ps = dbConn.prepareStatement(updateSQL)) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getContact());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getAge());
			ps.setString(7, user.getGender());
			ps.setDate(8, user.getDob());
			ps.setInt(9, Id);  // WHERE user_id = ?


			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// Log and handle SQL exceptions
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the program ID for a given program name.
	 * 
	 * @param programName The name of the program.
	 * @return The ID of the program. Returns 0 if the program is not found or an
	 *         exception occurs.
	 */
	private int getUserId(String userName) {
		String selectSQL = "SELECT user_id FROM user WHERE name = ?";

		try (PreparedStatement preparedStatement = dbConn.prepareStatement(selectSQL)) {
			preparedStatement.setString(1, userName);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				return result.getInt("user_id");
			}
		} catch (SQLException e) {
			// Log and handle SQL exceptions
			e.printStackTrace();
		}

		return 0;
	}
}