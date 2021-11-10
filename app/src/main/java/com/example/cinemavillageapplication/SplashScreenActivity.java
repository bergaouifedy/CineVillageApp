package com.example.cinemavillageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemavillageapplication.loginregister.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    //Initialiser les variables

    ImageView ivTop , ivBot , logo;
    TextView slogon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Assign variable

        ivTop = findViewById(R.id.iv_top);
        logo = findViewById(R.id.logo);
        ivBot = findViewById(R.id.iv_bottom);
        slogon = findViewById(R.id.name_logo);

        //set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize top animation
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.top_wave);

        //start top animation
        ivTop.setAnimation(animation1);

        //initialize object animation
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                logo,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );

        //set duration
        objectAnimator.setDuration(500);


        //set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //set repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //start animation
        objectAnimator.start();


        //initialize bottom animation
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.bot_wave);

        //start bottom animation
        ivBot.setAnimation(animation2);

        //Initialize handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Redirect to LOGIN activity
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo,"logo_image");
                pairs[1] = new Pair<View,String>(slogon,"logo_name");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this,pairs);


                startActivity(intent,options.toBundle());
                finish();
            }
        },4000);

    }
}