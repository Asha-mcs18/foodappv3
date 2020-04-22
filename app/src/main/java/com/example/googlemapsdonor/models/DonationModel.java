package com.example.googlemapsdonor.models;

public class DonationModel {
    private String key;
    private String ngoKey;
    private String donorKey;
    private String pickUpLocationKey;
    private String ngoLocationKey;
    private String donorLocationKey;
    private String foodKey;
    private String status;

    public DonationModel(String donorKey, String pickUpLocationKey, String foodKey) {
        this.donorKey = donorKey;
        this.pickUpLocationKey = pickUpLocationKey;
        this.foodKey = foodKey;
    }

    public DonationModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNgoKey() {
        return ngoKey;
    }

    public void setNgoKey(String ngoKey) {
        this.ngoKey = ngoKey;
    }

    public String getDonorKey() {
        return donorKey;
    }

    public void setDonorKey(String donorKey) {
        this.donorKey = donorKey;
    }

    public String getPickUpLocationKey() {
        return pickUpLocationKey;
    }

    public void setPickUpLocationKey(String pickUpLocationKey) {
        this.pickUpLocationKey = pickUpLocationKey;
    }

    public String getNgoLocationKey() {
        return ngoLocationKey;
    }

    public void setNgoLocationKey(String ngoLocationKey) {
        this.ngoLocationKey = ngoLocationKey;
    }

    public String getDonorLocationKey() {
        return donorLocationKey;
    }

    public void setDonorLocationKey(String donorLocationKey) {
        this.donorLocationKey = donorLocationKey;
    }

    public String getFoodKey() {
        return foodKey;
    }

    public void setFoodKey(String foodKey) {
        this.foodKey = foodKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
