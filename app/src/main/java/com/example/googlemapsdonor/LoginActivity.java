package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googlemapsdonor.firebasehandler.FBUserHandler;
import com.example.googlemapsdonor.models.DataStatus;
import com.example.googlemapsdonor.models.UserModel;

public class LoginActivity extends AppCompatActivity {
    private FBUserHandler fbUserHandler= new FBUserHandler();

    public void toRegisterActivity(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }
    public void onLogin(View view){
        final EditText email = (EditText) findViewById(R.id.emailField);
        final EditText password =  (EditText) findViewById(R.id.passwordField);
        Log.i("Login Data","Entered by user");
        Log.i("email",email.getText().toString());
        Log.i("password",password.getText().toString());
        //userModel.setPassword(password.getText().toString());
        fbUserHandler.readUser(email.getText().toString(),password.getText().toString(),new DataStatus(){
            @Override
            public void dataLoaded(Object object) {
                super.dataLoaded(object);
                UserModel user = (UserModel) object;
                Toast.makeText(LoginActivity.this,"Welcome "+user.getUserName(),Toast.LENGTH_LONG).show();
                //intent to rediret to new activity based on role
            }

            @Override
            public void errorOccured(String message) {
                Toast.makeText(LoginActivity.this,"Login Failed! "+message,Toast.LENGTH_LONG).show();
            }
        });
        //action to be taken after user login

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
