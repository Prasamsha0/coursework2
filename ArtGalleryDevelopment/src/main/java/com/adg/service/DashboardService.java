package com.adg.service;

import com.adg.config.DbConfig;
import com.adg.model.ArtworkModel;
import com.adg.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle dashboard-related data operations.
 * Provides methods to fetch counts and recent records of users and artworks.
 */
public class DashboardService {

    /**
     * Gets the total number of users from the database.
     *
     * @return number of users
     */
    public int getUserCount() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM user";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * Gets the total number of artworks from the database.
     *
     * @return number of artworks
     */
    public int getArtworkCount() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM artwork";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * Retrieves a list of the most recently added users, limited by the provided count.
     *
     * @param limit number of users to retrieve
     * @return list of recent UserModel objects
     */
    public List<UserModel> getRecentUsers(int limit) throws SQLException, ClassNotFoundException {
        List<UserModel> users = new ArrayList<>();
        String query = "SELECT * FROM user ORDER BY user_id DESC LIMIT ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        }

        return users;
    }

    /**
     * Retrieves a list of the most recently added artworks, limited by the provided count.
     *
     * @param limit number of artworks to retrieve
     * @return list of recent ArtworkModel objects
     */
    public List<ArtworkModel> getRecentArtworks(int limit) throws SQLException, ClassNotFoundException {
        List<ArtworkModel> artworks = new ArrayList<>();
        String query = "SELECT * FROM artwork ORDER BY artwork_id DESC LIMIT ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ArtworkModel art = new ArtworkModel();
                    art.setArtworkId(rs.getInt("artwork_id"));
                    art.setArtworkName(rs.getString("artwork_name"));
                    art.setArtistName(rs.getString("artist_name"));
                    art.setArtworkDate(rs.getDate("artwork_date"));
                    art.setArtworkMedium(rs.getString("artwork_medium"));
                    art.setArtworkPrice(rs.getDouble("artwork_price"));
                    art.setArtworkCategory(rs.getString("artwork_category"));
                    art.setArtworkFormat(rs.getString("artwork_format"));

                    artworks.add(art);
                }
            }
        }

        return artworks;
    }
}
