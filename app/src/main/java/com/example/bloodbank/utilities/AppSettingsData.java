package com.example.bloodbank.utilities;

import android.app.Activity;

import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.settings.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppSettingsData {

    private static String phoneNumber = null;
    private static String emailAdress = null;
    private static String fbUrl;
    private static String twUrl;
    private static String ytUrl;
    private static String instaUrl;


    private static void getAppSettingData(Activity activity){

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

       String apiToken = SharedPreference.loadString(activity,SharedPreference.API_TOKEN);

       apiService.getAppSettings(apiToken).enqueue(new Callback<Settings>() {
           @Override
           public void onResponse(Call<Settings> call, Response<Settings> response) {

               if (response.body().getStatus()==1){

                   AppSettingsData.emailAdress = response.body().getData().getEmail();

                   AppSettingsData.phoneNumber = response.body().getData().getPhone();

                   AppSettingsData.fbUrl = response.body().getData().getFacebookUrl();

                   AppSettingsData.twUrl = response.body().getData().getTwitterUrl();

                   AppSettingsData.ytUrl = response.body().getData().getYoutubeUrl();

                   AppSettingsData.instaUrl = response.body().getData().getInstagramUrl();

               }
                          }

           @Override
           public void onFailure(Call<Settings> call, Throwable t) {

           }
       });
   }


    public static String getPhoneNumber(Activity activity) {
        getAppSettingData(activity);
        return phoneNumber;
    }


    public static String getEmailAdress(Activity activity) {
        getAppSettingData(activity);
        return emailAdress;
    }

    public static String getFbUrl(Activity activity) {
        getAppSettingData(activity);
        return fbUrl;
    }


    public static String getTwUrl(Activity activity) {
        getAppSettingData(activity);
        return twUrl;
    }

    public static String getYtUrl(Activity activity) {
        getAppSettingData(activity);
        return ytUrl;
    }


    public static String getInstaUrl(Activity activity) {
        getAppSettingData(activity);
        return instaUrl;
    }
}
