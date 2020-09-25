package com.example.bloodbank.view.fragments.homecycle;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.DonationsAdapter;
import com.example.bloodbank.adapters.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.cities.CityData;
import com.example.bloodbank.data.model.donation.Donation;
import com.example.bloodbank.data.model.donation.DonationData;
import com.example.bloodbank.utilities.OnEndLess;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationRequestHomeFragment extends BaseFragment {


    @BindView(R.id.donation_requests_home_fragment_spinner_blood_type)
    Spinner donationRequestsHomeFragmentSpinnerBloodType;
    @BindView(R.id.donation_requests_home_fragment_spinner_governorate)
    Spinner donationRequestsHomeFragmentSpinnerGovernorate;
    @BindView(R.id.donation_requests_home_fragment_recycle_requests)
    RecyclerView donationRequestsHomeFragmentRecycleRequests;
    @BindView(R.id.donation_requests_home_fragment_img_btn_search)
    ImageButton donationRequestsHomeFragmentImgBtnSearch;

    private ApiService apiService;
    private List<DonationData> donationDataList;
    private OnEndLess onEndLess;
    private LinearLayoutManager linearLayoutManager;
    private DonationsAdapter donationsAdapter;
    private List<CityData> bloodTypeDataList;
    private List<CityData> governorateNames;
    private Integer lastpage;

    public DonationRequestHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_donation_request_home, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        getBloodTypes();
        setDonationRecycler();
        return view;

    }

    private void getBloodTypes() {

        apiService.getBloodTypes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

                try {

                    if (response.body().getStatus()==1) {

                        bloodTypeDataList = response.body().getData();
                        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(baseActivity,bloodTypeDataList);
                        donationRequestsHomeFragmentSpinnerBloodType.setAdapter(spinnerAdapter);
                    }

                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });



    }

    private void setDonationRecycler() {


        donationDataList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(baseActivity);
        donationRequestsHomeFragmentRecycleRequests.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

//                if (current_page <= lastPage) {
//
//                    if (lastPage != 0 && current_page != 1) {
//
//                        onEndLess.previous_page = current_page;
                getPage(current_page);
//
//                    } else {
//                        onEndLess.current_page = onEndLess.previous_page;
//                    }
//
//                } else {
//
//                    onEndLess.current_page = onEndLess.previous_page;
//                }
//
            }
        };

        donationRequestsHomeFragmentRecycleRequests.addOnScrollListener(onEndLess);

        donationsAdapter = new DonationsAdapter(baseActivity, donationDataList);
        donationRequestsHomeFragmentRecycleRequests.setAdapter(donationsAdapter);

        getPage(1);

    }

    private void getPage(int current_page) {

        String apiToken = SharedPreference.loadString(baseActivity, SharedPreference.API_TOKEN);
        apiService.getAllDonation(apiToken, current_page).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {

                if (response.body().getStatus() == 1) {


                    //lastpage = response.body().getData().getLastPage();
                    donationDataList.addAll(response.body().getData().getData());
                    donationsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.donation_requests_home_fragment_img_btn_search)
    public void onViewClicked() {
    }
}
