package com.example.bloodbank.view.fragments;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.view.activities.BaseActivity;

public class BaseFragment extends Fragment {

public BaseActivity baseActivity;

public void initFragment(){
    baseActivity = (BaseActivity) getActivity();
    baseActivity.baseFragment = this;
}

public void onBack(){

    baseActivity.superOnBackPressed();
}
}
