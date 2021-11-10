package com.example.cinemavillageapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cinemavillageapplication.dao.AlbumDao;
import com.example.cinemavillageapplication.dao.AnnonceDao;
import com.example.cinemavillageapplication.dao.LocalisationDao;
import com.example.cinemavillageapplication.dao.ReservationDao;
import com.example.cinemavillageapplication.dao.SignalsDao;
import com.example.cinemavillageapplication.dao.UserDao;
import com.example.cinemavillageapplication.models.AlbumModel;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.Converters;
import com.example.cinemavillageapplication.models.LocalisationModel;
import com.example.cinemavillageapplication.models.ReservationModel;
import com.example.cinemavillageapplication.models.SignalsModel;
import com.example.cinemavillageapplication.models.UserModel;

@Database(entities = {AnnonceModel.class,
        UserModel.class,
        SignalsModel.class,
        ReservationModel.class,
        LocalisationModel.class,
        AlbumModel.class},version = 1)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract AnnonceDao annonceDao();

    public abstract UserDao userDao();

    public abstract SignalsDao signalsDao();

    public abstract LocalisationDao localisationDao();

    public abstract ReservationDao reservationDao();

    public abstract AlbumDao albumDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "Cinema_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
