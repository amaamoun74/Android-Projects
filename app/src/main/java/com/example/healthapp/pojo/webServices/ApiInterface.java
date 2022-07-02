package com.example.healthapp.pojo.webServices;

import com.example.healthapp.model.Data;
import com.example.healthapp.model.DiseasesData;
import com.example.healthapp.model.Json;
import com.example.healthapp.model.PersonalInformation;
import com.example.healthapp.model.User;
import com.example.healthapp.model.UserLogin;
import com.example.healthapp.model.UserSignup;

import retrofit2.Call;
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
     @GET("get")
     Call<Data> getUserData();

     //common
     @POST("login")
     @FormUrlEncoded
     Call<UserLogin> login(@Field("email") String email, @Field("password") String password);

     @POST ("logout")
     Call<User>logout(String id);

     @POST("register")
     @FormUrlEncoded
     Call<UserSignup> register(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("age") String age,
                               @Field("gender") String gender,
                               @Field("address") String address,
                               @Field("phone") String phone,
                               @Field("state") String state,
                               @Field("MCA") String MCA,
                               @Field("em_num") String em_num);


     @POST("destroyuser/{userid}")
     Call<User>destroyUser(@Header("Authorization") String token, @Path("userid") int userid);


     // for user
     @GET("userview/{userid}")
     Call<DiseasesData> viewMedicalDataByPatient(@Header("Authorization") String token, @Path("userid") int userid);

     @GET("viewserdata/{userid}")
     Call<PersonalInformation> viewUser(@Header("Authorization") String token, @Path("userid") int userid);




     // for doctor
     @GET("showde/{userid}")
     Call<DiseasesData> viewDiseases(@Header("Authorization") String token, @Path("userid") int userid);

     @POST("updatediseases")
     Call<User> updateDiseases(String diseases ,String description);

     @GET("showresult")
     Call<DiseasesData>showUserData(@Header("Authorization") String token,@Query("ID") int ID);

     @GET("showrcovid")
     Call<User> showrCovid();



}
