package com.adg.model;

import java.sql.Date;

/**
 * Represents an artwork with all its key details like name, artist, date, medium, etc.
 */
public class ArtworkModel {

    // Unique identifier for the artwork
    private int artworkId;

    // Name/title of the artwork
    private String artworkName;

    // Name of the artist who created the artwork
    private String artistName;

    // Date when the artwork was created
    private Date artworkDate;

    // Medium used (e.g., oil, watercolor, digital)
    private String artworkMedium;

    // Price of the artwork
    private double artworkPrice;

    // Category or genre of the artwork (e.g., abstract, landscape)
    private String artworkCategory;

    // Format of the artwork (e.g., painting, sculpture, digital file)
    private String artworkFormat;

    // Default constructor
    public ArtworkModel() {}

    /**
     * Parameterized constructor to create an artwork with all details.
     */
    public ArtworkModel(int artworkId, String artworkName, String artistName, Date artworkDate,
                        String artworkMedium, double artworkPrice, String artworkCategory, String artworkFormat) {
        this.artworkId = artworkId;
        this.artworkName = artworkName;
        this.artistName = artistName;
        this.artworkDate = artworkDate;
        this.artworkMedium = artworkMedium;
        this.artworkPrice = artworkPrice;
        this.artworkCategory = artworkCategory;
        this.artworkFormat = artworkFormat;
    }

    // Getters and Setters

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Date getArtworkDate() {
        return artworkDate;
    }

    public void setArtworkDate(Date artworkDate) {
        this.artworkDate = artworkDate;
    }

    public String getArtworkMedium() {
        return artworkMedium;
    }

    public void setArtworkMedium(String artworkMedium) {
        this.artworkMedium = artworkMedium;
    }

    public double getArtworkPrice() {
        return artworkPrice;
    }

    public void setArtworkPrice(double artworkPrice) {
        this.artworkPrice = artworkPrice;
    }

    public String getArtworkCategory() {
        return artworkCategory;
    }

    public void setArtworkCategory(String artworkCategory) {
        this.artworkCategory = artworkCategory;
    }

    public String getArtworkFormat() {
        return artworkFormat;
    }

    public void setArtworkFormat(String artworkFormat) {
        this.artworkFormat = artworkFormat;
    }

    /**
     * Provides a readable string representation of the artwork object.
     */
    @Override
    public String toString() {
        return "Artwork{" +
                "artworkId=" + artworkId +
                ", artworkName='" + artworkName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", artworkDate=" + artworkDate +
                ", artworkMedium='" + artworkMedium + '\'' +
                ", artworkPrice=" + artworkPrice +
                ", artworkCategory='" + artworkCategory + '\'' +
                ", artworkFormat='" + artworkFormat + '\'' +
                '}';
    }
}
