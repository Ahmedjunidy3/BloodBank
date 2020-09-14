package com.example.bloodbank.data.api;


import com.example.bloodbank.data.model.generalSource.GeneralSource;
import com.example.bloodbank.data.model.generalSource.GeneralSource2;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.generalSource.GeneralSourcePagination;
import com.example.bloodbank.data.model.login.Login;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("blood-types")
    Call<GeneralSource> getBloodTypes();

    @GET("governorates")
    Call<GeneralSource> getGovernates();

    @GET("cities")
    Call<GeneralSource> getCities(@Query("governorate_id") String governId);

    @GET("post")
    Call<GeneralSource2> getPostDetails(@Query("api_token") String apiToken
            , @Query("post_id") Integer postId);

    @GET("posts")
    Call<GeneralSourcePagination> getPosts(@Query("api_token") String apiToken);

    @GET("posts")
    Call<GeneralSourcePagination> filterPosts(@Query("api_token") String apiToken
            , @Query("keyword") String keyword);

    @GET("donation-requests")
    Call<GeneralSourcePagination> getDonationReqs(@Query("api_token") String apiToken);

    @GET("donation-requests")
    Call<GeneralSourcePagination> filterDonationReqs(@Query("api_token") String apiToken
            , @Query("blood_type_id") String bloodTypeId
            , @Query("city_id") String cityId);

    @GET("donation-requests")
    Call<GeneralSourceData> getDonationDetails(@Query("api_token") String apiToken
            , @Query("donation_id") int donationId);

    @GET("my-favourites")
    Call<GeneralSourcePagination> getFavPosts(@Query("api_token") String apiToken);

    @GET("notifications")
    Call<GeneralSourcePagination> getNotifications(@Query("api_token") String apiToken);

    @POST("login")
    @FormUrlEncoded
    Call<Login> checkLoginDetails(@Field("phone") String phone, @Field("password") String password);

    @POST("signup")
    @FormUrlEncoded
    Call<Login> setUserRegisteredDetails(@Field("name") String name
            , @Field("email") String email
            , @Field("birth_date") String birthDate
            , @Field("city_id") String cityId
            , @Field("phone") String phoneNo
            , @Field("donation_last_date") String lastDonateDate
            , @Field("password") String pass
            , @Field("password_confirmation") String passConfirm
            , @Field("blood_type_id") String bloodType);

    @POST("reset-password")
    @FormUrlEncoded
    Call<Login> resetUserPass(@Field("phone") String phoneNo);

    @POST("new-password")
    @FormUrlEncoded
    Call<Login> setUserNewPass(@Field("password") String pass
            , @Field("password_confirmation") String passConfirm
            , @Field("pin_code") String pinCode, @Field("phone") String phoneNo);

    @POST("profile")
    @FormUrlEncoded
    Call<Login> updateProfile(@Field("api_token") String apiToken);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<GeneralSource2> addOrRemoveFavPost(@Field("post_id") String postId
            , @Field("api_token") String apiToken);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<GeneralSource2> getNotificationSettings(@Field("api_token") String apiToken);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<GeneralSource2> setNotificatSettings(@Field("api_token") String apiToken
            , @Field("governorates[]") List<String> govern
            , @Field("blood_types[]") List<String> bloodType);

    @POST("register-token")
    @FormUrlEncoded
    Call<Login> registerDeviceToken(@Field("token") String token
            , @Field("api_token") String apiToken
            , @Field("type") String platformType);

    @POST("remove-token")
    @FormUrlEncoded
    Call<Login> removeDeviceToken(@Field("token") String token
            , @Field("api_token") String apiToken);

    @POST("contact")
    @FormUrlEncoded
    Call<GeneralSource2> contactManagement(@Field("api_token") String apiToken
            , @Field("title") String title
            , @Field("message") String message);

}
