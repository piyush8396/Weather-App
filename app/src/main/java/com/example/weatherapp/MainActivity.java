package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

//import com.google.android.gms.location.LocationListener;

import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.ParcelableSpan;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener,OnAdapterItemClickListener {
    String APPId = "1aa1c77170fcdb824e74e94c02bd799b";
    int c=5;

    //  static FusedLocationProviderClient fusedLocationProvideClient;
    TextView temp;
    TextView city;

 final ArrayList<myList>lists = new ArrayList<myList>();

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         temp=(TextView) findViewById(R.id.temp) ;
         city=(TextView) findViewById(R.id.city_name);
//        day1 = (TextView) findViewById(R.id.day1);
//        temp1 = (TextView) findViewById(R.id.temp1);
//        day2 = (TextView) findViewById(R.id.day2);
//        temp2 = (TextView) findViewById(R.id.temp2);
//        day3 = (TextView) findViewById(R.id.day3);
//        temp3 = (TextView) findViewById(R.id.temp3);
//        day4 = (TextView) findViewById(R.id.day4);
//        temp4 = (TextView) findViewById(R.id.temp4);
//        day5 = (TextView) findViewById(R.id.day5);
//        temp5 = (TextView) findViewById(R.id.temp5);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    100);

        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this);
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null) {
            Log.d("lat1", Double.toString(location.getLatitude()));
            Log.d("lat2", Double.toString(location.getLongitude()));
            APiInterface aPiInterface = RetroFitClient.getRetrofit().create(APiInterface.class);
            Call<ModelClass> call = aPiInterface.getCurrentWeatherData(Double.toString(location.getLatitude()),
                    Double.toString(location.getLongitude()),c, APPId);
            Log.d("ram1",String.valueOf(call));

            call.enqueue(new Callback<ModelClass>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse( Call<ModelClass> call,  Response<ModelClass> response) {
                    Log.d("ram","aa");
                    if (response.code() == 200) {


                        ModelClass modelClass = response.body();
                       // assert modelClass != null;
                        Log.d("COD",modelClass.cod);
                        Log.d("size",Integer.toString(modelClass.list.size()));
                        for (int i = 0; i < modelClass.list.size(); i++) {
                            if (i == 0) {
                                Log.d("test",Double.toString(modelClass.list.get(i).main.temp));
                            temp.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                          //  temp1.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                               city.setText(modelClass.city.name);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date date = format.parse(modelClass.list.get(i).dt_txt);
                                    lists.add(new myList((String) DateFormat.format("EEEE", date), Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103"));
                                   // day1.setText((String) DateFormat.format("EEEE", date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }
                            if (i == 8) {
                               //temp2.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date date = format.parse(modelClass.list.get(i).dt_txt);
                                    lists.add(new myList((String) DateFormat.format("EEEE", date),Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103"));
                                    //day2.setText((String) DateFormat.format("EEEE", date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (i == 16) {
                              // temp3.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date date = format.parse(modelClass.list.get(i).dt_txt);
                                  // day3.setText((String) DateFormat.format("EEEE", date));
                                   lists.add(new myList((String) DateFormat.format("EEEE", date),Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (i == 24) {
                               //temp4.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date date = format.parse(modelClass.list.get(i).dt_txt);
                                    //day4.setText((String) DateFormat.format("EEEE", date));
                                    lists.add(new myList((String) DateFormat.format("EEEE", date),Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (i == 28) {
                               // temp5.setText(Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date date = format.parse(modelClass.list.get(i).dt_txt);
                                   // day5.setText((String) DateFormat.format("EEEE", date));
                                    lists.add(new myList((String) DateFormat.format("EEEE", date),Double.toString((int)modelClass.list.get(i).main.temp-273)+" \u2103"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }


                        }
                    }
                }

                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {
                }
            });
        }
       // Log.d("s",lists.size()+"");
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter=new MyListAdapter(lists,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        location.getLongitude();
        location.getLatitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }


    @Override
    public void onAdapterItemClickListener(int position) {
        myList m=lists.get(position);
        String day=m.getDay();
        String temp=m.getTemp();
        Toast.makeText(this,day+" temp is "+temp,Toast.LENGTH_LONG).show();
    }
}
