package com.example.healthapp.pojo.webServices;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;


    public static final String BASE_URL = "https://ai-clinics.herokuapp.com/api/auth/";

    public static Retrofit retrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


// l var dh hyb2 f class lw7do m3 vars tania
//10.0.2.2:3306
//https://lungcancer-ar.herokuapp.com/api/auth/