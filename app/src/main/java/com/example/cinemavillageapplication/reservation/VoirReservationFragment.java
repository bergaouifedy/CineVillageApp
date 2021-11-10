package com.example.cinemavillageapplication.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cinemavillageapplication.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoirReservationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoirReservationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;


    TextView user,annonce,datedeb,datefin,etat;

    public VoirReservationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VoirReservationFragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        VoirReservationFragment fragment = new VoirReservationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_voir_reservation, container, false);

        user = view.findViewById(R.id.userReservation);
        annonce = view.findViewById(R.id.AnnonceReservation);
        datedeb = view.findViewById(R.id.DateDebut);
        datefin = view.findViewById(R.id.DateFin);
        etat = view.findViewById(R.id.EtatReservation);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user.setText(mParam4);
        annonce.setText(mParam3);
        datedeb.setText(mParam1);
        datefin.setText(mParam2);
        etat.setText(mParam5);


    }
}