package com.example.bloodbank.view.fragments.usercycle;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragments.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordStep1Fragment extends BaseFragment {


    public ForgetPasswordStep1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password_step1, container, false);
    }

}
