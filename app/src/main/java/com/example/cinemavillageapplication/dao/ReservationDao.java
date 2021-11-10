package com.example.cinemavillageapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemavillageapplication.models.ReservationModel;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    public void insertReservation(ReservationModel reservationModel);

     @Query("SELECT * FROM RESERVATION_TABLE WHERE annonceId= :annonceId")
    public List<ReservationModel> getAllReservationByAnnonce(int annonceId);


    @Query("SELECT * FROM RESERVATION_TABLE WHERE utilisateurId= :utilisateurId")
    public ReservationModel getAllReservationByUser(int utilisateurId);

    @Update
    public void UpdateReservation(ReservationModel reservationModel);

    @Query("SELECT * FROM RESERVATION_TABLE WHERE id= :id")
    public ReservationModel getReservation(int id);

    @Query("SELECT * FROM reservation_table WHERE etat= :etat")
    public  ReservationModel getReservationByEtat(String etat);
}
