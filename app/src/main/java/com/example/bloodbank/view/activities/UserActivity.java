package com.example.bloodbank.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.view.fragments.usercycle.ForgetPasswordStep1Fragment;
import com.example.bloodbank.view.fragments.usercycle.ForgetPasswordStep2Fragment;
import com.example.bloodbank.view.fragments.usercycle.LoginFragment;
import com.example.bloodbank.view.fragments.usercycle.RegisterFragment;

public class UserActivity extends BaseActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    ForgetPasswordStep1Fragment forgetPasswordStep1Fragment;
    ForgetPasswordStep2Fragment forgetPasswordStep2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        forgetPasswordStep1Fragment = new ForgetPasswordStep1Fragment();
        forgetPasswordStep2Fragment = new ForgetPasswordStep2Fragment();
        HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.user_activity,loginFragment);


    }
}
