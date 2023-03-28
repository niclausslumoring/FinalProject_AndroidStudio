package com.example.signup;

public class User {
    private Integer userID;
    private String username;
    private String email;
    private String region;
    private String phone_number;
    private String password;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer userID, String username, String email, String region, String phone_number, String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.region = region;
        this.phone_number = phone_number;
        this.password = password;
    }
}

