package com.example.healthapp.model;

public class BaseUser {
    public String name , email , phoneNumber, nationalID;

    public BaseUser(){}

    public BaseUser(String name, String email, String phoneNumber, String nationalID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalID = nationalID;
    }
}
