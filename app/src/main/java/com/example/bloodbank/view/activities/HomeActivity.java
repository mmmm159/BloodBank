package com.example.bloodbank.view.activities;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.view.fragments.homecycle.DonationFormDetailsFragment;
import com.example.bloodbank.view.fragments.homecycle.HomeFragment;
import com.example.bloodbank.view.fragments.homecycle.MoreFragment;
import com.example.bloodbank.view.fragments.homecycle.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_activity_navigation_home:

                    HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.activity_home_frame
                            ,new HomeFragment());



                    return true;
                case R.id.home_activity_navigation_modify_data:


                    HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.activity_home_frame
                            ,new ProfileFragment());

                    return true;
                case R.id.home_activity_navigation_notifications:

                    HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.activity_home_frame
                    ,new DonationFormDetailsFragment());

                    return true;
                case R.id.home_activity_navigation_more:

                    HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.activity_home_frame
                            ,new MoreFragment());


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.activity_home_nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.activity_home_frame
                ,new HomeFragment());

    }

}
