package com.example.bloodbank.view.fragments.usercycle;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.ath.Auth;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.cities.CityData;

import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.activities.HomeActivity;
import com.example.bloodbank.view.activities.UserActivity;
import com.example.bloodbank.view.fragments.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {


    @BindView(R.id.register_fragment_edt_txt_name)
    EditText registerFragmentEdtTxtName;
    @BindView(R.id.register_fragment_edt_txt_email)
    EditText registerFragmentEdtTxtEmail;
    @BindView(R.id.register_fragment_txt_view_birth_date)
    TextView registerFragmentTxtViewBirthDate;
    @BindView(R.id.register_fragment_spinner_blood_type)
    Spinner registerFragmentSpinnerBloodType;
    @BindView(R.id.register_fragment_txt_view_last_donation)
    TextView registerFragmentTxtViewLastDonation;
    @BindView(R.id.register_fragment_spinner_governorate)
    Spinner registerFragmentSpinnerGovernorate;
    @BindView(R.id.register_fragment_spinner_city)
    Spinner registerFragmentSpinnerCity;
    @BindView(R.id.register_fragment_edt_txt_phone)
    EditText registerFragmentEdtTxtPhone;
    @BindView(R.id.register_fragment_edt_txt_password)
    EditText registerFragmentEdtTxtPassword;
    @BindView(R.id.register_fragment_edt_txt_password_confirm)
    EditText registerFragmentEdtTxtPasswordConfirm;
    @BindView(R.id.register_fragment_btn_register)
    Button registerFragmentBtnRegister;

    private ApiService apiService;
    private String cityId;
    private String bloodTypeId;
    private String name;
    private String email;
    private String birthData;
    private String phone;
    private String donationLastDate;
    private String password;
    private String passwordConfirmation;
    private String governorateId;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initFragment();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        apiService = getClient().create(ApiService.class);

        apiService.getBloodTypes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

                if (response.body().getStatus() == 1) {

                    City bloodResponse = response.body();
                    List<CityData> bloodTypes = bloodResponse.getData();
                    List<String> bloodTypesName = new ArrayList<>();
                    bloodTypesName.add(getString(R.string.register_fragment_spinner_blood_type_Default));

                    if (bloodTypes != null) {

                        for (int i = 0; i < bloodTypes.size(); i++) {

                            CityData bloodType = bloodTypes.get(i);

                            bloodTypesName.add(bloodType.getName());
                        }
                    }

                    ArrayAdapter<String> bloodTypesAdapter = new ArrayAdapter(getActivity(),
                            R.layout.spinner_color_layout, bloodTypesName);

                    bloodTypesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                    registerFragmentSpinnerBloodType.setAdapter(bloodTypesAdapter);



                }

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });

        apiService.getGovernorates().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {


                if (response.body().getStatus() == 1) {


                    List<CityData> governoratesData;
                    List<String> governorates = new ArrayList<>();

                    governorates.add(getString(R.string.register_fragment_spinner_gov_Default));

                    City city = response.body();

                    governoratesData = city.getData();

                    for (int i = 0; i < governoratesData.size(); i++) {

                        CityData cityData = governoratesData.get(i);
                        governorates.add(cityData.getName());
                    }

                    ArrayAdapter<String> governateAdapter = new ArrayAdapter(getActivity(),
                            R.layout.spinner_color_layout, governorates);

                    governateAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                    registerFragmentSpinnerGovernorate.setAdapter(governateAdapter);

                    registerFragmentSpinnerGovernorate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                            if (position != 0) {
                                apiService.getCities(position).enqueue(new Callback<City>() {
                                    @Override
                                    public void onResponse(Call<City> call, Response<City> response) {
                                        if (response.body().getStatus() == 1) {
                                            List<CityData> citiesData = response.body().getData();
                                            List<String> cityNames = new ArrayList<>();
                                            cityNames.add(getString(R.string.register_fragment_spinner_city_Default));
                                            List<Integer> cityIds = new ArrayList<>();
                                            cityIds.add(-1);

                                            for (int i = 0; i < citiesData.size(); i++) {
                                                CityData cityData = citiesData.get(i);
                                                cityIds.add(cityData.getId());
                                                cityNames.add(cityData.getName());
                                            }
                                            ArrayAdapter<String> cityAdapter = new ArrayAdapter(getActivity(),
                                                    R.layout.spinner_color_layout, cityNames);

                                            cityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                                            registerFragmentSpinnerCity.setAdapter(cityAdapter);

                                            registerFragmentSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                    if (position != 0)
                                                        cityId = String.valueOf(cityIds.get(position));


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

                Toast.makeText(baseActivity, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        return view;

    }


    @Override
    public void onBack() {
        HelperMethods.replaceFragment(getFragmentManager(),R.id.user_activity
                ,new LoginFragment());
    }


    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @OnClick({R.id.register_fragment_txt_view_birth_date, R.id.register_fragment_txt_view_last_donation, R.id.register_fragment_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_fragment_txt_view_birth_date:

               // setUpDatePicker(registerFragmentTxtViewBirthDate);
                HelperMethods.setDatePicker(registerFragmentTxtViewBirthDate,baseActivity);

                break;
            case R.id.register_fragment_txt_view_last_donation:

               // setUpDatePicker(registerFragmentTxtViewLastDonation);
                HelperMethods.setDatePicker(registerFragmentTxtViewLastDonation,baseActivity);
                break;
            case R.id.register_fragment_btn_register:

                name = registerFragmentEdtTxtName.getText().toString();
                email = registerFragmentEdtTxtEmail.getText().toString();
                birthData = registerFragmentTxtViewBirthDate.getText().toString();


                if (registerFragmentSpinnerBloodType.getSelectedItemPosition() != 0) {

                    bloodTypeId = String.valueOf(registerFragmentSpinnerBloodType.getSelectedItemPosition());
                }

                if (registerFragmentSpinnerGovernorate.getSelectedItemPosition() != 0) {

                    governorateId = String.valueOf
                            (registerFragmentSpinnerGovernorate.getSelectedItemPosition());
                }

                phone = registerFragmentEdtTxtPhone.getText().toString();
                donationLastDate = registerFragmentTxtViewLastDonation.getText().toString();
                password = registerFragmentEdtTxtPassword.getText().toString();
                passwordConfirmation = registerFragmentEdtTxtPasswordConfirm.getText().toString();

                apiService.register(name, email, birthData, cityId, phone, donationLastDate,
                        password, passwordConfirmation, bloodTypeId).enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {

                        if (response.body().getStatus() == 1) {

                            String apiToken = response.body().getData().getApiToken();


                            SharedPreference.saveData(baseActivity,SharedPreference.API_TOKEN,apiToken);
                            SharedPreference.saveData(baseActivity,SharedPreference.NAME,name);
                            SharedPreference.saveData(baseActivity,SharedPreference.EMAIL,email);
                            SharedPreference.saveData(baseActivity,SharedPreference.BIRTH_DATE,birthData);
                            SharedPreference.saveData(baseActivity,SharedPreference.CITY_ID,cityId);
                            SharedPreference.saveData(baseActivity,SharedPreference.PHONE,phone);
                            SharedPreference.saveData(baseActivity,SharedPreference.LAST_DONATION_DATE,donationLastDate);
                            SharedPreference.saveData(baseActivity,SharedPreference.BLOOD_TYPE_ID,bloodTypeId);
                            SharedPreference.saveData(baseActivity,SharedPreference.PASSWORD,password);
                            SharedPreference.saveData(baseActivity,SharedPreference.PASSWORD_CONFIRM,passwordConfirmation);
                            SharedPreference.saveData(baseActivity,SharedPreference.GOV_ID,governorateId);

                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(baseActivity, HomeActivity.class));

                        }

                        if (response.body().getStatus() == 0) {

                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {

                    }
                });

                break;
        }
    }

//    private void setUpDatePicker(View view) {
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                Calendar calendar = Calendar.getInstance();
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                int month = calendar.get(Calendar.DAY_OF_MONTH);
//                int year = calendar.get(Calendar.YEAR);
//
//                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//
//                        month++;
//                        String m = String.valueOf(month);
//                        String d = String.valueOf(day);
//
//                        if (month<=9){
//                           m = "0" + month;
//                        }
//                        if (day<=9){
//                            d = "0" + day;
//                        }
//
//                        ((TextView) view).setText(" " + year + "-" + m + "-" + d);
//
//                    }
//                };
//
//                DatePickerDialog dialog = new DatePickerDialog(baseActivity
//                        , android.R.style.Theme_Holo_Dialog_MinWidth
//                        , onDateSetListener
//                        , year, month, day);
//
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//            }
//        });
//
//    }
}
