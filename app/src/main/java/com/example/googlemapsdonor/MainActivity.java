package com.example.googlemapsdonor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GETMESSAGE = 1014;
    String latitude;
    String longitude;
    TimePicker picker;
    public void toMapActivity(View view){
     Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
//     startActivity(intent);
     startActivityForResult(intent , REQUEST_CODE_GETMESSAGE);

 }
 public void toSearchAddress(View view){
     Intent intent = new Intent(getApplicationContext(),SearchAddressMap.class);
//     startActivity(intent);
     startActivityForResult(intent , REQUEST_CODE_GETMESSAGE);
 }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_GETMESSAGE:
                if (resultCode == Activity.RESULT_OK) {
                     latitude = data.getStringExtra("latitude");
                  longitude = data.getStringExtra("longitude");

                } else {
                    Log.i("Activity cancelled", "cancelled");
                }
        }

    }
 public void onSubmit(View view){
     final EditText  foodItem = (EditText) findViewById(R.id.FoodItemField);
     final EditText shelfLife =  (EditText) findViewById(R.id.shelfLifeField);
     final EditText noOfPersons = (EditText) findViewById(R.id.NoOfPersonsField);
     picker=(TimePicker)findViewById(R.id.timePicker1);
     final String hours = Integer.toString(picker.getHour());
     final String minutes = Integer.toString(picker.getMinute());
//     Log.i("ALL FOOD DETAILS","ENTERED BY DONOR");
//     Log.i("FOOD ITEM", foodItem.getText().toString());
//     Log.i("SHELF LIFE", shelfLife.getText().toString());
//     Log.i("NO OF PERSONS",noOfPersons.getText().toString());
//     Log.i("LATITUDE ", latitude);
//     Log.i("LONGITUDE ", longitude);

//     startActivityForResult(intent , REQUEST_CODE_GETMESSAGE);

     Button btn =(Button) findViewById(R.id.submitRequest);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(),DonationsStatus.class);
             intent.putExtra("foodItem", foodItem.getText().toString());
             intent.putExtra("shelfLife", shelfLife.getText().toString());
             intent.putExtra("noOfPersons",noOfPersons.getText().toString());
             intent.putExtra("latitude",latitude);
             intent.putExtra("longitude",longitude);
             intent.putExtra("hours",hours);
             intent.putExtra("minutes",minutes);

             startActivity(intent);
             setResult(Activity.RESULT_OK,intent);
             finish();
         }
     });
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
