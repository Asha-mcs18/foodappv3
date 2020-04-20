package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
 public void toMapActivity(View view){
     Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
     startActivity(intent);
 }
 public void toSearchAddress(View view){
     Intent intent = new Intent(getApplicationContext(),SearchAddressMap.class);
     startActivity(intent);
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
