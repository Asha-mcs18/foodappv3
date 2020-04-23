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
    public void toDonorActivity(View view){
        Intent intent = new Intent(getApplicationContext(),DonorActivity.class);
     startActivity(intent);

    }
    public void toLoginActivity(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
    public void toRegisterActivity(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }
    public void toNGOActivity(View view){
        Intent intent = new Intent(getApplicationContext(),NgoActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
