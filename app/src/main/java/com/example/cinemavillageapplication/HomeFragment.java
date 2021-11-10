package com.example.cinemavillageapplication;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cinemavillageapplication.adapter.AnnonceAdapter;
import com.example.cinemavillageapplication.annonces.AddAnnonceActivity;
import com.example.cinemavillageapplication.annonces.AnnonceViewModel;
import com.example.cinemavillageapplication.annonces.DetailsFragment;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements AnnonceAdapter.ItemClickListener{

    private AnnonceViewModel annonceViewModel;
    private UserModel user;

    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


            RecyclerView recyclerView = view.findViewById(R.id.recyler_annonces);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

            AnnonceAdapter adapter = new AnnonceAdapter(this, getContext());
            recyclerView.setAdapter(adapter);

            annonceViewModel = ViewModelProviders.of(this).get(AnnonceViewModel.class);
            annonceViewModel.getAllAnnonces().observe(getActivity(), new Observer<List<AnnonceModel>>() {
                @Override
                public void onChanged(List<AnnonceModel> annonceModels) {
                    adapter.setAnnonces(annonceModels);
                }
            });

        return view;
    }


    @Override
    public void onItemClick(AnnonceModel annonceModel) {
        String details;
        String dispo = "disponible";
        if(annonceModel.isDisponibilite()==true){
            dispo= "disponible";
        } else
        {
            dispo = "non disponible";
        }
        if ((annonceModel.getCategorie().equalsIgnoreCase("maison"))||(annonceModel.getCategorie().equalsIgnoreCase("villa")))
        {
        details =   "Categorie: " +annonceModel.getCategorie()+
                "\n Adresse: " +annonceModel.getAdresse()+
                " \n Nombre de chambres: "+annonceModel.getNbrChambres()+
                "\n Nombre d'étages: "+annonceModel.getNbrEtages()+
                "\n Date d'ajout: " +annonceModel.getDateajout()+
                "\n Disponibilité: " + dispo;
        }else {
            details = "Categorie: " +annonceModel.getCategorie()+
                    "\n Adresse: " +annonceModel.getAdresse()+
                    "\n Surface: "+annonceModel.getSurface()+" m²"+
                    "\n Date d'ajout: " +annonceModel.getDateajout()+
                    "\n Disponibilité: " + dispo;

        }
        Uri uri = Uri.parse(annonceModel.getImage());
        Fragment fragment = DetailsFragment.newInstance(annonceModel.getId(),uri,annonceModel.getTitle(),annonceModel.getDescription(),annonceModel.getPrix(),annonceModel.getAdresse(),
                annonceModel.getUsername(), details);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}