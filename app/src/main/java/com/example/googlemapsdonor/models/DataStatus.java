package com.example.googlemapsdonor.models;

<<<<<<< HEAD
public class DataStatus {
=======
import java.util.List;

public abstract class  DataStatus {
    public  void dataLoaded(Object object){}
    public  void dataLoaded(List<Object> object){}
    public  void dataLoaded(String object){}
    public void dataCreated(Object object){}
    public abstract   void errorOccured(String message);
    public  void dataUpdated(String message){}
    public void dataCreated(String message){}
>>>>>>> 5406baa731f0b4bf62206d8e0700ef99b0d637d2
}
