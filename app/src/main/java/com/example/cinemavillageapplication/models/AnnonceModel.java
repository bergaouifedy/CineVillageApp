package com.example.cinemavillageapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "annonces_table")
public class AnnonceModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String categorie;

    private String typetarification;

    private String adresse;

    private float prix;

    private String dateajout;

    private boolean disponibilite;

    private String image;

    private int nbrChambres;

    private int nbrEtages;

    private String username;

    private String surface;


    public AnnonceModel() {
    }




    public AnnonceModel( String title, String description, String categorie, String typetarification,
                         String adresse, float prix, String dateajout, boolean disponibilite,
                         int nbrChambres, int nbrEtages, String image, String username,String surface) {
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.typetarification = typetarification;
        this.adresse = adresse;
        this.prix = prix;
        this.dateajout = dateajout;
        this.disponibilite = disponibilite;
        this.nbrChambres = nbrChambres;
        this.nbrEtages = nbrEtages;
        this.image=image;
        this.username= username;
        this.surface = surface;
    }

    public AnnonceModel( String title, String description, String categorie, String typetarification,
                         String adresse, float prix, String dateajout, boolean disponibilite,
                         int nbrChambres, int nbrEtages,  String username,String surface) {
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.typetarification = typetarification;
        this.adresse = adresse;
        this.prix = prix;
        this.dateajout = dateajout;
        this.disponibilite = disponibilite;
        this.nbrChambres = nbrChambres;
        this.nbrEtages = nbrEtages;
        this.username= username;
        this.surface = surface;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTypetarification() {
        return typetarification;
    }

    public void setTypetarification(String typetarification) {
        this.typetarification = typetarification;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDateajout() {
        return dateajout;
    }

    public void setDateajout(String dateajout) {
        this.dateajout = dateajout;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbrChambres() {
        return nbrChambres;
    }

    public void setNbrChambres(int nbrChambres) {
        this.nbrChambres = nbrChambres;
    }

    public int getNbrEtages() {
        return nbrEtages;
    }

    public void setNbrEtages(int nbrEtages) {
        this.nbrEtages = nbrEtages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
}
