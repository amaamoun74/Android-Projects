package com.example.healthapp.pojo.webServices;

import com.example.healthapp.model.Data;
import com.example.healthapp.model.DiseasesData;
import com.example.healthapp.model.Json;
import com.example.healthapp.model.PersonalInformation;
import com.example.healthapp.model.User;
import com.example.healthapp.model.UserLogin;
import com.example.healthapp.model.UserSignup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

     @GET("posts/1")
     Call<Json> getSpecificData();
     // for user

     @GET("showresult")
     Call<User>showUserData(@Query("ID") int ID);

     @GET("get")
     Call<Data> getUserData();


     @GET("showrcovid")
     Call<User> showrCovid();

     @POST("updatediseases")
     Call<User> updateDiseases(String diseases ,String description);
     @POST("login")
     @FormUrlEncoded
     Call<UserLogin> login(@Field("email") String email, @Field("password") String password);

     @POST("register")
     @FormUrlEncoded
     Call<UserSignup> register(@Body User user);
     // for doctor
     @GET("showde/{userid}")
     Call<DiseasesData> viewDiseases(@Header("Authorization") String token, @Path("userid") int userid);

     @POST ("logout")
     Call<User>logout(String id);

     @DELETE("destroyuser")
     Call<User>destroyUser(int id);

     @GET("viewserdata/{userid}")
     Call<PersonalInformation> viewUser(@Header("Authorization") String token, @Path("userid") int userid);

}
