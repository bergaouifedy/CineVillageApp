package com.example.cinemavillageapplication.models;

import android.annotation.SuppressLint;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "reservation_table")
public class ReservationModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int utilisateurId;
    private Date startTime;
    private Date endTime;
    private int annonceId;
    private String annonceTitle;
    private String utilisateurUserName;
    private String etat;

    public ReservationModel() {
    }


    public ReservationModel(int utilisateurId, Date startTime, Date endTime, int annonceId, String etat, String utilisateurUserName, String annonceTitle) {
        this.utilisateurId = utilisateurId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.annonceId = annonceId;
        this.etat = etat;
        this.utilisateurUserName = utilisateurUserName;
        this.annonceTitle = annonceTitle;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }

    public String getAnnonceTitle() {
        return annonceTitle;
    }

    public void setAnnonceTitle(String annonceTitle) {
        this.annonceTitle = annonceTitle;
    }

    public String getUtilisateurUserName() {
        return utilisateurUserName;
    }

    public void setUtilisateurUserName(String utilisateurUserName) {
        this.utilisateurUserName = utilisateurUserName;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean isAvailable(Date bookingStartTime, Date bookingEndTime) {
        if ((bookingStartTime.after(this.endTime) && bookingEndTime.after(this.endTime)) || (bookingEndTime.before(this.startTime) && bookingStartTime.before(this.startTime)))
            return true;
        else
            return false;
    }


}

