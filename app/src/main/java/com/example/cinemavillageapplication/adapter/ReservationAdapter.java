package com.example.cinemavillageapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.ReservationModel;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder>{

    List<ReservationModel> reservations = new ArrayList<>();
    ItemClickListener clickListener;

    Context context;


    public ReservationAdapter(ItemClickListener clickListener, Context context)
    {
        this.context = context;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vertical_reservation,parent,false);
        return new ReservationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationHolder holder, int position) {

        ReservationModel currentReservation = reservations.get(position);
        holder.ItemannonceReservation.setText(currentReservation.getAnnonceTitle());
        holder.ItemuserReservation.setText(currentReservation.getUtilisateurUserName());
        holder.Itemdatedebut.setText(currentReservation.getStartTime().toString());
        holder.Itemdatefin.setText(currentReservation.getEndTime().toString());
        holder.ItemEtat.setText(currentReservation.getEtat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(reservations.get(holder.getAdapterPosition()));
            }
        });
    }
    public ReservationModel getReservationAt(int position)
    {
        return reservations.get(position);
    }
    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public void setReservations(List<ReservationModel> reservations)
    {
        this.reservations = reservations;
        notifyDataSetChanged();
    }

    class ReservationHolder extends RecyclerView.ViewHolder
    {
        private TextView ItemuserReservation;
        private TextView ItemannonceReservation;
        private TextView Itemdatedebut;
        private TextView Itemdatefin;
        private TextView ItemEtat;

        public ReservationHolder(@NonNull View itemView) {
            super(itemView);
            ItemuserReservation = itemView.findViewById(R.id.ItemuserReservation);
            ItemannonceReservation = itemView.findViewById(R.id.ItemAnnonceReservation);
            Itemdatedebut = itemView.findViewById(R.id.ItemDateDebut);
            Itemdatefin = itemView.findViewById(R.id.ItemDateFin);
            ItemEtat = itemView.findViewById(R.id.ItemEtatReservation);

        }


    }

    public interface ItemClickListener{
        public void onItemClick(ReservationModel reservationModel);
    }
}
