package com.example.bloodbank.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static final String SHARED_PREF_KEY = "bloodBank";


    public static final String API_TOKEN = "apiToken";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String CITY_ID = "cityId";
    public static final String BIRTH_DATE = "bloodBank";
    public static final String PHONE = "phone";
    public static final String LAST_DONATION_DATE = "lastDonationDate";
    public static final String BLOOD_TYPE_ID = "bloodTypeId";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "passwordConfirm";
    public static final String GOV_ID = "governorateId";
    public static final String LOGIN_REMEBER_ME_CHECK_BOX = "rememberMeCheckBox";

    private static SharedPreferences sharedPreference;

    private static void setSharedPreferences(Activity activity) {
        if (sharedPreference == null) {
            sharedPreference = activity.getSharedPreferences(
                    SHARED_PREF_KEY, Context.MODE_PRIVATE);
        }
    }



    public static void saveData(Activity activity,String key, String value){

        setSharedPreferences(activity);
        if (sharedPreference!=null){
            SharedPreferences.Editor editor = sharedPreference.edit();
           editor.putString(key,value);
           editor.apply();
        }

    }

    public static void saveData(Activity activity,String key, Boolean value){

        setSharedPreferences(activity);
        if (sharedPreference!=null){
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putBoolean(key,value);
            editor.apply();
        }

    }

    public static String loadString(Activity activity,String key) {

        setSharedPreferences(activity);

        return sharedPreference.getString(key,null);
    }

        public static Boolean loadBoolean(Activity activity,String key) {

            setSharedPreferences(activity);

            return sharedPreference.getBoolean(key, false);

        }


}
