package com.example.healthapp.model;

// dh class bta3 l data w hn3dl 3lih 3 7sb l data bta3tna
// w lesa f classes tani zy kda l data l tania
// l fekra en klohm hyb2o t7t package l model

public class Data {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Data() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}