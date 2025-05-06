package com.adg.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adg.config.DbConfig;
import com.adg.model.ArtworkModel;
public class CrudService {
	public boolean insert(ArtworkModel artwork) throws Exception{
		try {
			System.out.println("Trying to connect...");
			Connection con = DbConfig.getConnection();
			System.out.println("Connected!");

		
		String query="insert into artwork(artwork_id, artwork_name, artist_name, artwork_date, artwork_medium, artwork_price, artwork_category, artwork_format ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, artwork.getArtworkId());
        ps.setString(2, artwork.getArtworkName());
        ps.setString(3, artwork.getArtistName());
        ps.setDate(4, artwork.getArtworkDate());
        ps.setString(5, artwork.getArtworkMedium());
        ps.setDouble(6, artwork.getArtworkPrice());  
        ps.setString(7, artwork.getArtworkCategory());          
        ps.setString(8, artwork.getArtworkFormat());

        
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
	}
	
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
	            artworks.add(artwork);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return artworks;
	}

    public boolean deleteArtworkById(int artworkId) {
        try (Connection con = DbConfig.getConnection()) {
            String query = "DELETE FROM artwork WHERE artwork_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, artworkId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}