package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    String userRole;
    public void onRegister(View view){
        final EditText email = (EditText) findViewById(R.id.emailField);
        final EditText password =  (EditText) findViewById(R.id.password_field);
        final EditText contact =  (EditText) findViewById(R.id.phoneField);

        Log.i("USER ROLE", userRole);
        Log.i("Email",email.getText().toString());
        Log.i("Password",password.getText().toString());
        Log.i("Contact num", contact.getText().toString());

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spin = (Spinner) findViewById(R.id.spin);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userRole = parent.getSelectedItem().toString();
                Log.i("user role",parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
