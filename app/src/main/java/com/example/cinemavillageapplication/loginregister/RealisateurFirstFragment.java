package com.example.cinemavillageapplication.loginregister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinemavillageapplication.R;
import com.google.android.material.textfield.TextInputEditText;


public class RealisateurFirstFragment extends Fragment {

    TextInputEditText nom,prenom,username, nomsociete, numTel, Adress;
    Button next;

    public RealisateurFirstFragment() {
        // Required empty public constructor
    }


    public static RealisateurFirstFragment newInstance() {
        RealisateurFirstFragment fragment = new RealisateurFirstFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_realisateur_first, container, false);

        nom = (TextInputEditText) view.findViewById(R.id.nom);
        prenom = (TextInputEditText) view.findViewById(R.id.prenom);
        username = (TextInputEditText) view.findViewById(R.id.username);
        nomsociete = (TextInputEditText) view.findViewById(R.id.nomSociete);
        Adress = (TextInputEditText) view.findViewById(R.id.adress);
        numTel = (TextInputEditText) view.findViewById(R.id.numTel);

        next = (Button) view.findViewById(R.id.nextButton);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment();
            }
        });
        return view;
    }

    public void loadFragment() {
        Fragment fragment = RealisateurSecondRegistrationFragment.newInstance();
        String name = nom.getText().toString();
        String prename = prenom.getText().toString();
        String usernam = username.getText().toString();
        String societyname = nomsociete.getText().toString();
        String telnum = numTel.getText().toString();
        String adress = Adress.getText().toString();
        if(name.trim().isEmpty()||prename.trim().isEmpty()||adress.trim().isEmpty()||societyname.trim().isEmpty()||telnum.trim().isEmpty())
        {
            Toast.makeText(getContext(),"Remplissez tous les champs",Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle data = new Bundle();
        data.putString("name",name);
        data.putString("prename",prename);
        data.putString("username",usernam);
        data.putString("socityname",societyname);
        data.putString("telnum",telnum);
        data.putString("adress",adress);

        fragment.setArguments(data);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, null).addToBackStack(null);
        transaction.commit();
    }

}