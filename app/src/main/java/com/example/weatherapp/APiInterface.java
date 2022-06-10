package com.example.weatherapp;

import android.graphics.DashPathEffect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APiInterface {

    @GET("/data/2.5/forecast")
    Call<ModelClass> getCurrentWeatherData(@Query("lat") String lat,@Query("lon")
            String lon,@Query("cnt") int cnt,@Query("appid") String app_id);

}
