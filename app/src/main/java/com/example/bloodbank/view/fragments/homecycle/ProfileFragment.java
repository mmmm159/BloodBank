package com.example.bloodbank.view.fragments.homecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.ath.Auth;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.cities.CityData;
import com.example.bloodbank.utilities.HelperMethods;
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

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.fragment_profile_edt_txt_name)
    EditText fragmentProfileEdtTxtName;
    @BindView(R.id.fragment_profile_edt_txt_email)
    EditText fragmentProfileEdtTxtEmail;
    @BindView(R.id.fragment_profile_txt_view_birth_date)
    TextView fragmentProfiletxtViewBirthDate;
    @BindView(R.id.fragment_profile_spinner_blood_type)
    Spinner fragmentProfileSpinnerBloodType;
    @BindView(R.id.fragment_profile_txt_view_last_donation_date)
    TextView fragmentProfiletxtViewLastDonationDate;
    @BindView(R.id.fragment_profile_spinner_governorate)
    Spinner fragmentProfileSpinnerGovernorate;
    @BindView(R.id.fragment_profile_spinner_city)
    Spinner fragmentProfileSpinnerCity;
    @BindView(R.id.fragment_profile_edt_txt_phone)
    EditText fragmentProfileEdtTxtPhone;
    @BindView(R.id.fragment_profile_edt_txt_password)
    EditText fragmentProfileEdtTxtPassword;
    @BindView(R.id.fragment_profile_edt_txt_confirm_password)
    EditText fragmentProfileEdtTxtConfirmPassword;
    @BindView(R.id.fragment_profile_btn_modify)
    Button fragmentProfileBtnModify;

    private String bloodTypeId;
    private String governorateId;
    private String cityId;

    private ApiService apiService;
    private List<Integer> citiesIdsList;




    public ProfileFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        String name = SharedPreference.loadString(baseActivity, SharedPreference.NAME);
        String email = SharedPreference.loadString(baseActivity, SharedPreference.EMAIL);
        String birthDate = SharedPreference.loadString(baseActivity, SharedPreference.BIRTH_DATE);
        bloodTypeId = SharedPreference.loadString(baseActivity, SharedPreference.BLOOD_TYPE_ID);
        String lastDonation = SharedPreference.loadString(baseActivity, SharedPreference.LAST_DONATION_DATE);
        governorateId = SharedPreference.loadString(baseActivity, SharedPreference.GOV_ID);
        cityId = SharedPreference.loadString(baseActivity, SharedPreference.CITY_ID);
        String phone = SharedPreference.loadString(baseActivity, SharedPreference.PHONE);
        String password = SharedPreference.loadString(baseActivity, SharedPreference.PASSWORD);
        String passwordConfirmation = SharedPreference.loadString(baseActivity, SharedPreference.PASSWORD_CONFIRM);




            apiService.getBloodTypes().enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {



                        if (response.body().getStatus()==1) {

                            List<CityData> bloodTypeDataList = response.body().getData();
                            List<String> bloodTpesNames = new ArrayList<>();


                            //if (baseActivity!=null)
                            bloodTpesNames.add(baseActivity.getString(R.string.register_fragment_spinner_blood_type_Default));

                            for (int i = 0; i <bloodTypeDataList.size() ; i++) {

                                CityData bloodTypeData = bloodTypeDataList.get(i);
                                bloodTpesNames.add(bloodTypeData.getName());
                            }
                            ArrayAdapter bloodSpinnerAdapter = new ArrayAdapter(baseActivity,R.layout.spinner_color_layout
                                    ,bloodTpesNames);

                            bloodSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

                            fragmentProfileSpinnerBloodType.setAdapter(bloodSpinnerAdapter);
                            fragmentProfileSpinnerBloodType.setSelection(Integer.parseInt(bloodTypeId));
                        }



//                    catch (Exception e){
//
//                        Toast.makeText(baseActivity,e.getMessage(), Toast.LENGTH_LONG).show();
//                        Log.d("profileFragment",  e.getMessage());
//                    }

                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {

                }
            });

            apiService.getGovernorates().enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {
                    if (response.body().getStatus()==1) {

                        List<CityData> governorateDataList = response.body().getData();
                        List<String> govNamesList = new ArrayList<>();

                       // if (baseActivity!=null)
                        govNamesList.add(baseActivity.getString(R.string.register_fragment_spinner_gov_Default));

                        for (int i = 0; i <governorateDataList.size() ; i++) {

                            CityData governorateData = governorateDataList.get(i);
                            govNamesList.add(governorateData.getName());
                        }

                        ArrayAdapter governorateAdapter = new ArrayAdapter(baseActivity,R.layout.spinner_color_layout
                                ,govNamesList);

                        governorateAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

                        fragmentProfileSpinnerGovernorate.setAdapter(governorateAdapter);
                        fragmentProfileSpinnerGovernorate.setSelection(Integer.parseInt(governorateId));

                        fragmentProfileSpinnerGovernorate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                                if (position!=0) {

                                    apiService.getCities(position).enqueue(new Callback<City>() {
                                        @Override
                                        public void onResponse(Call<City> call, Response<City> response) {

                                            List<CityData> cityDataList = response.body().getData();
                                            List<String> cityNamesList = new ArrayList<>();

                                            //if (baseActivity!=null)
                                                cityNamesList.add(baseActivity.getString(R.string.register_fragment_spinner_city_Default));



                                            citiesIdsList = new ArrayList<>();
                                            citiesIdsList.add(-1);
                                            for (int i = 0; i <cityDataList.size() ; i++) {

                                                CityData cityData = cityDataList.get(i);
                                                cityNamesList.add(cityData.getName());
                                                citiesIdsList.add(cityData.getId());
                                            }

                                            ArrayAdapter citiesAdapter = new ArrayAdapter(baseActivity,R.layout.spinner_color_layout
                                                    ,cityNamesList);

                                            citiesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                                            fragmentProfileSpinnerCity.setAdapter(citiesAdapter);

                                            for (int i = 0; i <citiesIdsList.size() ; i++) {

                                                if (Integer.parseInt(cityId)== citiesIdsList.get(i)){

                                                    fragmentProfileSpinnerCity.setSelection(i);
                                                }
                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<City> call, Throwable t) {

                                        }
                                    });
                                }


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {

                }
            });







        fragmentProfileEdtTxtName.setText(name);
        fragmentProfileEdtTxtEmail.setText(email);
        fragmentProfiletxtViewBirthDate.setText(birthDate);
        fragmentProfiletxtViewLastDonationDate.setText(lastDonation);
        fragmentProfileEdtTxtPhone.setText(phone);
        fragmentProfileEdtTxtPassword.setText(password);
        fragmentProfileEdtTxtConfirmPassword.setText(passwordConfirmation);





        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_profile_btn_modify)
    public void onViewClicked() {
    }

    @OnClick({R.id.fragment_profile_txt_view_birth_date,
            R.id.fragment_profile_txt_view_last_donation_date
    ,R.id.fragment_profile_btn_modify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_profile_txt_view_birth_date:

                HelperMethods.setDatePicker(fragmentProfiletxtViewBirthDate,baseActivity);

                break;
            case R.id.fragment_profile_txt_view_last_donation_date:
                HelperMethods.setDatePicker(fragmentProfiletxtViewLastDonationDate,baseActivity);
                break;
            case R.id.fragment_profile_btn_modify:

                String name = fragmentProfileEdtTxtName.getText().toString();
                String email = fragmentProfileEdtTxtEmail.getText().toString();
                String birthDate = fragmentProfiletxtViewBirthDate.getText().toString();
                String lastDonationDate = fragmentProfiletxtViewLastDonationDate.getText().toString();
                String phone = fragmentProfileEdtTxtPhone.getText().toString();
                String password = fragmentProfileEdtTxtPassword.getText().toString();
                String confirmPassword = fragmentProfileEdtTxtConfirmPassword.getText().toString();
                String apiToken = SharedPreference.loadString(baseActivity,SharedPreference.API_TOKEN);

                String bloodTypeId = String.valueOf(fragmentProfileSpinnerBloodType.getSelectedItemPosition());
                String governorateId = String.valueOf(fragmentProfileSpinnerGovernorate.getSelectedItemPosition());
                String cityId = String.valueOf(citiesIdsList.get(fragmentProfileSpinnerCity.getSelectedItemPosition()));

                apiService.modify(name,email,birthDate,cityId,phone,lastDonationDate
                ,password,confirmPassword,bloodTypeId,apiToken).enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        if (response.body().getStatus()==1) {

                            SharedPreference.saveData(baseActivity, SharedPreference.API_TOKEN, apiToken);
                            SharedPreference.saveData(baseActivity, SharedPreference.NAME, name);
                            SharedPreference.saveData(baseActivity, SharedPreference.EMAIL, email);
                            SharedPreference.saveData(baseActivity, SharedPreference.BIRTH_DATE, birthDate);
                            SharedPreference.saveData(baseActivity, SharedPreference.CITY_ID, cityId);
                            SharedPreference.saveData(baseActivity, SharedPreference.PHONE, phone);
                            SharedPreference.saveData(baseActivity, SharedPreference.LAST_DONATION_DATE, lastDonationDate);
                            SharedPreference.saveData(baseActivity, SharedPreference.BLOOD_TYPE_ID, bloodTypeId);
                            SharedPreference.saveData(baseActivity, SharedPreference.PASSWORD, password);
                            SharedPreference.saveData(baseActivity, SharedPreference.PASSWORD_CONFIRM, password);
                            SharedPreference.saveData(baseActivity, SharedPreference.GOV_ID, governorateId);


                            Toast.makeText(baseActivity,response.body().getMsg(), Toast.LENGTH_SHORT).show();


                        }

                        if (response.body().getStatus()==0) {

                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {

                    }
                });

                break;
        }
    }
}
