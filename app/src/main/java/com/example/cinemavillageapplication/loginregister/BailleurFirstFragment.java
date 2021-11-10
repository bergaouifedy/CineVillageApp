package com.example.cinemavillageapplication.loginregister;

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


public class BailleurFirstFragment extends Fragment {

    TextInputEditText nom,prenom, numTel, Adress, username;
    Button next;


    public BailleurFirstFragment() {
        // Required empty public constructor
    }


    public static BailleurFirstFragment newInstance() {
        BailleurFirstFragment fragment = new BailleurFirstFragment();


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
        View view = inflater.inflate(R.layout.fragment_bailleur_first, container, false);

        nom = (TextInputEditText) view.findViewById(R.id.Bailleurnom);
        prenom = (TextInputEditText) view.findViewById(R.id.Bailleurprenom);
        username = (TextInputEditText) view.findViewById(R.id.BailleurUsername);
        Adress = (TextInputEditText) view.findViewById(R.id.Bailleuradress);
        numTel = (TextInputEditText) view.findViewById(R.id.BailleurnumTel);

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
        Fragment fragment = BailleurSecondRegistrationFragment.newInstance();
        String name = nom.getText().toString();
        String prename = prenom.getText().toString();
        String usernam = username.getText().toString();
        String telnum = numTel.getText().toString();
        String adress = Adress.getText().toString();
        if(name.trim().isEmpty()||prename.trim().isEmpty()||adress.trim().isEmpty()||telnum.trim().isEmpty())
        {
            Toast.makeText(getContext(),"Remplissez tous les champs",Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle data = new Bundle();
        data.putString("name",name);
        data.putString("prename",prename);
        data.putString("username",usernam);
        data.putString("telnum",telnum);
        data.putString("adress",adress);

        fragment.setArguments(data);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, null).addToBackStack(null);
        transaction.commit();
    }

}