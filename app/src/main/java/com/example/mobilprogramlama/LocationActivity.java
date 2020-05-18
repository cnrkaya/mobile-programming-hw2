package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class LocationActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private TextView tvInfos;
    private static final int REQUEST_CODE = 200;
    private Button btn_showLocation;
    private Button btn_sendLocation;
    private Boolean info_visibility = false;
    private double longtitude;
    private double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tvInfos = findViewById(R.id.infos);
        btn_showLocation = findViewById(R.id.btn_showLocation);
        btn_sendLocation = findViewById(R.id.btn_sendLocation);
        getLastlocation();

        btn_showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_visibility == false) {
                    info_visibility = true;
                    tvInfos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    getLastlocation();
                } else {
                    tvInfos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    info_visibility = false;
                }

            }
        });
        btn_sendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "http://www.google.com/maps/place/"+
                        Double.toString(latitude)+","+Double.toString(longtitude);
                showMap(location);
            }
        });
    }
    public void showMap(String geoLocation) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,geoLocation);
        Intent sendIntent = Intent.createChooser(intent,null);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    private void getLastlocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    Toast.makeText(LocationActivity.this, "Location yakalandı", Toast.LENGTH_SHORT).show();
                    latitude = location.getLatitude();
                    longtitude = location.getLongitude();
                    tvInfos.setText(latitude +  "   "  + longtitude);

                    //tvInfos.setPadding(20,20,20,20);

                } else {
                    Toast.makeText(LocationActivity.this, "Location NULL döndü", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
