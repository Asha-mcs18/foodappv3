package com.example.googlemapsdonor.firebasehandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googlemapsdonor.models.DataStatus;
import com.example.googlemapsdonor.models.DonationListModel;
import com.example.googlemapsdonor.models.DonationModel;
import com.example.googlemapsdonor.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FBDonationHandler {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference donationRef;
    private List<DonationModel> donations;
    Calendar startDate = new GregorianCalendar();
    Calendar endDate = new GregorianCalendar();
    int flag=0;

    //DonationModel donationModel;

    public FBDonationHandler(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        donationRef = firebaseDatabase.getReference(Constants.DONATION);
        donations  = new ArrayList<DonationModel>();
        //start date
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);
        startDate.set(Calendar.MILLISECOND,0);
        //for end date
        endDate.set(Calendar.HOUR_OF_DAY,11);
        endDate.set(Calendar.MINUTE,59);
        endDate.set(Calendar.SECOND,59);
        endDate.set(Calendar.MILLISECOND,999);
    }

    //done
    public void readDonations(final DataStatus dataStatus){
        donationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donations.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds!=null){
                        String key = ds.getKey();
                        DonationModel donationModel= ds.getValue(DonationListModel.class);
                        keys.add(key);
                        //donations.add(donationModel);
                        if(donationModel.getStatus()!=null&&donationModel.getStatus().equals(Constants.NOT_ACCEPTED_YET)) {
                            donations.add(donationModel);
                            Log.d("read donations", "donation key" + donationModel.getKey());
                            Log.d("read donations", "donation status" + donationModel.getStatus());
                        }
                    }
                }
                Log.d("read donations", "Outside donation handler for" + donations.size());
//                for (DonationModel dm:donations) {
//                    Log.d("For each loop",dm.getDonorKey());
//                }
                dataStatus.dataLoaded(donations);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("read donations", "Some problem occured fetching list");
                dataStatus.errorOccured(databaseError.getMessage());
            }
        });
    }

    //done
    public void newDonation(final DonationModel donationModel, final DataStatus dataStatus){
        final String donorKey = donationModel.getDonorKey();
        String pickUpLocationKey = donationModel.getPickUpLocationKey();
        String foodKey= donationModel.getFoodKey();
        if(donorKey!=null&&pickUpLocationKey!=null&&foodKey!=null){
            final String key = donationRef.push().getKey();
            donationModel.setKey(key);
            donationModel.setStatus(Constants.NOT_ACCEPTED_YET);
            donationModel.setTimestampCreated(ServerValue.TIMESTAMP);
            //if this is the first donation made by donor
            donationRef.orderByChild("timestampCreated").startAt(startDate.getTimeInMillis()).
                    endAt(endDate.getTimeInMillis()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    flag=0;
                    Log.d("New Donation","New donation added at key"+dataSnapshot.toString());
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        if(ds.getValue(DonationModel.class)!=null &&(ds.getValue(DonationModel.class).getDonorKey()).equals(donorKey)){
                            Log.d("New Donation","inside if condition hence donation already exist");
                            flag=1;
                        }
                    }
                    if(flag==0){
                        donationRef.child(key).setValue(donationModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("New Donation","New donation added at key"+donationModel.getKey());
                                        //donationRef.updateChildren()
                                        dataStatus.dataCreated(donationModel);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("New Donation","New donation Failure"+e.getMessage());
                                        dataStatus.errorOccured("New donation Failure"+e.getMessage());
                                    }
                                });
                    }
                    else{
                        Log.d("New Donation","Cannot add more than one donation per day for key "+ donorKey);
                        dataStatus.errorOccured("Cannot add more than one donation per day");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



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

    //done
    public void readDonationStatus(final String donorKey, final DataStatus dataStatus){

//        endDate = startDate;
//        endDate.add(Calendar.DAY_OF_MONTH,1);
        Log.d("Donation Hleper", "start date is " + startDate.getTimeInMillis());
        Log.d("Donation Hleper", "end date is " + endDate.getTimeInMillis());
        donationRef.orderByChild("timestampCreated").startAt(startDate.getTimeInMillis()).endAt(endDate.getTimeInMillis()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DonationModel donationModel=null;
                Log.d("Donation Hleper","Data Snapshot is "+dataSnapshot.toString());
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    if((ds.getValue(DonationModel.class)).getDonorKey().equals(donorKey)) {
                        donationModel = ds.getValue(DonationModel.class);
                    }
                }
                if(donationModel!=null){
                    Log.d("Donation Hleper", "Data Snapshot is " + donationModel.toString());

                    dataStatus.dataLoaded(donationModel.getStatus());
                }
                else{
                    Log.d("Donation Hleper", "Data Snapshot is " + donationModel.toString());
                    dataStatus.dataLoaded("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Donation Hleper","Data Snapshot is "+databaseError.getMessage());
                dataStatus.errorOccured(databaseError.getMessage());
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