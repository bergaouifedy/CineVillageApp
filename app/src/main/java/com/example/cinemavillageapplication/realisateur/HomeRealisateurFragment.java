package com.example.cinemavillageapplication.realisateur;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.adapter.AnnonceAdapter;
import com.example.cinemavillageapplication.annonces.AnnonceViewModel;
import com.example.cinemavillageapplication.annonces.DetailsFragment;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;

import java.util.List;

public class HomeRealisateurFragment extends Fragment implements AnnonceAdapter.ItemClickListener{


    private AnnonceViewModel annonceViewModel;


    public HomeRealisateurFragment() {
        // Required empty public constructor
    }

    public static HomeRealisateurFragment newInstance() {
        HomeRealisateurFragment fragment = new HomeRealisateurFragment();

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
        View view = inflater.inflate(R.layout.fragment_home_realisateur, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras.getString("category") == null) {


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

        } else
        {
            String category = extras.getString("category");

            RecyclerView recyclerView = view.findViewById(R.id.recyler_annonces);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

            AnnonceAdapter adapter = new AnnonceAdapter(this, getContext());
            recyclerView.setAdapter(adapter);

            annonceViewModel = ViewModelProviders.of(this).get(AnnonceViewModel.class);
            List<AnnonceModel> annonceModel = AppDataBase.getInstance(getContext()).annonceDao().getAnnoncesByCategory(category);
            adapter.setAnnonces(annonceModel);

        }
        return view;
    }


    @Override
    public void onItemClick(AnnonceModel annonceModel) {
        String details;
        String dispo ;
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
                    "\n Date d'ajout: " +annonceModel.getDateajout()+
                    "\n Surface: "+annonceModel.getSurface()+
                    "\n Disponibilité: "+dispo;
        }
        Uri uri = Uri.parse(annonceModel.getImage());
        Fragment fragment = DetailsFragment.newInstance(annonceModel.getId(),uri,annonceModel.getTitle(),annonceModel.getDescription(),annonceModel.getPrix(),annonceModel.getAdresse(),
                annonceModel.getUsername(), details);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container2,fragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}