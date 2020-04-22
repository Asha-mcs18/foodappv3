package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.googlemapsdonor.firebasehandler.FBDonationHandler;
import com.example.googlemapsdonor.firebasehandler.FBFoodHandler;
import com.example.googlemapsdonor.firebasehandler.FBLocationHandler;
import com.example.googlemapsdonor.firebasehandler.FBUserHandler;
import com.example.googlemapsdonor.models.DonationModel;
import com.example.googlemapsdonor.models.FoodModel;
import com.example.googlemapsdonor.models.LocationModel;
import com.example.googlemapsdonor.models.UserModel;
import com.example.googlemapsdonor.utils.Constants;

public class MainActivity extends AppCompatActivity {
 public void toMapActivity(View view){
     Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
     startActivity(intent);
 }
 public void toSearchAddress(View view){
     //test code start
     FBUserHandler fbUserHandler = new FBUserHandler();
     fbUserHandler.readUser("-M5DfB22QnARRzo3N9nd",Constants.DONOR);
     UserModel user = new UserModel("user1","test1",Constants.NGO);
     fbUserHandler.addUser(user);
     //test code end

     Intent intent = new Intent(getApplicationContext(),SearchAddressMap.class);
     startActivity(intent);
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
