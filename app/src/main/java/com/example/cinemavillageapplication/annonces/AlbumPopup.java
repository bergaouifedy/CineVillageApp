package com.example.cinemavillageapplication.annonces;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.models.AlbumModel;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;

public class AlbumPopup extends Dialog {

    AlbumModel albumModel;
    Context context;
    Activity activity;
    FragmentManager fragmentManager;
    int position = 1;

    private ImageSwitcher imageIs,is;

    private Button prev,next;

    public AlbumPopup(int position , Activity activity, @NonNull FragmentManager fragmentManager, Context context,AlbumModel albumModel) {
        super(context);

        this.albumModel =  albumModel;
        this.context = context;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.popup_album);

        next = findViewById(R.id.NextBtn);
        prev = findViewById(R.id.PreviousBtn);

        imageIs = findViewById(R.id.imagesIs);


        imageIs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(context);
                return imageView;
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position>0) {
                    position--;
                    imageIs.setImageURI(Uri.parse(albumModel.getImages().get(position)));
                } else {
                    Toast.makeText(context,"Pas d'images",Toast.LENGTH_LONG).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position<albumModel.getImages().size() -1)
                {
                    position++;
                    imageIs.setImageURI(Uri.parse(albumModel.getImages().get(position)));

                } else {
                    Toast.makeText(context,"Pas d'images",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
