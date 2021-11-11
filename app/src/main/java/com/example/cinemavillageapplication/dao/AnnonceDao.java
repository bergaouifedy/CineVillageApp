package com.example.cinemavillageapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemavillageapplication.models.AnnonceModel;

import java.util.List;

@Dao
public interface AnnonceDao {

    @Insert
    void insertAnnonce(AnnonceModel annonce);


    @Update
    void updateAnnonce(AnnonceModel annonce);

    @Delete
    void deleteAnnonce(AnnonceModel annonce);

    @Query("SELECT * FROM annonces_table")
    LiveData<List<AnnonceModel>> getAllAnnonces();

    @Query("SELECT * FROM annonces_table WHERE categorie= :category")
    List<AnnonceModel> getAnnoncesByCategory(String category);


    @Query("DELETE FROM annonces_table")
    void deleteAllAnnonces();

    @Query("Select * FROM annonces_table WHERE title= :title")
    public AnnonceModel getAnnonceByTitle(String title);

    @Query("Select * FROM annonces_table WHERE id= :id")
    public AnnonceModel getAnnonceById(int id);


    @Query("SELECT * FROM annonces_table WHERE username=:username")
    List<AnnonceModel> getUsersAnnonce(String username);

    @Query("SELECT title FROM annonces_table WHERE id = :id")
    String getTitleAnnonce(int id);

    @Query("SELECT COUNT(id) FROM annonces_table WHERE username= :username")
    int getNombreAnnonces (String username);


}
