package com.example.bloodbank.view.fragments.usercycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.ath.Auth;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.activities.HomeActivity;
import com.example.bloodbank.view.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {


    @BindView(R.id.login_fragment_edt_txt_phone)
    EditText loginFragmentEdtTxtPhone;
    @BindView(R.id.login_fragment_edt_txt_password)
    EditText loginFragmentEdtTxtPassword;
    @BindView(R.id.login_fragment_check_box_remember_me)
    CheckBox loginFragmentCheckBoxRememberMe;
    @BindView(R.id.login_fragment_txt_did_you_forget)
    TextView loginFragmentTxtDidYouForget;
    @BindView(R.id.login_fragment_btn_enter_background)
    Button loginFragmentBtnEnterBackground;
    @BindView(R.id.login_fragment_txt_view_dont_have_acc)
    TextView loginFragmentTxtViewDontHaveAcc;

    private ApiService apiService;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        return view;
    }


    @OnClick({R.id.login_fragment_txt_did_you_forget, R.id.login_fragment_btn_enter_background, R.id.login_fragment_txt_view_dont_have_acc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_txt_did_you_forget:
                break;
            case R.id.login_fragment_btn_enter_background:

                String phone = loginFragmentEdtTxtPhone.getText().toString();
                String password = loginFragmentEdtTxtPassword.getText().toString();

                Boolean checkBoxStatus = loginFragmentCheckBoxRememberMe.isChecked();
                SharedPreference.saveData(baseActivity,SharedPreference.LOGIN_REMEBER_ME_CHECK_BOX
                ,checkBoxStatus);

                apiService.login(phone, password).enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        if (response.body().getStatus() == 1) {

                            String apiToken = response.body().getData().getApiToken();
                            String name = response.body().getData().getClient().getName();
                            String email = response.body().getData().getClient().getEmail();
                            String birthData = response.body().getData().getClient().getBirthDate();
                            String cityId = response.body().getData().getClient().getCityId();
                            String donationLastDate = response.body().getData().getClient().getDonationLastDate();
                            String bloodTypeId = response.body().getData().getClient().getBloodTypeId();
                            String governorateId = response.body().getData().getClient().getCity().getGovernorateId();

                            SharedPreference.saveData(baseActivity, SharedPreference.API_TOKEN, apiToken);
                            SharedPreference.saveData(baseActivity, SharedPreference.NAME, name);
                            SharedPreference.saveData(baseActivity, SharedPreference.EMAIL, email);
                            SharedPreference.saveData(baseActivity, SharedPreference.BIRTH_DATE, birthData);
                            SharedPreference.saveData(baseActivity, SharedPreference.CITY_ID, cityId);
                            SharedPreference.saveData(baseActivity, SharedPreference.PHONE, phone);
                            SharedPreference.saveData(baseActivity, SharedPreference.LAST_DONATION_DATE, donationLastDate);
                            SharedPreference.saveData(baseActivity, SharedPreference.BLOOD_TYPE_ID, bloodTypeId);
                            SharedPreference.saveData(baseActivity, SharedPreference.PASSWORD, password);
                            SharedPreference.saveData(baseActivity, SharedPreference.PASSWORD_CONFIRM, password);
                            SharedPreference.saveData(baseActivity, SharedPreference.GOV_ID, governorateId);

                            startActivity(new Intent(baseActivity, HomeActivity.class));

                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }

                        if (response.body().getStatus() == 0) {

                            Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {

                    }
                });
                break;
            case R.id.login_fragment_txt_view_dont_have_acc:
                HelperMethods.replaceFragment(baseActivity.getSupportFragmentManager(), R.id.user_activity, new RegisterFragment());
                break;
        }
    }
}


