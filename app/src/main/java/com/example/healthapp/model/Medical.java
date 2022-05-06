package com.example.healthapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Medical {

    @SerializedName("Hospital_ID")
    @Expose
    private String hospitalID;
    @SerializedName("Model_ID")
    @Expose
    private String modelID;
    @SerializedName("M_ID")
    @Expose
    private String mId;
    @SerializedName("Doctor_ID")
    @Expose
    private String doctorID;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("Bloodtype")
    @Expose
    private String bloodtype;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
