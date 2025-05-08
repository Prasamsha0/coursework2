package com.adg.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;

public class UserService {
	public List<UserModel> getAllUsers() {
	    List<UserModel> users = new ArrayList<>();
	    String sql = "SELECT * FROM user";

	    try (Connection con = DbConfig.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            UserModel user = new UserModel();
	            user.setUserId(rs.getInt("user_id"));
	            user.setUserName(rs.getString("user_username"));
	            user.setContact(rs.getString("user_contact"));
	            user.setAddress(rs.getString("user_address"));
	            user.setEmail(rs.getString("user_email"));
	            user.setAge(rs.getInt("user_age"));
	            user.setGender(rs.getString("user_gender"));
	            user.setDob(rs.getDate("user_dob"));
	            users.add(user);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return users;
	}

}
