package com.adg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.adg.config.DbConfig;
import com.adg.model.UserModel;

/**
 * Service class for registering new users into the database.
 */
public class RegisterService {

    /**
     * Inserts a new user record into the database.
     *
     * @param user The UserModel object containing user details.
     * @return true if insertion is successful, false otherwise.
     * @throws Exception if a database connection or query error occurs.
     */
    public boolean insert(UserModel user) throws Exception {
    	String query = "INSERT INTO user (user_id, user_username, user_contact, user_address, user_email, user_password, user_age, user_gender, user_dob, user_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getContact());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword()); // Consider encrypting before storing
            ps.setInt(7, user.getAge());
            ps.setString(8, user.getGender());
            ps.setDate(9, user.getDob());
            ps.setString(10, user.getImage());


            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace(); // Replace with logger in production
            return false;
        }
    }
}
