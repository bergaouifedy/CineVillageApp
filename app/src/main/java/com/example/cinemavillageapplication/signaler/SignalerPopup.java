package com.example.cinemavillageapplication.signaler;


import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cinemavillageapplication.MainActivity2;
import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.annonces.DetailsFragment;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.AnnonceModel;
import com.example.cinemavillageapplication.models.SignalsModel;
import com.example.cinemavillageapplication.models.UserModel;


public class SignalerPopup extends Dialog {

    AnnonceModel annonceModel;
    UserModel user;

    RadioGroup typeSignalementGroupe;
    RadioButton type1 , type2 , type3 , type4;

    Button Signaler;
    EditText message;
    Context context;

    Intent intent;


    FragmentManager activity;

    TextView annonceTitle;
    public SignalerPopup(@NonNull FragmentManager activity, Context context, UserModel user, AnnonceModel annonceModel) {
        super(context);
        this.annonceModel =  annonceModel;
        this.user = user;
        this.context = context;
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_signaler);

        annonceTitle = findViewById(R.id.AnnonceTitle);

        String title = annonceModel.getTitle();
        annonceTitle.setText(title);

        typeSignalementGroupe = findViewById(R.id.TypeSignalement);

        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        type3 = findViewById(R.id.type3);
        type4 = findViewById(R.id.type4);

        type1.setChecked(true);

        Signaler = findViewById(R.id.btnSignal);

        message = findViewById(R.id.messageSignal);

        typeSignalementGroupe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doOnTypeChanged(radioGroup,i);
            }
        });

        Signaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignaler();
            }
        });

    }

    private void doSignaler() {
        int IdtypeSignalement = this.typeSignalementGroupe.getCheckedRadioButtonId();
        RadioButton radioType = this.findViewById(IdtypeSignalement);

        String type = radioType.getText().toString();
        int annonceId = annonceModel.getId();
        int userId = user.getId();



        String messageSignal = message.getText().toString();

        SignalsModel signalsModel = new SignalsModel(type, messageSignal, annonceId,userId);

        if(AppDataBase.getInstance(context).signalsDao().getSignalByUserAndAnnonce(userId,annonceId)==null)
        {
            if (signalsModel != null) {
                AppDataBase.getInstance(context).signalsDao().insertSignal(signalsModel);
                Toast.makeText(context, "Signal envoyé", Toast.LENGTH_LONG).show();
                dismiss();

                if(Integer.parseInt(AppDataBase.getInstance(context).signalsDao().getRowCount(annonceId))>3)
                {
                    UserModel userSignaler= AppDataBase.getInstance(context).userDao().getUserByUsername(annonceModel.getUsername());
                    userSignaler.setVerified(false);
                    AppDataBase.getInstance(context).userDao().updateUser(userSignaler);
                }

                /*Fragment fragment = new DetailsFragment();
                FragmentTransaction transaction = activity.beginTransaction();
                if (user.getRole().equals("Realisateur")) {
                    transaction.replace(R.id.fragment_container2, fragment, null);
                    transaction.commit();
                } else {
                    transaction.replace(R.id.fragment_container, fragment, null);
                    transaction.commit();
                }
*/

            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                dismiss();

            }

        }else{
            Toast.makeText(context, "Vous avez déjà signaler cette annonce", Toast.LENGTH_LONG).show();
 /*           SignalsModel signalsModel1 = AppDataBase.getInstance(context).signalsDao().getSignalByUserAndAnnonce(userId,annonceId);
            int ann = signalsModel1.getAnnonceId();
            String anno = AppDataBase.getInstance(context).annonceDao().getTitleAnnonce(ann);
            Fragment fragment = new DetailsFragment();
                    FragmentTransaction transaction = activity.beginTransaction();*/
            dismiss();
         /*   if (user.getRole().equals("Realisateur")) {
                transaction.replace(R.id.fragment_container2, fragment, null);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                transaction.replace(R.id.fragment_container, fragment, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }*/
        }


    }

    private void doOnTypeChanged(RadioGroup radioGroup, int i) {

        int checkedId = radioGroup.getCheckedRadioButtonId();

        if(checkedId == R.id.type1)
        {
            Toast.makeText(context,"tu as choisi le type1",Toast.LENGTH_SHORT).show();


        } else if(checkedId == R.id.type2)
        {
            Toast.makeText(context,"tu as choisi le type2",Toast.LENGTH_SHORT).show();

        } else if(checkedId == R.id.type3)
        {
            Toast.makeText(context,"tu as choisi le type3",Toast.LENGTH_SHORT).show();

        } else if(checkedId == R.id.type4)
        {
            Toast.makeText(context,"tu as choisi le type4",Toast.LENGTH_SHORT).show();

        }
    }

}
