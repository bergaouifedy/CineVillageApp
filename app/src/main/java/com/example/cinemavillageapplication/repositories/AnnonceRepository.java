package com.example.cinemavillageapplication.repositories;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.cinemavillageapplication.dao.AnnonceDao;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class AnnonceRepository {

    private AnnonceDao annonceDao;
    private LiveData<List<AnnonceModel>> allAnnonces;
    private List<AnnonceModel> usersAnnonces;
    private List<AnnonceModel> annonceByCategory;

    String category,user;


    public AnnonceRepository(Application application)
    {
        AppDataBase database = AppDataBase.getInstance(application);
        annonceDao = database.annonceDao();
        allAnnonces = annonceDao.getAllAnnonces();
        usersAnnonces = annonceDao.getUsersAnnonce(user);
        annonceByCategory = annonceDao.getAnnoncesByCategory(category);
    }

    public void insert(AnnonceModel annonceModel) {
        new InsertAnnonceAsyncTask(annonceDao).execute(annonceModel);
    }

    public void update(AnnonceModel annonceModel)
    {
        new UpdateAnnonceAsyncTask(annonceDao).execute(annonceModel);

    }

    public void delete(AnnonceModel annonceModel)
    {
        new DeleteAnnonceAsyncTask(annonceDao).execute(annonceModel);

    }


    public LiveData<List<AnnonceModel>> getAllAnnonces()
    {
        return allAnnonces;
    }

    public List<AnnonceModel> getUsersAnnonce(String user)
    {
        usersAnnonces= annonceDao.getUsersAnnonce(user);
        return usersAnnonces;
    }

    public List<AnnonceModel> getAnnoncesByCategory(String category)
    {
        annonceByCategory= annonceDao.getAnnoncesByCategory(category);
        return annonceByCategory;
    }
    public static class InsertAnnonceAsyncTask extends AsyncTask<AnnonceModel,Void,Void>
    {
        private AnnonceDao annonceDao;

        private InsertAnnonceAsyncTask(AnnonceDao annonceDao)
        {
            this.annonceDao=annonceDao;
        }
        @Override
        protected Void doInBackground(AnnonceModel... annonceModels) {
            annonceDao.insertAnnonce(annonceModels[0]);
            return null;
        }
    }

    public static class UpdateAnnonceAsyncTask extends AsyncTask<AnnonceModel,Void,Void>
    {
        private AnnonceDao annonceDao;

        private UpdateAnnonceAsyncTask(AnnonceDao annonceDao)
        {
            this.annonceDao=annonceDao;
        }
        @Override
        protected Void doInBackground(AnnonceModel... annonceModels) {
            annonceDao.updateAnnonce(annonceModels[0]);
            return null;
        }
    }



    public static class DeleteAnnonceAsyncTask extends AsyncTask<AnnonceModel,Void,Void>
    {
        private AnnonceDao annonceDao;

        private DeleteAnnonceAsyncTask(AnnonceDao annonceDao)
        {
            this.annonceDao=annonceDao;
        }
        @Override
        protected Void doInBackground(AnnonceModel... annonceModels) {
            annonceDao.deleteAnnonce(annonceModels[0]);
            return null;
        }
    }

   public void uploadImage(Uri file)
   {
       if(file != null)
       {
           String fileName = UUID.randomUUID().toString() + ".jpg";


       }
   }


}
