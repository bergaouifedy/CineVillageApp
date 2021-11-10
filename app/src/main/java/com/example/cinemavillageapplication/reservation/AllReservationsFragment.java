package com.example.cinemavillageapplication.reservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.adapter.ReservationAdapter;
import com.example.cinemavillageapplication.annonces.BailleurAnnoncesFragment;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.ReservationModel;

import java.util.List;


public class AllReservationsFragment extends Fragment implements ReservationAdapter.ItemClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_reservations, container, false);

        Bundle bundle = this.getArguments();
        int annonceId = bundle.getInt("annonce");
        int userId = bundle.getInt("user");
        RecyclerView recyclerView = view.findViewById(R.id.recyler_reservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ReservationAdapter adapter = new ReservationAdapter(this,getContext());
        recyclerView.setAdapter(adapter);
        List<ReservationModel> reservationModels = AppDataBase.getInstance(getContext()).reservationDao().getAllReservationByAnnonce(annonceId);
        adapter.setReservations(reservationModels);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true); builder.setTitle("Accepter la réservation");
                builder.setMessage("Vous etes sur que vous voulez accepter la réservation ?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                       ReservationModel res= AppDataBase.getInstance(getContext()).reservationDao().getReservation(adapter.getReservationAt(viewHolder.getAdapterPosition()).getId());
                      res.setEtat("Accepté");
                      AppDataBase.getInstance(getContext()).reservationDao().UpdateReservation(res);
                      Toast.makeText(getContext(),"Réservation acceptée",Toast.LENGTH_LONG).show();
                        List<ReservationModel> reservationModels2 = AppDataBase.getInstance(getContext()).reservationDao().getAllReservationByAnnonce(annonceId);
                        adapter.setReservations(reservationModels2);
                        AnnonceModel annonceModel = AppDataBase.getInstance(getContext()).annonceDao().getAnnonceByTitle(res.getAnnonceTitle());
                        annonceModel.setDisponibilite(false);
                        AppDataBase.getInstance(getContext()).annonceDao().updateAnnonce(annonceModel);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        adapter.setReservations(reservationModels);


                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true); builder.setTitle("Refuser la réservation");
                builder.setMessage("Vous etes sur que vous voulez refuser la réservation ?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ReservationModel res1= AppDataBase.getInstance(getContext()).reservationDao().getReservation(adapter.getReservationAt(viewHolder.getAdapterPosition()).getId());
                        res1.setEtat("Refusée");
                        AppDataBase.getInstance(getContext()).reservationDao().UpdateReservation(res1);
                        Toast.makeText(getContext(),"Réservation refusée",Toast.LENGTH_LONG).show();
                        List<ReservationModel> reservationModels3 = AppDataBase.getInstance(getContext()).reservationDao().getAllReservationByAnnonce(annonceId);
                        adapter.setReservations(reservationModels3);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        adapter.setReservations(reservationModels);


                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);
        return  view;
    }

    @Override
    public void onItemClick(ReservationModel reservationModel) {

    }
}