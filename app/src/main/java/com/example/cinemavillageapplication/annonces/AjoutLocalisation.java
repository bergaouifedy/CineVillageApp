package com.example.cinemavillageapplication.annonces;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.LocalisationModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AjoutLocalisation extends Dialog {

    AnnonceModel annonceModel;
    FragmentManager fragmentManager;
    Context context;
    Activity activity;

    Button btLocalisation;
    Button sauvegarder;

    TextView textView1,textView2,textView3,textView4,textView5;

    FusedLocationProviderClient fusedLocationProviderClient ;


    public AjoutLocalisation(Activity activity,@NonNull FragmentManager fragmentManager, Context context, AnnonceModel annonceModel) {
        super(context);

        this.annonceModel =  annonceModel;
        this.context = context;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.ajout_localisation);

        btLocalisation = findViewById(R.id.bt_local);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);
        sauvegarder = findViewById(R.id.sauvegarder);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        btLocalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                {
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if(location != null){
                                try {
                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(
                                            location.getLatitude(), location.getLongitude(), 1
                                    );
                                    textView1.setText(Double.toString(addresses.get(0).getLatitude()));
                                    textView2.setText(Double.toString(addresses.get(0).getLongitude()));
                                    textView3.setText("Country : "+addresses.get(0).getCountryName());
                                    textView4.setText("Locality : "+addresses.get(0).getLocality());
                                    textView5.setText("Adresse : "+addresses.get(0).getAddressLine(0));

                                }catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });

        sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double latitude = Double.parseDouble(textView1.getText().toString());
                Double longtitude = Double.parseDouble(textView2.getText().toString());
                LocalisationModel localisationModel = new LocalisationModel(
                        latitude,
                        longtitude,
                        textView3.getText().toString(),
                        textView4.getText().toString(),
                        textView5.getText().toString(),
                        annonceModel.getId()

                );
                if(localisationModel!= null) {
                    AppDataBase.getInstance(context).localisationDao().insertLocalisation(localisationModel);
                    Toast.makeText(context, "Localisation ajouté avec succées", Toast.LENGTH_LONG).show();
                    dismiss();
                }else {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }
        });
    }


}
