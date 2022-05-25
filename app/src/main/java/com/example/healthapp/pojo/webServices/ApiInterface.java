package com.example.healthapp.pojo.webServices;

import com.example.healthapp.model.Data;
import com.example.healthapp.model.Json;
import com.example.healthapp.model.Patient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

     // for user
     @GET("posts/1")
     Call<Json> getSpecificData();

     @GET("showresult")
     Call<Patient>showUserData(@Query("ID") String ID);

     @GET("get")
     Call<Data> getUserData();

     @GET("destroyuser")
     Call<Patient> destroyUser();

     @GET("showrcovid")
     Call<Patient> showrCovid();


     @POST("login")
     @FormUrlEncoded
     Call<Patient> login(@Field("email") String email, @Field("password") String password);

     @POST("register")
     @FormUrlEncoded
     Call<Patient> register(@Field("name") String name
             , @Field("email") String email
             , @Field("password") String password
             , @Field("age") String age
             , @Field("gender") String gender
             , @Field("address") String address
             , @Field("state") String state
            );

     // for doctor
     @GET("usersearch/{id}")
     Call<Patient> usersearch();

}
