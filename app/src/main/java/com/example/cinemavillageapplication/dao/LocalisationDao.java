package com.example.cinemavillageapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemavillageapplication.models.LocalisationModel;
import com.example.cinemavillageapplication.models.ReservationModel;

import java.util.List;

@Dao
public interface LocalisationDao {

    @Insert
    void insertLocalisation(LocalisationModel Localisation);

    @Update
    void updateLocalisation(LocalisationModel Localisation);

    @Delete
    void deleteLocalisation(LocalisationModel Localisation);

    @Query("SELECT * FROM localisation_table")
    LiveData<List<LocalisationModel>> rgetAllLocalisations();


    @Query("SELECT * FROM localisation_table WHERE annonceId= :annonceId")
    public LocalisationModel getLocalisationByAnnonce(int annonceId);


}
