package com.example.cinemavillageapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cinemavillageapplication.models.AlbumModel;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert
    public void insertAlbum(AlbumModel albumModel);

    @Query("SELECT * FROM AlbumModel WHERE annonceId = :annonceId")
    AlbumModel getAlbumByAnnonce(int annonceId);
}
