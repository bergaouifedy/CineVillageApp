package com.example.cinemavillageapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinemavillageapplication.loginregister.LoginActivity;
import com.example.cinemavillageapplication.loginregister.ProfileFragment;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.realisateur.DashboardRealisateurActivity;
import com.example.cinemavillageapplication.realisateur.HomeRealisateurFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private UserModel user;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button reserver ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user = (UserModel) getIntent().getSerializableExtra("User");

        loadFragment(new HomeRealisateurFragment(), R.string.home_page_title);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_mesAnnonces).setVisible(false);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment, int string) {

        View var10000 = this.findViewById(R.id.page_title);
        ((TextView) var10000).setText((CharSequence) this.getResources().getString(string));
        //injecter le fragment dans notre boite (fragment_container)
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragment_container2, fragment, null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_dashboard:
                Intent intent = new Intent(this, DashboardRealisateurActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                onDestroy();
            case R.id.nav_profile:
                loadFragment(new ProfileFragment(), R.string.profile_page_title);

        }
        return true;
    }
}