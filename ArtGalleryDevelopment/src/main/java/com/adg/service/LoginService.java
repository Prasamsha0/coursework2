package com.adg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.adg.config.DbConfig;
import com.adg.model.UserModel;
public class LoginService {
	public boolean insert(UserModel user) throws Exception{
		try {
			System.out.println("Trying to connect...");
			Connection con = DbConfig.getConnection();
			System.out.println("Connected!");

		
		String query="insert into user(user_id, user_name,user_contact, user_address, user_email, user_password, user_age, user_gender, user_role, user_dob ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, user.getUserId());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getContact());
        ps.setString(4, user.getAddress());
        ps.setString(5, user.getEmail());
        ps.setString(7, user.getPassword());
        ps.setLong(8, user.getAge());
        ps.setString(9, user.getGender());
        ps.setString(6, user.getRole());
        ps.setDate(10, user.getDob());
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
	}}