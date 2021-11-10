package com.example.cinemavillageapplication.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.dao.UserDao;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.UserModel;
import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {

    private TextView tvUser,nombreAnnonces,AnnoncePublierText;
    private TextInputEditText name,prename,email,password, numTel,adress,username;
    private Button modifier;

    UserDao userDao;

    private UserModel user;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        user = (UserModel) getActivity().getIntent().getSerializableExtra("User");
        tvUser = view.findViewById(R.id.tv_user);
        username = view.findViewById(R.id.usernameProf);
        name = view.findViewById(R.id.name_profile);
        prename = view.findViewById(R.id.prenom_profile);
        email = view.findViewById(R.id.email_profile);
        numTel = view.findViewById(R.id.num_tel_profile);
        password = view.findViewById(R.id.password_profile);
        adress = view.findViewById(R.id.adresse_profile);
        nombreAnnonces = view.findViewById(R.id.Annonces_publies);
        AnnoncePublierText = view.findViewById(R.id.Annonces_publies_text);


        if (user != null) {
            tvUser.setText(" " + user.getUsername());
            String nbr = Integer.toString(AppDataBase.getInstance(getActivity().getApplicationContext()).annonceDao().getNombreAnnonces(user.getUsername()));
            String nbrSignals = Integer.toString(AppDataBase.getInstance(getActivity().getApplicationContext()).signalsDao().getNombreSignals(user.getId()));
           if(user.getRole().equals("Realisateur")) {
               AnnoncePublierText.setText("Annonces signalés");

               nombreAnnonces.setText(nbrSignals);
           }
           else {
               nombreAnnonces.setText(nbr);

           }
            username.setText(""+user.getUsername());
            name.setText("" + user.getNom());
            prename.setText(""+user.getPrenom());
            email.setText("" + user.getMail());
            numTel.setText(""+user.getNumTel());
            password.setText(" "+user.getMdp());
            adress.setText(" "+user.getAdresse());
        }

        modifier = view.findViewById(R.id.modif);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateUser();
            }
        });

        return view;
    }

    public void updateUser()
    {

        String nom = name.getText().toString();
        String usernam = username.getText().toString();
        String prenom = prename.getText().toString();
        String mail = email.getText().toString();
        String tel = numTel.getText().toString();
        String pswrd = password.getText().toString();
        String adr = adress.getText().toString();

        UserModel userModel = AppDataBase.getInstance(getContext()).userDao().getUserByUsername(usernam);
        userModel.setUsername(usernam);
        userModel.setNom(nom);
        userModel.setPrenom(prenom);
        userModel.setMail(mail);
        userModel.setNumTel(tel);
        userModel.setMdp(pswrd);
        userModel.setAdresse(adr);

        AppDataBase.getInstance(getContext()).userDao().updateUser(userModel);


    }
}