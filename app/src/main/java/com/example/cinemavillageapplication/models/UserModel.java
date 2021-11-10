package com.example.cinemavillageapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class UserModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "nom")
    public String Nom;
    @ColumnInfo(name = "prenom")
    public String Prenom;
    @ColumnInfo(name = "password")
    public String mdp;
    @ColumnInfo(name = "isVerified")
    public Boolean isVerified;
    @ColumnInfo(name = "email")
    public String Mail;
    @ColumnInfo(name = "numTel")
    public String numTel;
    @ColumnInfo(name = "role")
    public String role;
    @ColumnInfo(name = "nomsociete")
    public String nomSociete;
    @ColumnInfo(name = "adresse")
    public String adresse;


    public UserModel(String username, String mdp, String mail) {
        this.username = username;
        this.mdp = mdp;
        Mail = mail;
    }



    public UserModel(String nom, String prenom, String username, String mdp, Boolean isVerified, String mail, String numTel, String role, String nomSociete, String adresse) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.username = username;
        this.mdp = mdp;
        this.isVerified = isVerified;
        this.Mail = mail;
        this.numTel = numTel;
        this.role = role;
        this.nomSociete = nomSociete;
        this.adresse = adresse;
    }

    public UserModel(String nom, String prenom, String mdp, String mail, String numTel, String adresse) {
        Nom = nom;
        Prenom = prenom;
        this.mdp = mdp;
        Mail = mail;
        this.numTel = numTel;
        this.adresse = adresse;
    }

    public UserModel(String nom, String prenom, String username, String mdp, Boolean isVerified, String mail, String numTel, String role, String adresse) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.username= username;
        this.mdp = mdp;
        this.isVerified = isVerified;
        this.Mail = mail;
        this.numTel = numTel;
        this.role = role;
        this.adresse = adresse;
    }

    public UserModel() {
        super();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
