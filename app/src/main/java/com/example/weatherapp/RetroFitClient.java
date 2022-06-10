package com.example.weatherapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {
    private  static Retrofit retrofit;
    private static String base_url="https://api.openweathermap.org";
       public static  Retrofit getRetrofit()
       {
           if(retrofit==null)
           {
               retrofit=new Retrofit.Builder().baseUrl(base_url)
                       .addConverterFactory(GsonConverterFactory.create()).build();
           }
           return  retrofit;
       }
}
