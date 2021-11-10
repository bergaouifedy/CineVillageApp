package com.example.cinemavillageapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.AnnonceHolder> {
    private List<AnnonceModel> annonces = new ArrayList<>();
    Context context;

    ItemClickListener clickListener;

    public AnnonceAdapter(ItemClickListener clickListener,Context context)
    {

        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AnnonceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vertical_asset,parent,false);
        return new AnnonceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceHolder holder, int position) {


        AnnonceModel currentAnnonce = annonces.get(position);
        UserModel userModel = AppDataBase.getInstance(context).userDao().getUserByUsername(currentAnnonce.getUsername());
        if(!userModel.isVerified)
        {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

        }else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.textViewTitle.setText(currentAnnonce.getTitle());
            holder.textViewDescription.setText(currentAnnonce.getDescription());
            holder.textViewUsername.setText(currentAnnonce.getUsername());
            holder.textViewPrix.setText(Float.toString(currentAnnonce.getPrix()) + " DT" + " " + currentAnnonce.getTypetarification());
            if (context != null) {

                Glide.with(context).load(Uri.parse(currentAnnonce.getImage())).into(holder.imageViewAsset);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(annonces.get(holder.getAdapterPosition()));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return annonces.size();
    }

    public void setAnnonces(List<AnnonceModel> annonces)
    {
        this.annonces=annonces;
        notifyDataSetChanged();
    }

    public AnnonceModel getAnnonceAt(int position)
    {
        return annonces.get(position);
    }

    class AnnonceHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewUsername;
        private TextView textViewPrix;
        private ImageView imageViewAsset;

        public AnnonceHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.TitreItem);
            textViewDescription = itemView.findViewById(R.id.DescriptionItem);
            textViewUsername = itemView.findViewById(R.id.username);
            textViewPrix = itemView.findViewById(R.id.PrixItem);
            imageViewAsset = itemView.findViewById(R.id.image_asset2);

        }
    }

    public interface ItemClickListener{
        public void onItemClick(AnnonceModel annonceModel);
    }

}
