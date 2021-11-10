package com.example.cinemavillageapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemavillageapplication.models.UserModel;

@Dao
public interface UserDao {

    @Insert
    void inserUser(UserModel userModel);

    @Delete
    void deleteUser(UserModel userModel);

    @Update
    void updateUser(UserModel userModel);


    @Query("SELECT * FROM users  WHERE id=:userID")
    LiveData<UserModel> getUser(String userID);

    @Query("SELECT * FROM users where email= :mail and password= :password")
    UserModel getUser(String mail, String password);

    @Query("SELECT * FROM users where username= :username")
    UserModel getUserByUsername(String username);

}
