package com.example.cinemavillageapplication.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemavillageapplication.MainActivity;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.dao.UserDao;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.realisateur.DashboardRealisateurActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister;
    UserDao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButton);

        textViewRegister = findViewById(R.id.textViewRegister);


        db = AppDataBase.getInstance(this).userDao();


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                UserModel user = db.getUser(email, password);
                if (user != null) {

                    if(user.isVerified) {
                        if (user.getRole().equalsIgnoreCase("Realisateur")) {

                            Intent i = new Intent(LoginActivity.this, DashboardRealisateurActivity.class);
                            i.putExtra("User", user);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("User", user);
                            startActivity(i);
                            finish();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"Votre compte a été verouillé à cause des signals excessifs ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}