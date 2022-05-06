package com.example.healthapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor {

    @SerializedName("Doctor_ID")
    @Expose
    private String doctorID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("specialist")
    @Expose
    private String specialist;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
