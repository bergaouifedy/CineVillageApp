package com.example.cinemavillageapplication.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SignalsModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String type;
    private String message;

    private int annonceId;

    private int userId;

    public SignalsModel(int id, String type, String message, int annonceId, int userId) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.annonceId = annonceId;
        this.userId = userId;
    }

    public SignalsModel(String type, String message, int annonceId,int userId) {
        this.type = type;
        this.message = message;
        this.annonceId = annonceId;
        this.userId = userId;

    }

    public SignalsModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
