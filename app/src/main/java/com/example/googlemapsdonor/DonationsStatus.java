package com.example.googlemapsdonor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.googlemapsdonor.models.DonationModel;

public class DonationsStatus extends AppCompatActivity {

    public static final int REQUEST_CODE_GETMESSAGE = 1014;
//    String latitude;
//    String longitude;
//    String foodItem;
//    String shelfLife;
//    String noOfPersons;
    private TextView donationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_status);
        Log.i("Donation Status","status");
        DonationModel donationModel = (DonationModel) getIntent().getSerializableExtra("DonationModel");
        donationText = findViewById(R.id.donationKey);
        donationText.setText(donationModel.getKey());

        Log.d("Donor Status Activity",donationModel.toString());

//       String foodItem =getIntent().getStringExtra("foodItem");
//       String shelfLife =getIntent().getStringExtra("shelfLife");
//       String noOfPersons =getIntent().getStringExtra("noOfPersons");
//       String latitude =getIntent().getStringExtra("latitude");
//       String longitude =getIntent().getStringExtra("longitude");
//       String hours = getIntent().getStringExtra("hours");
//       String minutes = getIntent().getStringExtra("minutes");
//
//        Log.i("ALL FOOD DETAILS","ENTERED BY DONOR");
//        Log.i("FOOD ITEM", foodItem);
//        Log.i("SHELF LIFE", shelfLife);
//        Log.i("NO OF PERSONS",noOfPersons);
//        Log.i("LATITUDE ", latitude);
//        Log.i("LONGITUDE ", longitude);
       // Log.i("HOURS",hours);
        //Log.i("MINUTES",minutes);
    }
}
