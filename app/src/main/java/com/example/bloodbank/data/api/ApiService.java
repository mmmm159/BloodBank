package com.example.bloodbank.data.api;


import com.example.bloodbank.data.model.article.Article;
import com.example.bloodbank.data.model.ath.Auth;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.contactus.ContactUs;
import com.example.bloodbank.data.model.donation.Donation;
import com.example.bloodbank.data.model.notificationsettings.NotificationSettings;
import com.example.bloodbank.data.model.settings.Settings;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("blood-types")
    Call<City> getBloodTypes();

    @GET("governorates")
    Call<City> getGovernorates();

    @GET("cities")
    Call<City> getCities(@Query("governorate_id") int governorateId);

    @POST("signup")
    @FormUrlEncoded
    Call<Auth> register(@Field("name") String name,
                        @Field("email") String email,
                        @Field("birth_date") String birthData,
                        @Field("city_id") String cityId,
                        @Field("phone") String phone,
                        @Field("donation_last_date") String donationLastDate,
                        @Field("password") String password,
                        @Field("password_confirmation") String passwordConfirmation,
                        @Field("blood_type_id") String bloodTypeId);

    @POST("login")
    @FormUrlEncoded
    Call<Auth> login(@Field("phone") String phone, @Field("password") String password);


    @POST("profile")
    @FormUrlEncoded
    Call<Auth> modify(@Field("name") String name,
                      @Field("email") String email,
                      @Field("birth_date") String birthData,
                      @Field("city_id") String cityId,
                      @Field("phone") String phone,
                      @Field("donation_last_date") String donationLastDate,
                      @Field("password") String password,
                      @Field("password_confirmation") String passwordConfirmation,
                      @Field("blood_type_id") String bloodTypeId,
                      @Field("api_token") String apiToken);


    @GET("categories")
    Call<City> getCategories();


    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> contactUs(@Field("api_token") String apiToken,
                              @Field("title") String messageTitle,
                              @Field("message") String message);


    @GET("settings")
    Call<Settings> getAppSettings(@Query("api_token") String apiToken);

    @GET("posts")
    Call<Article> getArticles(@Query("api_token") String apiToken,
                              @Query("page") int page);


    @GET("donation-requests")
    Call<Donation> getAllDonation(@Query("api_token") String apiToken,
                                  @Query("page") int page);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettings> getNotificationSettings(@Field("api_token") String apiToken);


    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettings> changeNotificationSettings(@Field("api_token") String apiToken
            , @Field("governorates[]") List<Integer> governatesList, @Field("blood_types[]") List<Integer> bloodTypesList);


}
