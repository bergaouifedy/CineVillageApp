package com.example.cinemavillageapplication.loginregister;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cinemavillageapplication.dao.UserDao;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.UserModel;

public class EditProfileViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private UserDao userDao;
    private AppDataBase db;

    public EditProfileViewModel(@NonNull Application application) {
        super(application);

        Log.i(TAG, "Edit ViewModel");
        db = AppDataBase.getInstance(application);
        userDao = db.userDao();
    }

    public LiveData<UserModel> getUser(String userId) {
        return userDao.getUser(userId);
    }

    public void update(UserModel userModel) {
        new UpdateAsyncTask(userDao).execute(userModel);
    }

    private class OperationsAsyncTask extends AsyncTask<UserModel, Void, Void> {

        UserDao mAsyncTaskDao;

        OperationsAsyncTask(UserDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserModel... users) {
            return null;
        }
    }


    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(UserDao userDao) {
            super(userDao);
        }

        @Override
        protected Void doInBackground(UserModel... users) {
            mAsyncTaskDao.updateUser(users[0]);
            return null;
        }
    }
}
