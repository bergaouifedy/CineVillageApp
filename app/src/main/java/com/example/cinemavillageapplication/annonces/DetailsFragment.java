package com.example.cinemavillageapplication.annonces;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.loginregister.BailleurSecondRegistrationFragment;
import com.example.cinemavillageapplication.models.AlbumModel;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.LocalisationModel;
import com.example.cinemavillageapplication.models.ReservationModel;
import com.example.cinemavillageapplication.models.UserModel;
import com.example.cinemavillageapplication.reservation.AllReservationsFragment;
import com.example.cinemavillageapplication.reservation.VoirReservationFragment;
import com.example.cinemavillageapplication.signaler.SignalerPopup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.InflaterInputStream;


public class DetailsFragment extends Fragment implements OnMapReadyCallback {


    Context context;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "param7";
    private static final String ARG_PARAM8 = "param8";
    private UserModel user;

    ArrayList<Uri> selectedImagesUri;

    int position = 0;

    private Toolbar toolbar;
    private ImageView ImageAnnonce;
    private TextView annonceDes, annonceAdress, annoncePrix, username, signals, adressLocalisation;
    private Button reserver, signaler, ajouterAlbum, album;


    private Button pickDate;
    private Button endTimeButton;
    private Button bookButton;
    private Button reservations;
    private Button localisation;
    private Button AjouterLocalisation;
    private static String timeId;
    private static Date startTime;
    private static Date endTime;

    private CardView cd;
    private CardView cdMap;
    private String mParam1;
    private String mParam2;
    private float mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;
    private String mParam7;
    private int mParam8;

    int SELECT_PICTURE = 200;

    GoogleMap map;
    private MapView mapView;


    public DetailsFragment() {
        // Required empty public constructor
    }


    public static DetailsFragment newInstance(int param8,
                                              Uri param7,
                                              String param1,
                                              String param2,
                                              float param3,
                                              String param4,
                                              String param5,
                                              String param6
    ) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putFloat(String.valueOf(ARG_PARAM3), param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        args.putString(ARG_PARAM7, param7.toString());
        args.putInt(ARG_PARAM8, param8);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getFloat(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);
            mParam7 = getArguments().getString(ARG_PARAM7);
            mParam8 = getArguments().getInt(ARG_PARAM8);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        toolbar = view.findViewById(R.id.toolbarInfo);
        ImageAnnonce = view.findViewById(R.id.ImageAnnonce);
        username = view.findViewById(R.id.username);
        annonceDes = view.findViewById(R.id.annonce_descr_detail);
        reserver = view.findViewById(R.id.Reserv);
        annonceAdress = view.findViewById(R.id.annonce_adress_detail);
        annoncePrix = view.findViewById(R.id.annonce_prix_detail);
        signaler = view.findViewById(R.id.signaler);
        signals = view.findViewById(R.id.signals);
        cd = view.findViewById(R.id.resCard);
        cd.setVisibility(View.INVISIBLE);

        cdMap = view.findViewById(R.id.mapcd);
        cdMap.setVisibility(View.INVISIBLE);

        localisation = view.findViewById(R.id.VoirMap);
        adressLocalisation = view.findViewById(R.id.adressLocalisation);


        reservations = view.findViewById(R.id.reservations);
        reservations.setVisibility(View.INVISIBLE);

        AjouterLocalisation = view.findViewById(R.id.AjoutLocalisation);
        AjouterLocalisation.setVisibility(View.INVISIBLE);

        pickDate = view.findViewById(R.id.pick_date);
        endTimeButton = view.findViewById(R.id.end_time_button);
        bookButton = view.findViewById(R.id.book_button);


        ajouterAlbum = view.findViewById(R.id.AjoutAlbum);
        ajouterAlbum.setVisibility(View.INVISIBLE);
        album = view.findViewById(R.id.album);
        album.setVisibility(View.INVISIBLE);


        AlbumModel albumM = AppDataBase.getInstance(getContext()).albumDao().getAlbumByAnnonce(mParam8);
        if (albumM != null) {
            ajouterAlbum.setVisibility(View.INVISIBLE);
        }

        user = (UserModel) getActivity().getIntent().getSerializableExtra("User");

        if (user.getRole().equalsIgnoreCase("Bailleur")) {
            reserver.setVisibility(View.INVISIBLE);
        }


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        toolbar.setTitle(mParam1);

        annonceDes.setText(mParam2 + " \n More details: \n" + mParam6);
        annonceAdress.setText(mParam4);
        annoncePrix.setText(String.valueOf(mParam3) + " Dt");
        username.setText(mParam5);
        context = getContext();

        signals.setText(AppDataBase.getInstance(getContext()).signalsDao().getRowCount(mParam8) + " Signales au total");

        if (context != null) {
            Glide
                    .with(context).load(mParam7).into(ImageAnnonce);
        }
        if (username.getText().equals(user.getUsername())) {
            signaler.setVisibility(View.INVISIBLE);
        }

        if (!username.getText().equals(user.getUsername())) {
            signals.setVisibility(View.INVISIBLE);
        }


        selectedImagesUri = new ArrayList<>();
        ajouterAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });


        AnnonceModel annonceModel = AppDataBase.getInstance(getContext()).annonceDao().getAnnonceByTitle(mParam1);

        signaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SignalerPopup(getActivity().getSupportFragmentManager(), getContext(), user, annonceModel).show();

            }
        });

        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cdMap.getVisibility() == View.INVISIBLE) {
                    cdMap.setVisibility(View.VISIBLE);
                } else {
                    cdMap.setVisibility(View.INVISIBLE);
                }
            }
        });

        AlbumModel albumModel1 = AppDataBase.getInstance(getContext()).albumDao().getAlbumByAnnonce(mParam8);
        if(albumModel1!=null) {
            album.setVisibility(View.VISIBLE);
            album.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlbumPopup(1, getActivity(), getActivity().getSupportFragmentManager(), getContext(), albumModel1).show();
                }
            });
        }
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.getVisibility() == View.INVISIBLE) {
                    cd.setVisibility(View.VISIBLE);

                } else {
                    cd.setVisibility(View.INVISIBLE);

                }
            }
        });


        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeId = "start";
                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });


        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeId = "end";

                DialogFragment dFragment = new DatePickerFragment();

                // Show the date picker dialog fragment
                dFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pickDate.getText().equals("Choisir date de debut")
                        || endTimeButton.getText().equals("Choisir date de fin")) {
                    Toast.makeText(getContext(), "Please fill in the required fields", Toast.LENGTH_LONG).show();

                } else {

                    ReservationModel reservationModel = new ReservationModel(user.getId(), startTime, endTime, annonceModel.getId(), "En attente", user.getUsername(), annonceModel.getTitle());
                    if (reservationModel != null) {
                        if (annonceModel.isDisponibilite()) {
                            AppDataBase.getInstance(getContext()).reservationDao().insertReservation(reservationModel);
                            Toast.makeText(getContext(), "Congratulations! Your booking is now pending!", Toast.LENGTH_LONG).show();
                            cd.setVisibility(View.GONE);
                            String start = reservationModel.getStartTime().toString();
                            String end = reservationModel.getEndTime().toString();
                            Fragment fragment = VoirReservationFragment.newInstance(start, end, reservationModel.getAnnonceTitle(), reservationModel.getUtilisateurUserName(), reservationModel.getEtat());
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container2, fragment, null);
                            transaction.commit();
                        } else {
                            Toast.makeText(getContext(), "Cette annonce n'est pas disponible", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Toast.makeText(getContext(), "errooooooor", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        LocalisationModel loc = AppDataBase.getInstance(getContext()).localisationDao().getLocalisationByAnnonce(mParam8);
        if (loc == null) {
            localisation.setVisibility(View.INVISIBLE);

        }
        if ((user.getRole().equals("Bailleur")) && (user.getUsername().equals(mParam5)) && (loc == null)) {
            AjouterLocalisation.setVisibility(View.VISIBLE);
            AjouterLocalisation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AjoutLocalisation(getActivity(), getActivity().getSupportFragmentManager(), getContext(), annonceModel).show();


                }
            });
        }
        if (user.getRole().equals("Bailleur")) {
            reservations.setVisibility(View.VISIBLE);
            reservations.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new AllReservationsFragment();
                    Bundle data = new Bundle();
                    data.putInt("annonce", annonceModel.getId());
                    fragment.setArguments(data);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment, null).addToBackStack(null);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        } else {
            reservations.setVisibility(View.INVISIBLE);
        }
    }

    private void imageChooser() {
        Intent i = new Intent();
        Bundle bundle = new Bundle();

        i.setType("image/*");
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);


        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE) {
            if (resultCode == getActivity().RESULT_OK) {
                if (data.getClipData() != null) {
                    int cout = data.getClipData().getItemCount();

                    for (int i = 0; i < cout; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        selectedImagesUri.add(imageUri);
                    }
                    position = 0;
                } else {

                    Uri imageUri = data.getData();
                    selectedImagesUri.add(imageUri);
                    position = 0;

                }

                ArrayList<String> target = new ArrayList<>();
                selectedImagesUri.forEach(uri -> target.add(uri.toString()));
                AlbumModel albumModel = new AlbumModel();
                albumModel.setAnnonceId(mParam8);
                albumModel.setImages(target);
                AppDataBase.getInstance(getContext()).albumDao().insertAlbum(albumModel);


            }
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LocalisationModel localisationModel = AppDataBase.getInstance(getContext()).localisationDao().getLocalisationByAnnonce(mParam8);
        if (localisationModel != null) {
            Double longtitudeL = localisationModel.getLongitude();
            Double LatitudeL = localisationModel.getLatitude();
            LatLng XX = new LatLng(longtitudeL, LatitudeL);
            adressLocalisation.setText(localisationModel.getAdresse());
            map.addMarker(new MarkerOptions().position(XX)).setTitle(mParam1);
            map.moveCamera(CameraUpdateFactory.newLatLng(XX));
            map.getMaxZoomLevel();

        }
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
            return dpd;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

            Button button;
            if (timeId.equals("start")) {
                button = getActivity().findViewById(R.id.pick_date);

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(year, month, dayOfMonth, 0, 0, 0);
                Date chosenDate = cal.getTime();

                // Format the date using style and locale
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
                String formattedDate = df.format(chosenDate);

                Calendar startTimeCal = Calendar.getInstance();
                startTimeCal.set(year, month, dayOfMonth);
                startTime = startTimeCal.getTime();
                // Display the chosen date to app interface
                button.setText(formattedDate);

            } else {
                button = getActivity().findViewById(R.id.end_time_button);

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(year, month, dayOfMonth, 0, 0, 0);
                Date chosenDate = cal.getTime();

                // Format the date using style and locale
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
                String formattedDate = df.format(chosenDate);
                Calendar endTimeCal = Calendar.getInstance();
                endTimeCal.set(year, month, dayOfMonth);
                endTime = endTimeCal.getTime();

                // Display the chosen date to app interface
                button.setText(formattedDate);
            }


        }
    }


}