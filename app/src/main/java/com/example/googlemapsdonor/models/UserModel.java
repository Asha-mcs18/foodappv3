package com.example.googlemapsdonor.models;

public class UserModel {
    private String userKey;
    private String userName;
    private String password;
    private String role;

    public UserModel(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserModel() {
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
