package com.example.cinemavillageapplication.annonces;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.repositories.AnnonceRepository;

import java.util.List;

public class AnnonceViewModel extends AndroidViewModel {

    private AnnonceRepository repository;
    private LiveData<List<AnnonceModel>> allAnnonces;
    private List<AnnonceModel> annonceByCategory;
    private List<AnnonceModel> usersAnnonces;

    String user,category;


    public AnnonceViewModel(@NonNull Application application) {
        super(application);

        repository = new AnnonceRepository(application);
        allAnnonces = repository.getAllAnnonces();
        usersAnnonces = repository.getUsersAnnonce(user);
        annonceByCategory = repository.getAnnoncesByCategory(category);
    }

    public void insert(AnnonceModel annonceModel)
    {
        repository.insert(annonceModel);
    }

    public void update(AnnonceModel annonceModel)
    {
        repository.update(annonceModel);
    }

    public void delete(AnnonceModel annonceModel)
    {
        repository.delete(annonceModel);
    }

    public LiveData<List<AnnonceModel>> getAllAnnonces()
    {
        return allAnnonces;
    }

    public List<AnnonceModel> getUsersAnnonces()
    {
        return usersAnnonces;
    }

    public List<AnnonceModel> getAnnoncesByCategory()
    {
        return annonceByCategory;
    }


}
