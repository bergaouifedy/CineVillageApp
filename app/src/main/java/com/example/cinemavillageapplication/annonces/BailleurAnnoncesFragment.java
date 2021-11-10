package com.example.cinemavillageapplication.annonces;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;

import java.util.List;
import java.util.Locale;


public class BailleurAnnoncesFragment extends Fragment implements AnnonceAdapter.ItemClickListener{

    private AnnonceViewModel annonceViewModel;
    private UserModel user;

    Context context;

    public BailleurAnnoncesFragment() {
        // Required empty public constructor
    }


    public static BailleurAnnoncesFragment newInstance() {
        BailleurAnnoncesFragment fragment = new BailleurAnnoncesFragment();

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
        View view = inflater.inflate(R.layout.fragment_bailleur_annonces, container, false);
        user = (UserModel) getActivity().getIntent().getSerializableExtra("User");

        RecyclerView recyclerView = view.findViewById(R.id.recyler_annonces);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        AnnonceAdapter adapter = new AnnonceAdapter(this,getContext());
        recyclerView.setAdapter(adapter);

        annonceViewModel = ViewModelProviders.of(this).get(AnnonceViewModel.class);


        List<AnnonceModel> annonceModel = AppDataBase.getInstance(getContext()).annonceDao().getUsersAnnonce(user.getUsername());
        adapter.setAnnonces(annonceModel);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Suppression d'annonce");
                builder.setMessage("Vous etes sur que vous voulez supprimer l'annonce?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        annonceViewModel.delete(adapter.getAnnonceAt(viewHolder.getAdapterPosition()));
                        Toast.makeText(getContext(),"Annonce supprimé",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Fragment fragment = BailleurAnnoncesFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container,fragment,null);
                        transaction.attach(fragment);
                        transaction.commit();


                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);


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
                    "\n Surface: "+annonceModel.getSurface()+" m²"+
                    "\n Date d'ajout: " +annonceModel.getDateajout()+
                    "\n Disponibilité: " + dispo;

        }



        Uri uri = Uri.parse(annonceModel.getImage().toLowerCase(Locale.ROOT));
        Fragment fragment = DetailsFragment.newInstance(annonceModel.getId(),uri,annonceModel.getTitle(),annonceModel.getDescription(),annonceModel.getPrix(),annonceModel.getAdresse(),
                annonceModel.getUsername(), details);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment,null);
        transaction.commit();
    }
}