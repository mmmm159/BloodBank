package com.example.bloodbank.view.fragments.homecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragments.BaseFragment;

public class NotificationHomeFragment extends BaseFragment {

    public NotificationHomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view =  inflater.inflate(R.layout.fragment_home_notifications, container, false);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
