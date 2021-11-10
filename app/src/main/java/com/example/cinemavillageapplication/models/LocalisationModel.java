package com.example.cinemavillageapplication.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "localisation_table")
public class LocalisationModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Double longitude;

    private Double latitude;

    private String country;

    private String locality;

    private String adresse;

    private int annonceId;



    public LocalisationModel(Double longitude, Double latitude, String country, String locality, String adresse, int annonceId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
        this.locality = locality;
        this.adresse = adresse;
        this.annonceId = annonceId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }
}
