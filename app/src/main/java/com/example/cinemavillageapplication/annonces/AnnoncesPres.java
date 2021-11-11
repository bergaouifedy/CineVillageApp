package com.example.cinemavillageapplication.annonces;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.LocalisationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class AnnoncesPres extends Dialog implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<LatLng> locationList;
    Context context;
    FragmentManager fragmentManager;
    MapView mapView;
    TextView tv;

    public AnnoncesPres(@NonNull Context context, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.annonces_pres);
        tv = findViewById(R.id.tv1);

        List<LocalisationModel> Localisations = AppDataBase.getInstance(context).localisationDao().getAllLocalisations();
        locationList = new ArrayList<>();
        Double longtitude = 0.0;
        Double latitude = 0.0;
        mapView = (MapView) findViewById(R.id.map2);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        for(int i=0;i<Localisations.size();i++) {
            latitude = Localisations.get(i).getLongitude();
            longtitude = Localisations.get(i).getLatitude();
            LatLng XX = new LatLng(latitude,longtitude);
            locationList.add(XX);
        }


    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        List<LocalisationModel> Localisations = AppDataBase.getInstance(context).localisationDao().getAllLocalisations();

        for (int i = 0; i < locationList.size(); i++) {
            AnnonceModel annonceModel = AppDataBase.getInstance(context).annonceDao().getAnnonceById(Localisations.get(i).getAnnonceId());

            int siz = Localisations.size();
            // below line is use to add marker to each location of our array list.

            String title = annonceModel.getTitle();


            mMap.addMarker(new MarkerOptions().position(locationList.get(i)).title(siz+" Annonces à proximité, "+title));

            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));


            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationList.get(i),16.0f));
        }
    }

}
