package com.example.cinemavillageapplication.annonces;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.repositories.AnnonceRepository;
import com.google.android.material.textfield.TextInputLayout;

public class AddAnnonceActivity extends AppCompatActivity {


    // constant to compare the activity result code
    int SELECT_PICTURE = 200;

    //Var
    Uri selectedImageUri;

    public static final String EXTRA_TITLE =
            "com.example.cinemavillageapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.cinemavillageapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRICE =
            "com.example.cinemavillageapplication.EXTRA_PRICE";
    public static final String EXTRA_ADRESS =
            "com.example.cinemavillageapplication.EXTRA_ADRESS";
    public static final String EXTRA_NBRCHAMBRES =
            "com.example.cinemavillageapplication.EXTRA_NBRCHAMBRES";
    public static final String EXTRA_NBRETAGES =
            "com.example.cinemavillageapplication.EXTRA_NBRETAGES";
    public static final String EXTRA_CATEGORY =
            "com.example.cinemavillageapplication.EXTRA_CATEGORY";
    public static final String EXTRA_TYPETARIF =
            "com.example.cinemavillageapplication.EXTRA_TYPETARIF";
    public static final String EXTRA_IMAGE=
            "com.example.cinemavillageapplication.EXTRA_IMAGE";
    public static final String EXTRA_USERNAME=
            "com.example.cinemavillageapplication.EXTRA_USERNAME";

    public static final String EXTRA_SURFACE=
            "com.example.cinemavillageapplication.EXTRA_SURFACE";


    UserModel user;



    EditText titre,description,prix,adresse,nbrEtage,nbrChambres,surface;
    Spinner type,category;
    Button ajouter,uploadImage;
    ImageView previewImage = null;
    TextInputLayout surfaceTI,NbChTi,NbEtTi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_annonce);
        previewImage = findViewById(R.id.previewImage);
        titre = findViewById(R.id.add_lieu_page_title_lieu_input);
        description = findViewById(R.id.add_lieu_page_description_input);
        prix =findViewById(R.id.add_lieu_page_price_input);
        adresse =findViewById(R.id.add_lieu_page_adresse_input);
        nbrChambres = findViewById(R.id.add_lieu_page_nbrChambre_input);
        nbrEtage = findViewById(R.id.add_lieu_page_nbrEtages_input);

        type = findViewById(R.id.add_lieu_page_TypeTarification_input);
        category = findViewById(R.id.add_lieu_page_Category_input);
        surface = findViewById(R.id.add_lieu_page_surface_input);
        surfaceTI = findViewById(R.id.surfaceTV);
        NbChTi = findViewById(R.id.NbChTv);
        NbEtTi = findViewById(R.id.NbEtTv);


        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if((adapterView.getItemAtPosition(i).toString().equals("Maison"))
                        || (adapterView.getItemAtPosition(i).toString().equals("Villa")))
                {
                    nbrEtage.setVisibility(View.VISIBLE);
                    nbrChambres.setVisibility(View.VISIBLE);
                    NbChTi.setVisibility(View.VISIBLE);
                    NbEtTi.setVisibility(View.VISIBLE);
                    surface.setVisibility(View.GONE);
                    surfaceTI.setVisibility(View.GONE);
                }
                else {
                    surface.setVisibility(View.VISIBLE);
                    surfaceTI.setVisibility(View.VISIBLE);
                    nbrEtage.setVisibility(View.GONE);
                    NbEtTi.setVisibility(View.GONE);
                    nbrChambres.setVisibility(View.GONE);
                    NbChTi.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        uploadImage = findViewById(R.id.add_lieu_page_upload_button);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
        ajouter = findViewById(R.id.ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnnonce();
            }
        });
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        //setTitle("Add Note");
    }




    private void saveAnnonce()
    {

        String title = titre.getText().toString();
        String desc = description.getText().toString();
        Float price = Float.parseFloat(prix.getText().toString());
        String adress = adresse.getText().toString();
        //int nbChambres = Integer.parseInt(nbrChambres.getText().toString());
        //int nbEtages = Integer.parseInt(nbrEtage.getText().toString());
        String cat = category.getSelectedItem().toString();
        String typeTarif = type.getSelectedItem().toString();
        String imageA = selectedImageUri.toString();
        user = (UserModel) getIntent().getSerializableExtra("User");
        String username = user.getUsername();
        String surfaceAnnonce;
        if(surface.getText().toString().isEmpty())
        {
            surfaceAnnonce = "";

        } else
        {
            surfaceAnnonce = surface.getText().toString();
        }

        int nombreChambres,nombreEtages;
        if((nbrChambres.getText().toString().isEmpty())&&(nbrEtage.getText().toString().isEmpty()))
        {
            nombreChambres= 0;
            nombreEtages = 0;
        } else
        {
            nombreChambres = Integer.parseInt(nbrChambres.getText().toString());
            nombreEtages = Integer.parseInt(nbrEtage.getText().toString());
        }
        if(title.trim().isEmpty()||desc.trim().isEmpty()||adress.trim().isEmpty()||cat.trim().isEmpty()||typeTarif.trim().isEmpty())
        {
            Toast.makeText(this,"Remplissez tous les champs",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_DESCRIPTION, desc);
        bundle.putString(EXTRA_ADRESS, adress);
        bundle.putString(EXTRA_CATEGORY, cat);
        bundle.putString(EXTRA_TYPETARIF, typeTarif);
        bundle.putInt(EXTRA_NBRCHAMBRES, nombreChambres);
        bundle.putInt(EXTRA_NBRETAGES, nombreEtages);
        bundle.putFloat(EXTRA_PRICE, price);
        bundle.putString(EXTRA_IMAGE,imageA);
        bundle.putString(EXTRA_USERNAME,username);
        bundle.putString(EXTRA_SURFACE,surfaceAnnonce);

        data.putExtras(bundle);

        setResult(RESULT_OK, data);
        finish();
    }


    private void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    previewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

}