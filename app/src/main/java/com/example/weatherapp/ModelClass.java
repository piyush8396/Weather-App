package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelClass {
@SerializedName("cod")
    String cod;
@SerializedName("message")
    int message;
@SerializedName("cnt")
    int cnt;
@SerializedName("list")
    ArrayList<list>list;
@SerializedName("city")
    City city;
 static class  list{
    @SerializedName("dt")
     int  dt;
    @SerializedName("main")
     Main main;
    @SerializedName("weather")
     ArrayList<Weather>weather;
    @SerializedName("clouds")
     Cloud cloud;
    @SerializedName("wind")
     Wind wind;
    @SerializedName("visibility")
     int visibility;
    @SerializedName("pop")
     int pop;
    @SerializedName("sys")
     Sys sys;
    @SerializedName("dt_txt")
     String dt_txt;


}
static class City
{
    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;
    @SerializedName("coord")
    Coord coord;
    @SerializedName("country")
    String country;
    @SerializedName("population")
    int population;
    @SerializedName("timezone")
    int timezone;
    @SerializedName("sunrise")
    long sunrise;
    @SerializedName("sunset")
    long sunset;
}
static class  Main
{
    @SerializedName("temp")
    double temp;
    @SerializedName("feels_like")
    double feel_like;
    @SerializedName("temp_min")
    double temp_min;
    @SerializedName("temp_max")
    double temp_max;
    @SerializedName("pressure")
    int pressure;
    @SerializedName("sea_level")
    int sea_level;
    @SerializedName("humidity")
    int humidity;
    @SerializedName("temp_kf")
    double temp_kf;


}
static class  Weather{
     @SerializedName("id")
    int id;
     @SerializedName("main")
    String main;
     @SerializedName("description")
    String description;
     @SerializedName("icon")
    String icon;
}
static class  Cloud
{
    @SerializedName("all")
    int all;
}
static class  Wind
{
    @SerializedName("speed")
    double speed;
    @SerializedName("deg")
    int deg;
    @SerializedName("gust")
      double gust;
}
static class  Sys
{
    @SerializedName("pod")
    String pod;
}
static class  Coord
{
    @SerializedName("lat")
    Double lat;
    @SerializedName("lon")
    Double lon;
}
}
