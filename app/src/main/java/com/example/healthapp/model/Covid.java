package com.example.healthapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covid {
    @SerializedName("M1_ID")
    @Expose
    private String m1Id;
    @SerializedName("faver")
    @Expose
    private String faver;
    @SerializedName("tiredness")
    @Expose
    private String tiredness;
    @SerializedName("Dry_Cough")
    @Expose
    private String dryCough;
    @SerializedName("Difficulty_in_Breathing")
    @Expose
    private String difficultyInBreathing;
    @SerializedName("Sore_Throat")
    @Expose
    private String soreThroat;
    @SerializedName("None_Sympton")
    @Expose
    private String noneSympton;
    @SerializedName("Pains")
    @Expose
    private String pains;
    @SerializedName("Nasal_Congestion")
    @Expose
    private String nasalCongestion;
    @SerializedName("Runny_Nose")
    @Expose
    private String runnyNose;
    @SerializedName("None_Experiencing")
    @Expose
    private String noneExperiencing;
    @SerializedName("Age_0_9")
    @Expose
    private String age09;
    @SerializedName("Age_10_19")
    @Expose
    private String age1019;
    @SerializedName("Age_20_24")
    @Expose
    private String age2024;
    @SerializedName("Age_25_59")
    @Expose
    private String age2559;
    @SerializedName("Age_60")
    @Expose
    private String age60;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("color")
    @Expose
    private String color;

    public String getM1Id() {
        return m1Id;
    }

    public void setM1Id(String m1Id) {
        this.m1Id = m1Id;
    }

    public String getFaver() {
        return faver;
    }

    public void setFaver(String faver) {
        this.faver = faver;
    }

    public String getTiredness() {
        return tiredness;
    }

    public void setTiredness(String tiredness) {
        this.tiredness = tiredness;
    }

    public String getDryCough() {
        return dryCough;
    }

    public void setDryCough(String dryCough) {
        this.dryCough = dryCough;
    }

    public String getDifficultyInBreathing() {
        return difficultyInBreathing;
    }

    public void setDifficultyInBreathing(String difficultyInBreathing) {
        this.difficultyInBreathing = difficultyInBreathing;
    }

    public String getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    public String getNoneSympton() {
        return noneSympton;
    }

    public void setNoneSympton(String noneSympton) {
        this.noneSympton = noneSympton;
    }

    public String getPains() {
        return pains;
    }

    public void setPains(String pains) {
        this.pains = pains;
    }

    public String getNasalCongestion() {
        return nasalCongestion;
    }

    public void setNasalCongestion(String nasalCongestion) {
        this.nasalCongestion = nasalCongestion;
    }

    public String getRunnyNose() {
        return runnyNose;
    }

    public void setRunnyNose(String runnyNose) {
        this.runnyNose = runnyNose;
    }

    public String getNoneExperiencing() {
        return noneExperiencing;
    }

    public void setNoneExperiencing(String noneExperiencing) {
        this.noneExperiencing = noneExperiencing;
    }

    public String getAge09() {
        return age09;
    }

    public void setAge09(String age09) {
        this.age09 = age09;
    }

    public String getAge1019() {
        return age1019;
    }

    public void setAge1019(String age1019) {
        this.age1019 = age1019;
    }

    public String getAge2024() {
        return age2024;
    }

    public void setAge2024(String age2024) {
        this.age2024 = age2024;
    }

    public String getAge2559() {
        return age2559;
    }

    public void setAge2559(String age2559) {
        this.age2559 = age2559;
    }

    public String getAge60() {
        return age60;
    }

    public void setAge60(String age60) {
        this.age60 = age60;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}