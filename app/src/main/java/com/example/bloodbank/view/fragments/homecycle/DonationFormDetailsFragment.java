package com.example.bloodbank.view.fragments.homecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.DonationsAdapter;
import com.example.bloodbank.view.fragments.BaseFragment;
import com.google.android.gms.maps.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonationFormDetailsFragment extends BaseFragment {

    @BindView(R.id.donation_form_details_fragment_txt_view_name_top)
    TextView donationFormDetailsFragmentTxtViewNameTop;
    @BindView(R.id.donation_form_details_fragment_txt_view_name)
    TextView donationFormDetailsFragmentTxtViewName;
    @BindView(R.id.donation_form_details_fragment_txt_view_age)
    TextView donationFormDetailsFragmentTxtViewAge;
    @BindView(R.id.donation_form_details_fragment_txt_view_blood_type)
    TextView donationFormDetailsFragmentTxtViewBloodType;
    @BindView(R.id.donation_form_details_fragment_txt_view_number_needed_bags)
    TextView donationFormDetailsFragmentTxtViewNumberNeededBags;
    @BindView(R.id.donation_form_details_fragment_txt_view_hospital)
    TextView donationFormDetailsFragmentTxtViewHospital;
    @BindView(R.id.donation_form_details_fragment_txt_view_address)
    TextView donationFormDetailsFragmentTxtViewAddress;
    @BindView(R.id.donation_form_details_fragment_txt_view_phone)
    TextView donationFormDetailsFragmentTxtViewPhone;
    @BindView(R.id.donation_form_details_fragment_txt_view_dummy)
    TextView donationFormDetailsFragmentTxtViewDummy;
    @BindView(R.id.donation_form_details_fragment_map_view)
    MapView donationFormDetailsFragmentMapView;
    @BindView(R.id.donation_form_details_fragment_btn_call)
    Button donationFormDetailsFragmentBtnCall;

    public DonationFormDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_donation_form_details, container, false);
        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.donation_form_details_fragment_map_view, R.id.donation_form_details_fragment_btn_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.donation_form_details_fragment_map_view:
                break;
            case R.id.donation_form_details_fragment_btn_call:
                break;
        }
    }
}
