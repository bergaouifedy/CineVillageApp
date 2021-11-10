package com.example.cinemavillageapplication.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class AlbumModel {

    @PrimaryKey(autoGenerate = true)
    int id;

    ArrayList<String> images;

    int annonceId;

    public AlbumModel(ArrayList<String> images, int annonceId) {
        this.images = images;
        this.annonceId = annonceId;
    }

    public AlbumModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }

}

