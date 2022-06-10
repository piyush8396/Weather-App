package com.example.weatherapp;

public class myList {
    String day,temp;
    public myList(String day,String temp)
    {
        this.day=day;
        this.temp=temp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
