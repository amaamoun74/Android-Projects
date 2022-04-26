package com.example.healthapp.pojo;

import com.example.healthapp.model.Data;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
     @GET("posts/1")
     Call<Data> getPost();

     @POST("posts/1")
     Call<Data> sendPost();
}
