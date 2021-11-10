package com.example.cinemavillageapplication.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinemavillageapplication.R;


public class FragmentRegisterStepOne extends Fragment {


    public FragmentRegisterStepOne() {
        // Required empty public constructor
    }


    Button btnReal;
    Button btnBail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_step_one, container, false);
        btnReal = (Button) view.findViewById(R.id.buttonReal);
        btnBail = (Button) view.findViewById(R.id.buttonBailleur);


        btnReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadfragmentReal();
            }
        });

        btnBail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragmentBaill();
            }
        });


        return view;
    }

    public static FragmentRegisterStepOne newInstance() {
        FragmentRegisterStepOne fragment = new FragmentRegisterStepOne();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void loadfragmentReal() {
        Fragment fragment = RealisateurFirstFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, null).addToBackStack(null);
        transaction.commit();

    }

    public void loadFragmentBaill() {
        Fragment fragment = BailleurFirstFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, null).addToBackStack(null);
        transaction.commit();
    }
}