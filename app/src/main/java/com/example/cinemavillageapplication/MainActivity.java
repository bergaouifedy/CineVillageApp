package com.example.cinemavillageapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cinemavillageapplication.annonces.AddAnnonceActivity;
import com.example.cinemavillageapplication.annonces.AnnonceViewModel;
import com.example.cinemavillageapplication.annonces.BailleurAnnoncesFragment;
import com.example.cinemavillageapplication.loginregister.LoginActivity;
import com.example.cinemavillageapplication.loginregister.ProfileFragment;





import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.realisateur.DashboardRealisateurActivity;
import com.example.cinemavillageapplication.realisateur.HomeRealisateurFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener,NavigationView.OnNavigationItemSelectedListener{


    private UserModel user;
    public static final int ADD_ANNONCE_REQUEST = 1;
    private AnnonceViewModel annonceViewModel;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         loadFragment(new HomeFragment(),R.string.home_page_title);


        loadFragment(new HomeRealisateurFragment(), R.string.home_page_title);

        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.toolbar2);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_dashboard).setVisible(false);

        BottomNavigationView navigationView = findViewById(R.id.bottomnavigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.home_page);
        FloatingActionButton buttonAddAnnonce = findViewById(R.id.button_add_annonce);
        buttonAddAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAnnonceActivity.class);
                user = (UserModel) getIntent().getSerializableExtra("User");

                intent.putExtra("User", user);
                startActivityForResult(intent,ADD_ANNONCE_REQUEST);
            }
        });


    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int Title = 0;
        switch (item.getItemId()) {
            case R.id.home_page:
                fragment = new HomeFragment();
                Title = R.string.home_page_title;
                break;
            case R.id.mes_annonces:
                fragment = new BailleurAnnoncesFragment();
                Title = R.string.mes_lieux;
                break;
            case R.id.profile_page:
                fragment = new ProfileFragment();
                Title = R.string.profile_page_title;
                break;
            case R.id.nav_home:
                fragment = new HomeFragment();
                Title = R.string.home_page_title;
                break;
                case R.id.nav_logout:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                onDestroy();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                Title = R.string.profile_page_title;
                break;
            case R.id.nav_mesAnnonces:
                fragment = new BailleurAnnoncesFragment();
                Title = R.string.mes_lieux;
        }
        loadFragment(fragment,Title);

        return true;
    }


    private void loadFragment(Fragment fragment,int string) {

        View var10000 = this.findViewById(R.id.page_title);
        ((TextView)var10000).setText((CharSequence)this.getResources().getString(string));
        //injecter le fragment dans notre boite (fragment_container)
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment, null)
                .commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_ANNONCE_REQUEST && resultCode== RESULT_OK)
        {

            String title = data.getExtras().getString(AddAnnonceActivity.EXTRA_TITLE);
            String description = data.getExtras().getString(AddAnnonceActivity.EXTRA_DESCRIPTION);
            String adress = data.getExtras().getString(AddAnnonceActivity.EXTRA_ADRESS);
            String typetarif = data.getExtras().getString(AddAnnonceActivity.EXTRA_TYPETARIF);
            String cat = data.getExtras().getString(AddAnnonceActivity.EXTRA_CATEGORY);
            int nbretages = data.getExtras().getInt(AddAnnonceActivity.EXTRA_NBRETAGES,1);
            int nbrchambre = data.getExtras().getInt(AddAnnonceActivity.EXTRA_NBRCHAMBRES,1);
            float price = data.getExtras().getFloat(AddAnnonceActivity.EXTRA_PRICE,1);
            String image = data.getExtras().getString(AddAnnonceActivity.EXTRA_IMAGE);
            String username = data.getExtras().getString(AddAnnonceActivity.EXTRA_USERNAME);
            String surface = data.getExtras().getString(AddAnnonceActivity.EXTRA_SURFACE);

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);

            annonceViewModel = ViewModelProviders.of(this).get(AnnonceViewModel.class);

            AnnonceModel annonceModel = new AnnonceModel(title,
                    description,
                    cat,
                    typetarif,
                    adress,
                    price,
                    strDate,
                    true,
                    nbrchambre,
                    nbretages,
                    image,
                    username,
                    surface);
            annonceViewModel.insert(annonceModel);

            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();

        }
    }
}