package com.example.bloodbank.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bloodbank.R;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.activities.BaseActivity;
import com.example.bloodbank.view.fragments.splashcycle.SliderFragment;
import com.example.bloodbank.view.fragments.splashcycle.SplashFragment;
import com.example.bloodbank.view.fragments.usercycle.LoginFragment;

public class SplashActivity extends BaseActivity {


    SplashFragment splashFragment;
    SliderFragment sliderFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashFragment = new SplashFragment();
        sliderFragment = new SliderFragment();


        HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.splash_activity,splashFragment);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               String apiToken =  SharedPreference.loadString(SplashActivity.this,SharedPreference.API_TOKEN);
               if (apiToken==null)
                   HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.splash_activity,sliderFragment);
                else{

                    Boolean remeberMeChkStatus = SharedPreference.loadBoolean(SplashActivity.this,
                            SharedPreference.LOGIN_REMEBER_ME_CHECK_BOX);

                    if (remeberMeChkStatus){
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                    }
                    else {

                        startActivity(new Intent(SplashActivity.this,UserActivity.class));
                    }

               }
            }
        },2000);
    }
}
