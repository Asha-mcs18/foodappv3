package com.example.googlemapsdonor.firebasehandler;

import com.example.googlemapsdonor.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FBUserAuthHandler {
    private FirebaseAuth firebaeAuth;

    public FBUserAuthHandler(){
        firebaeAuth = FirebaseAuth.getInstance();

    }

    public void isSignedIn(){
        FirebaseUser currentUser = firebaeAuth.getCurrentUser();
        if(currentUser!=null){
            //redirect to main page based on the user role
        }
        else{
            //redirect to register page
        }
    }

    public void createNewUser(UserModel newUser){

        firebaeAuth.createUserWithEmailAndPassword(newUser.getUserName(),newUser.getPassword());
    }

    public void signInUser(UserModel userModel){
        firebaeAuth.signInWithEmailAndPassword(userModel.getUserName(),userModel.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //redirect to main page based on role
                    }
                });
    }
}
