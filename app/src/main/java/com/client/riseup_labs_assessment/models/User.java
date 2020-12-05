package com.client.riseup_labs_assessment.models;

public class User {
    private String name;
    private String username;
    private int password;
    private String mobileNo;

    public User(String name, String username, int password, String mobileNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
