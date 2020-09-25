package com.example.bloodbank.view.fragments.homecycle;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.view.activities.MapsActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.view.fragments.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonationFormFragment extends BaseFragment {

    @BindView(R.id.donation_form_fragment_edt_txt_name)
    EditText donationFormFragmentEdtTxtName;
    @BindView(R.id.donation_form_fragment_edt_txt_age)
    EditText donationFormFragmentEdtTxtAge;
    @BindView(R.id.donation_form_fragment_spinner_blood_type)
    Spinner donationFormFragmentSpinnerBloodType;
    @BindView(R.id.donation_form_fragment_edt_txt_num_blood_bags)
    EditText donationFormFragmentEdtTxtNumBloodBags;
    @BindView(R.id.donation_form_fragment_edt_txt_hospital_location)
    EditText donationFormFragmentEdtTxtHospitalLocation;
    @BindView(R.id.donation_form_fragment_btn_hospital_location)
    ImageButton donationFormFragmentBtnHospitalLocation;
    @BindView(R.id.donation_form_fragment_spinner_governorate)
    Spinner donationFormFragmentSpinnerGovernorate;
    @BindView(R.id.donation_form_fragment_spinner_city)
    Spinner donationFormFragmentSpinnerCity;
    @BindView(R.id.donation_form_fragment_edt_txt_phone)
    EditText donationFormFragmentEdtTxtPhone;
    @BindView(R.id.donation_form_fragment_edt_txt_notes)
    EditText donationFormFragmentEdtTxtNotes;
    @BindView(R.id.donation_form_fragment_btn_send)
    Button donationFormFragmentBtnSend;

    private ApiService apiService;

    public DonationFormFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_donation_form, container, false);
        ButterKnife.bind(this, view);


        apiService = RetrofitClient.getClient().create(ApiService.class);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        String lat = MapsActivity.lat;
        String lang = MapsActivity.longtide;

        if (lat!=null&&lang!=null) {

            List<Address> addresses = new ArrayList<>();
            Geocoder  geocoder = new Geocoder(baseActivity, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lang), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            String address = addresses.get(0).getAddressLine(0);

//            String streetAdress = "";
//            for (int i = 0; i <address.length() ; i++) {
//
//
//                streetAdress += address.charAt(i);
//
//                if (Character.toString(address.charAt(i)).equals(",")){
//                    return;
//                }
//
//            }

            donationFormFragmentEdtTxtHospitalLocation.setText(address);
        }




    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.donation_form_fragment_btn_hospital_location, R.id.donation_form_fragment_btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.donation_form_fragment_btn_hospital_location:
                startActivity(new Intent(baseActivity, MapsActivity.class));
                break;
            case R.id.donation_form_fragment_btn_send:
                break;
        }
    }
}
