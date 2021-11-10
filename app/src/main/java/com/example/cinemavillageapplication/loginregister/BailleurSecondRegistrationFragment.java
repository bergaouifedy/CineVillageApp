package com.example.cinemavillageapplication.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinemavillageapplication.R;
import com.example.cinemavillageapplication.dao.UserDao;
import com.example.cinemavillageapplication.database.AppDataBase;
import com.example.cinemavillageapplication.models.UserModel;
import com.google.android.material.textfield.TextInputEditText;

public class BailleurSecondRegistrationFragment extends Fragment {

    TextInputEditText EmailEditText,PasswordEditText,ConfirmpasswordEditText;
    Button signup;
    private UserDao userDao;

    public BailleurSecondRegistrationFragment() {
        // Required empty public constructor
    }


    public static BailleurSecondRegistrationFragment newInstance() {
        BailleurSecondRegistrationFragment fragment = new BailleurSecondRegistrationFragment();

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
        View view =  inflater.inflate(R.layout.fragment_bailleur_second_registration, container, false);

        Bundle bundle = this.getArguments();
        String name = bundle.getString("name");
        String prename = bundle.getString("prename");
        String username = bundle.getString("username");
        String telnum = bundle.getString("telnum");
        String adress = bundle.getString("adress");

        EmailEditText = (TextInputEditText) view.findViewById(R.id.Bailleuremail);
        PasswordEditText = (TextInputEditText) view.findViewById(R.id.Bailleurpassword);
        ConfirmpasswordEditText = (TextInputEditText) view.findViewById(R.id.BailleurConfirmpassword);

        signup = (Button) view.findViewById(R.id.BailleursignupButton);

        userDao = AppDataBase.getInstance(getContext()).userDao();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailEditText.getText().toString().trim();
                String password = PasswordEditText.getText().toString().trim();
                String passwordConf = ConfirmpasswordEditText.getText().toString().trim();
                final String role = "Bailleur";
                if ((password.equals(passwordConf))
                        &&(!email.isEmpty())&&(!password.isEmpty())) {
                    UserModel user = new UserModel(
                            name,
                            prename,
                            username,
                            password,
                            true,
                            email,
                            telnum,
                            role,
                            adress
                    );
                    userDao.inserUser(user);
                    Toast.makeText(BailleurSecondRegistrationFragment.newInstance().getContext(), "Sign up succedded", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(BailleurSecondRegistrationFragment.newInstance().getContext(), LoginActivity.class);
                    startActivity(moveToLogin);
                } else {
                    Toast.makeText(BailleurSecondRegistrationFragment.newInstance().getContext(), "Password is not matching", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return  view;
    }
}