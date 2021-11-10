package com.example.cinemavillageapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.SignalsModel;

import java.util.List;

@Dao
public interface SignalsDao {


    @Insert
    public void insertSignal (SignalsModel signalsModel);


    @Delete
    public void deleteSignal(SignalsModel signalsModel);

    @Query("SELECT * FROM SignalsModel WHERE annonceId= :annonceId")
    public List<SignalsModel> getSignalByAnnonce(int annonceId);

    @Query("SELECT COUNT(id) FROM signalsmodel WHERE annonceId = :annonceId ")
    String getRowCount(int  annonceId);

    @Query("SELECT COUNT(id) FROM signalsmodel WHERE userId = :userId ")
    int getNombreSignals(int  userId);

    @Query("SELECT * FROM signalsmodel WHERE userId= :userId AND annonceId= :annonceId")
    SignalsModel getSignalByUserAndAnnonce(int userId, int annonceId);


}
