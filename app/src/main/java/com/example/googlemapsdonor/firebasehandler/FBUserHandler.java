package com.example.googlemapsdonor.firebasehandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googlemapsdonor.models.UserModel;
import com.example.googlemapsdonor.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FBUserHandler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userRef;
    private DatabaseReference fullPath;

    public FBUserHandler(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef  = firebaseDatabase.getReference(Constants.USER);
    }

    //user will call this and it will direct for auth
    public void addUser(UserModel user){
        fullPath = userRef.child(user.getRole());
        if(user!=null) {
            //check if user with same username already exist
            fullPath.orderByChild(Constants.USER_NAME).equalTo(user.getUserName());



            String key = userRef.push().getKey();
            user.setUserKey(key);
            fullPath.child(key).setValue(user);
            Log.d("add user","user key is "+user.getUserKey());
        }
    }

    public void readUser(final String userKey,final String role){
        fullPath = userRef.child(role);
        fullPath.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("read user","key and role is "+dataSnapshot.exists()+"  "+dataSnapshot.toString());
                UserModel user = dataSnapshot.getValue(UserModel.class);
                if(user!=null) {
                    Log.d("read user", "user key read for role" + user.getRole());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("read user","error reading user");
            }
        });
    }

    public void updateUser(final UserModel updatedUser){
        fullPath =userRef.child(updatedUser.getRole());
        fullPath.orderByKey().equalTo(updatedUser.getUserKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                fullPath.child(updatedUser.getUserKey()).setValue(updatedUser);
                Log.d("updated user","user updated role is "+updatedUser.getRole());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("updated user","error performing update operation");
            }
        });
    }
}
