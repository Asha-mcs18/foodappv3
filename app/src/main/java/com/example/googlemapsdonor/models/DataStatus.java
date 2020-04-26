package com.example.googlemapsdonor.models;
<<<<<<< HEAD
//
//<<<<<<< HEAD
//public class DataStatus {
//=======
=======

import java.util.List;//<<<<<<< HEAD
>>>>>>> 99f4e58c6928f12e0432b4d1dc5b3514fc8d939d
import java.util.List;
import java.util.Map;

public abstract class  DataStatus {
    public  void dataLoaded(Object object){}
    public  void dataLoaded(List<?> donations){}
    public  void dataLoaded(String object){}
    public void dataCreated(Object object){}
    public abstract   void errorOccured(String message);
    public  void dataUpdated(String message){}
    public void dataCreated(String message){}
//>>>>>>> 5406baa731f0b4bf62206d8e0700ef99b0d637d2
}
