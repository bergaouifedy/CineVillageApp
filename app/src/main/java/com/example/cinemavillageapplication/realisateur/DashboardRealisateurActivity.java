package com.example.cinemavillageapplication.realisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemavillageapplication.MainActivity;
import com.example.cinemavillageapplication.MainActivity2;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.models.UserModel;

public class DashboardRealisateurActivity extends AppCompatActivity {

    ImageView tous,maison , villa , espace;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_realisateur);

        user = (UserModel) getIntent().getSerializableExtra("User");


        tous = findViewById(R.id.TousAnnonces);
        tous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardRealisateurActivity.this, MainActivity2.class);
                intent.putExtra("User", user);
                startActivity(intent);
                onDestroy();
            }
        });

        maison = findViewById(R.id.maisons);

        maison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = "Maison";
                Intent intent = new Intent(DashboardRealisateurActivity.this, MainActivity2.class);
                intent.putExtra("category",category);
                intent.putExtra("User", user);
                startActivity(intent);
                onDestroy();


            }
        });

        villa = findViewById(R.id.Villas);

        villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = "Villa";
                Intent intent = new Intent(DashboardRealisateurActivity.this, MainActivity2.class);
                intent.putExtra("category",category);
                intent.putExtra("User", user);
                startActivity(intent);
                onDestroy();


            }
        });

        espace = findViewById(R.id.Commerce);
        espace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = "Espace Commercial";
                Intent intent = new Intent(DashboardRealisateurActivity.this, MainActivity2.class);
                intent.putExtra("category",category);
                intent.putExtra("User", user);
                startActivity(intent);
                onDestroy();


            }
        });
    }
}