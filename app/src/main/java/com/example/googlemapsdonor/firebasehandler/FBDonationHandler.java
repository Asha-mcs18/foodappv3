package com.example.googlemapsdonor.firebasehandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googlemapsdonor.models.DonationModel;
import com.example.googlemapsdonor.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FBDonationHandler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference donationRef;
    private List<DonationModel> donations;

    //DonationModel donationModel;

    public FBDonationHandler(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        donationRef = firebaseDatabase.getReference(Constants.DONATION);
        donations  = new ArrayList<DonationModel>();

    }

    //done
    public void readDonations(){
        donationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donations.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds!=null){
                        String key = ds.getKey();
                        DonationModel donationModel= ds.getValue(DonationModel.class);
                        keys.add(key);
                        if(donationModel.getStatus()!=null&&donationModel.getStatus().equals(Constants.NOT_ACCEPTED_YET)) {
                            donations.add(donationModel);
                            Log.d("read donations", "donation key" + donationModel.getKey());
                            Log.d("read donations", "donation status" + donationModel.getStatus());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //done
    public void newDonation(DonationModel donationModel){
        String donorKey = donationModel.getDonorKey();
        String pickUpLocationKey = donationModel.getPickUpLocationKey();
        String foodKey= donationModel.getFoodKey();
        if(donorKey!=null&&pickUpLocationKey!=null&&foodKey!=null){
            String key = donationRef.push().getKey();
            donationModel.setKey(key);
            donationModel.setStatus(Constants.NOT_ACCEPTED_YET);
            donationRef.child(key).setValue(donationModel);
            Log.d("New Donation","New donation added at key"+key);
        }
    }

    //done
    public void addNgo(final String ngoKey, final String donationKey){
        donationRef.child(donationKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonationModel donationModel = dataSnapshot.getValue(DonationModel.class);
                if(donationModel!=null&& donationModel.getStatus().equals(Constants.NOT_ACCEPTED_YET) ){
                    donationModel.setNgoKey(ngoKey);
                    donationModel.setStatus(Constants.ACCEPTED);
                    donationRef.child(donationKey).setValue(donationModel);
                    Log.d("get donation once","ngo key set is "+donationModel.getNgoKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //done
    public void changeStatusToComplete(final String donationKey){
        donationRef.child(donationKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonationModel donationModel = dataSnapshot.getValue(DonationModel.class);
                if(donationModel!=null&& donationModel.getStatus().equals(Constants.ACCEPTED) ){
                    //donationModel.setNgoKey(ngoKey);
                    donationModel.setStatus(Constants.SUCCESS);
                    donationRef.child(donationKey).setValue(donationModel);
                    Log.d("set complete ","donation done successfully!! "+donationModel.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("set failed","error setting status"+donationKey);
            }
        });
    }

    //done
    public void changeStatusToFail(final String donationKey){
        donationRef.child(donationKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonationModel donationModel = dataSnapshot.getValue(DonationModel.class);
                if(donationModel!=null&& donationModel.getStatus().equals(Constants.ACCEPTED) ){
                    //donationModel.setNgoKey(ngoKey);
                    donationModel.setStatus(Constants.FAILED);
                    donationRef.child(donationKey).setValue(donationModel);
                    Log.d("set failed ","donation failed!! "+donationModel.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("set failed","error setting status"+donationKey);
            }
        });
    }

    //done
    public void changeStatusToNA(final String donationKey){
        donationRef.child(donationKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonationModel donationModel = dataSnapshot.getValue(DonationModel.class);
                if(donationModel!=null&& donationModel.getStatus().equals(Constants.NOT_ACCEPTED_YET)){
                    //donationModel.setNgoKey(ngoKey);
                    donationModel.setStatus(Constants.NOT_ACCEPTED_BY_ANYONE);
                    donationRef.child(donationKey).setValue(donationModel);
                    Log.d("set complete ","donation done successfully!! "+donationModel.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

/*FBDonationHandler donationHandler = new FBDonationHandler();
     donationHandler.readDonations();
     DonationModel donationModel =new DonationModel("-M5DfB22QnARRzo3N9nd","-M59AD6NQYtMHccVso0J","-M53nnhSfqp6rw8Jwzvw");
     //donationHandler.newDonation(donationModel);
     //donationHandler.addNgo("-M5CmgL8PoC-01MzwTmR","-M5G2bZ75dpTECziT4x6");
     //donationHandler.changeStatusToComplete("-M5G5dpW9hAQ5W_NLLtl");
     //donationHandler.changeStatusToFail("-M5G8WDK8bgZFVUpmP_Z");
     //done
     //donationHandler.changeStatusToNA("-M5G7qqB--YDK91qB_Am");
* */