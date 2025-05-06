package com.adg.model;

import java.sql.Date;

public class ArtworkModel {

    private int artworkId;
    private String artworkName;
    private String artistName;
    private Date artworkDate;
    private String artworkMedium;
    private double artworkPrice;
    private String artworkCategory;
    private String artworkFormat;

    // Constructors
    public ArtworkModel() {}

    public ArtworkModel(int artworkId, String artworkName, String artistName, Date date,
                   String artworkMedium, double price, String artworkCategory, String artworkFormat) {
        this.artworkId = artworkId;
        this.artworkName = artworkName;
        this.artistName = artistName;
        this.artworkDate = date;
        this.artworkMedium = artworkMedium;
        this.artworkPrice = price;
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

    public Double getArtworkPrice() {
        return artworkPrice;
    }

    public void setArtworkPrice(double d) {
        this.artworkPrice = d;
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