package com.example.healthapp.pojo;
import com.example.healthapp.model.Data;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;

    // l var dh hyb2 f class lw7do m3 vars tania
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";


    public static Retrofit retrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}


