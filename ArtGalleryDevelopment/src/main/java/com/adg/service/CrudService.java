package com.adg.service;

import com.adg.config.DbConfig;
import com.adg.model.ArtworkModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to perform CRUD operations on the 'artwork' table.
 */
public class CrudService {

    /**
     * Inserts a new artwork into the database.
     * 
     * @param artwork the artwork to insert
     * @return true if insertion was successful, false otherwise
     */
    public boolean insert(ArtworkModel artwork) throws Exception {
        String query = "INSERT INTO artwork (artwork_id, artwork_name, artist_name, artwork_date, artwork_medium, artwork_price, artwork_category, artwork_format) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DbConfig.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, artwork.getArtworkId());
            ps.setString(2, artwork.getArtworkName());
            ps.setString(3, artwork.getArtistName());
            ps.setDate(4, artwork.getArtworkDate());
            ps.setString(5, artwork.getArtworkMedium());
            ps.setDouble(6, artwork.getArtworkPrice());
            ps.setString(7, artwork.getArtworkCategory());
            ps.setString(8, artwork.getArtworkFormat());

            return ps.executeUpdate() > 0; // returns true if one or more rows were inserted
        }
    }

    /**
     * Retrieves all artworks from the database.
     * 
     * @return a list of ArtworkModel objects
     */
    public List<ArtworkModel> getAllArtworks() throws SQLException, ClassNotFoundException {
        List<ArtworkModel> artworks = new ArrayList<>();
        String query = "SELECT * FROM artwork";

        try (Connection con = DbConfig.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ArtworkModel artwork = new ArtworkModel();
                artwork.setArtworkId(rs.getInt("artwork_id"));
                artwork.setArtworkName(rs.getString("artwork_name"));
                artwork.setArtistName(rs.getString("artist_name"));
                artwork.setArtworkDate(rs.getDate("artwork_date"));
                artwork.setArtworkMedium(rs.getString("artwork_medium"));
                artwork.setArtworkPrice(rs.getDouble("artwork_price"));
                artwork.setArtworkCategory(rs.getString("artwork_category"));
                artwork.setArtworkFormat(rs.getString("artwork_format"));

                artworks.add(artwork); // add the filled model to the list
            }
        }

        return artworks;
    }

    /**
     * Deletes an artwork by its ID.
     * 
     * @param artworkId the ID of the artwork to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteArtworkById(int artworkId) {
        String query = "DELETE FROM artwork WHERE artwork_id = ?";

        try (Connection con = DbConfig.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, artworkId);
            return ps.executeUpdate() > 0; // true if a row was deleted

        } catch (Exception e) {
            e.printStackTrace(); // Log error for debugging
            return false;
        }
    }

    /**
     * Updates the details of an existing artwork.
     * 
     * @param artwork the artwork object with updated values
     * @return true if update was successful, false otherwise
     */
    public boolean updateArtwork(ArtworkModel artwork) {
        String query = "UPDATE artwork SET artwork_name=?, artist_name=?, artwork_date=?, artwork_medium=?, artwork_price=?, artwork_category=?, artwork_format=? WHERE artwork_id=?";

        try (Connection con = DbConfig.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, artwork.getArtworkName());
            ps.setString(2, artwork.getArtistName());
            ps.setDate(3, artwork.getArtworkDate());
            ps.setString(4, artwork.getArtworkMedium());
            ps.setDouble(5, artwork.getArtworkPrice());
            ps.setString(6, artwork.getArtworkCategory());
            ps.setString(7, artwork.getArtworkFormat());
            ps.setInt(8, artwork.getArtworkId());

            return ps.executeUpdate() > 0; // true if a row was updated

        } catch (Exception e) {
            e.printStackTrace(); // Log error for debugging
            return false;
        }
    }
}
