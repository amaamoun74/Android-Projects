package com.example.healthapp.model;

public class Diseases {
    private String id;
    private String diseases;
    private String description;
    private String user_id;

    public Diseases(String id, String diseases, String description, String user_id) {
        this.id = id;
        this.diseases = diseases;
        this.description = description;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}