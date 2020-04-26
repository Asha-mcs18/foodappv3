package com.example.googlemapsdonor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
<<<<<<< HEAD
import android.view.View;

import static com.example.googlemapsdonor.utils.Notifications.CHANNEL_1_ID;
=======
import android.widget.TextView;

import com.example.googlemapsdonor.models.DonationModel;
>>>>>>> 99f4e58c6928f12e0432b4d1dc5b3514fc8d939d

public class DonationsStatus extends AppCompatActivity {

    public static final int REQUEST_CODE_GETMESSAGE = 1014;
    private NotificationManagerCompat notificationManager;
//    String latitude;
//    String longitude;
//    String foodItem;
//    String shelfLife;
//    String noOfPersons;
<<<<<<< HEAD
=======
    private TextView donationText;
>>>>>>> 99f4e58c6928f12e0432b4d1dc5b3514fc8d939d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_status);
        Log.i("Donation Status","status");
<<<<<<< HEAD


       String foodItem =getIntent().getStringExtra("foodItem");
       String shelfLife =getIntent().getStringExtra("shelfLife");
       String noOfPersons =getIntent().getStringExtra("noOfPersons");
       String latitude =getIntent().getStringExtra("latitude");
       String longitude =getIntent().getStringExtra("longitude");
       String hours = getIntent().getStringExtra("hours");
       String minutes = getIntent().getStringExtra("minutes");

        Log.i("ALL FOOD DETAILS","ENTERED BY DONOR");
        Log.i("FOOD ITEM", foodItem);
        Log.i("SHELF LIFE", shelfLife);
        Log.i("NO OF PERSONS",noOfPersons);
        Log.i("LATITUDE ", latitude);
        Log.i("LONGITUDE ", longitude);
        Log.i("HOURS",hours);
        Log.i("MINUTES",minutes);

        notificationManager = NotificationManagerCompat.from(this);
//        sendNotification();
    }
/* Check for donation isAccepted*/
//    if (){
//        sendNotification();
//    }
    public void sendNotification() {
        String title = "Your Donation Accepted";
        String message = "Your donation is accepted by NGO";
        Log.i("onsend","onsend");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_message_black)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
        Intent intent = new Intent(getApplicationContext(),NgoActivity.class);
        startActivity(intent);
=======
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
>>>>>>> 99f4e58c6928f12e0432b4d1dc5b3514fc8d939d
    }
}
