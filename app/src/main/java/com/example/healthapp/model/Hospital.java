package com.example.healthapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Hospital {

    @SerializedName("Hos_name")
    @Expose
    private String hosName;
    @SerializedName("Hos_ID")
    @Expose
    private String hosID;
    @SerializedName("hos_add")
    @Expose
    private String hosAdd;
    @SerializedName("hos_phone")
    @Expose
    private String hosPhone;

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getHosID() {
        return hosID;
    }

    public void setHosID(String hosID) {
        this.hosID = hosID;
    }

    public String getHosAdd() {
        return hosAdd;
    }

    public void setHosAdd(String hosAdd) {
        this.hosAdd = hosAdd;
    }

    public String getHosPhone() {
        return hosPhone;
    }

    public void setHosPhone(String hosPhone) {
        this.hosPhone = hosPhone;
    }

}
