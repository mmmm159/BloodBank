package com.example.bloodbank.view.fragments.homecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.NotificationSettingAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.cities.CityData;
import com.example.bloodbank.data.model.notificationsettings.NotificationSettings;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationSettingMoreFragment extends BaseFragment {

    @BindView(R.id.fragment_notification_setting_more_btn_blood_types)
    Button fragmentNotificationSettingMoreBtnBloodTypes;
    @BindView(R.id.fragment_notification_setting_more_recycler_blood_types)
    RecyclerView fragmentNotificationSettingMoreRecyclerBloodTypes;
    @BindView(R.id.fragment_notification_setting_more_btn_governorates)
    Button fragmentNotificationSettingMoreBtnGovernorates;
    @BindView(R.id.fragment_notification_setting_more_recycler_governorates)
    RecyclerView fragmentNotificationSettingMoreRecyclerGovernorates;
    @BindView(R.id.fragment_notification_setting_more_btn_save)
    Button fragmentNotificationSettingMoreBtnSave;

    private ApiService apiService;
    private List<CityData> bloodTypeDataList;
    private List<CityData> governoratesDataList;
    private List<String> bloodTypesIds;
    private List<String> governorateIds;
    private NotificationSettingAdapter notificationSettingBloodAdapter;
    private NotificationSettingAdapter notificationSettingGovAdapter;

    public NotificationSettingMoreFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_more_notification_setting, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient().create(ApiService.class);

        getNotificationSetting();

        return view;
    }

    private void getNotificationSetting() {

        String apiToken = SharedPreference.loadString(baseActivity,SharedPreference.API_TOKEN);

        apiService.getNotificationSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationSettings>() {
            @Override
            public void onResponse(Call<NotificationSettings> call, Response<NotificationSettings> response) {
                try {
                    if (response.body().getStatus()==1) {

                         bloodTypesIds = response.body().getData().getBloodTypes();
                         governorateIds = response.body().getData().getGovernorates();

                        getBloodTypes();
                        getGovernorates();

                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<NotificationSettings> call, Throwable t) {

            }
        });
    }

    private void setUpBloodRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(baseActivity,3);
        fragmentNotificationSettingMoreRecyclerBloodTypes.setLayoutManager(gridLayoutManager);

         notificationSettingBloodAdapter = new NotificationSettingAdapter(baseActivity,bloodTypeDataList,bloodTypesIds);
        fragmentNotificationSettingMoreRecyclerBloodTypes.setAdapter(notificationSettingBloodAdapter);
    }

    private void setUpGovernorateRecycler() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(baseActivity,3);
        fragmentNotificationSettingMoreRecyclerGovernorates.setLayoutManager(gridLayoutManager);

         notificationSettingGovAdapter = new NotificationSettingAdapter(baseActivity,governoratesDataList,governorateIds);
        fragmentNotificationSettingMoreRecyclerGovernorates.setAdapter(notificationSettingGovAdapter);
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.fragment_notification_setting_more_btn_blood_types, R.id.fragment_notification_setting_more_btn_governorates, R.id.fragment_notification_setting_more_btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_notification_setting_more_btn_blood_types:

                showRecyclerButton(fragmentNotificationSettingMoreRecyclerBloodTypes,
                        fragmentNotificationSettingMoreBtnBloodTypes);
                break;
            case R.id.fragment_notification_setting_more_btn_governorates:

                showRecyclerButton(fragmentNotificationSettingMoreRecyclerGovernorates
                ,fragmentNotificationSettingMoreBtnGovernorates);
                break;
            case R.id.fragment_notification_setting_more_btn_save:
                
                changeNotificationSettings();


                break;
        }
    }

    private void changeNotificationSettings() {

        String apiToken = SharedPreference.loadString(baseActivity,SharedPreference.API_TOKEN);
        List<Integer> newBloodTypes = notificationSettingBloodAdapter.newIds;
        List<Integer> newgovernorates = notificationSettingGovAdapter.newIds;

        apiService.changeNotificationSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",newgovernorates,newBloodTypes).enqueue(new Callback<NotificationSettings>() {
            @Override
            public void onResponse(Call<NotificationSettings> call, Response<NotificationSettings> response) {
                try {

                    if (response.body().getStatus()==1) {
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<NotificationSettings> call, Throwable t) {

            }
        });
    }

    private void showRecyclerButton(RecyclerView recyclerView, Button button) {

        if (recyclerView.getVisibility()== View.VISIBLE) {

            int imgResource = R.drawable.ic_add_white_24dp;
            button.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            int imgResource = R.drawable.ic_remove_white_24dp;
            button.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void getBloodTypes(){

        apiService.getBloodTypes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                try {
                    if (response.body().getStatus()==1) {
                        bloodTypeDataList = response.body().getData();


                        setUpBloodRecycler();
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

    private void getGovernorates(){

        apiService.getGovernorates().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                try {

                    if (response.body().getStatus()==1) {

                        governoratesDataList = response.body().getData();


                        setUpGovernorateRecycler();
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


}
