package com.example.googlemapsdonor.firebasehandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googlemapsdonor.models.FoodModel;
import com.example.googlemapsdonor.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FBFoodHandler {
    private FirebaseDatabase databaseRef = FirebaseDatabase.getInstance();
    private DatabaseReference foodRef  = databaseRef.getReference(Constants.FOOD);
    private DatabaseReference fullPathRef;
    private FoodModel food;

    public FBFoodHandler(){
        food =null;
    }

    //done
    public void addFood(FoodModel foodModel){
        Log.d("add Food","Inside function");
        if(foodModel!=null){
            String key =  foodRef.push().getKey();
            foodModel.setFoodKey(key);
            foodRef.child(key).setValue(foodModel);
            Log.d("Add Food","food added succesfully!! "+foodModel.getFoodKey());
        }
    }

    public void readLocationForKey(String key){
        fullPathRef= foodRef.child(key);
        //Log.d("read Location","Location read for key"+key);
        fullPathRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FoodModel food = dataSnapshot.getValue(FoodModel.class);
                Log.d("read food model","food key is "+ food.getFoodKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateLocation(FoodModel updatedFood){
        fullPathRef =null;
        fullPathRef = foodRef.child(updatedFood.getFoodKey());
        if (updatedFood != null) {
            //location = getLocForKey(updatedLocation.getLocationKey();
            fullPathRef.setValue(updatedFood);
            Log.d("updte ", "Loc updated successfully!!");
        }
        //}
    }

    public void getFoodItem(String key){
        fullPathRef = foodRef.child(key);
        fullPathRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                food = dataSnapshot.getValue(FoodModel.class);
                // call back here only
                Log.d("get fFood Item","food item is "+food.getFoodKey());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

/*
FBFoodHandler fbFoodHandler = new FBFoodHandler();
     FoodModel food = new FoodModel("bread",10,5);
     //fbFoodHandler.addFood(food);
     //fbFoodHandler.readLocationForKey("-M5OR9_G9hibUU1jKlPn");
     FoodModel foodModel=  fbFoodHandler.getFoodItem("-M5OR9_G9hibUU1jKlPn");
     if(foodModel!=null){
         Log.d("Main Activity","Food shelf life"+food.getFoodKey());
     }
* */