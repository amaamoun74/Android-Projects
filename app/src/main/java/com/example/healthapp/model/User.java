package com.example.healthapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
 @SerializedName("fname")
 @Expose
 private String fname;
 @SerializedName("lname")
 @Expose
 private String lname;
 @SerializedName("username")
 @Expose
 private String username;
 @SerializedName("password")
 @Expose
 private String password;
 @SerializedName("status")
 @Expose
 private String status;
 @SerializedName("DOB")
 @Expose
 private String dob;
 @SerializedName("gender")
 @Expose
 private String gender;
 @SerializedName("age")
 @Expose
 private String age;
 @SerializedName("phone")
 @Expose
 private String phone;
 @SerializedName("address")
 @Expose
 private String address;
 @SerializedName("email")
 @Expose
 private String email;
 @SerializedName("photo")
 @Expose
 private String photo;
 @SerializedName("QR")
 @Expose
 private String qr;
 @SerializedName("ID")
 @Expose
 private String id;

 public String getFname() {
  return fname;
 }

 public void setFname(String fname) {
  this.fname = fname;
 }

 public String getLname() {
  return lname;
 }

 public void setLname(String lname) {
  this.lname = lname;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public String getDob() {
  return dob;
 }

 public void setDob(String dob) {
  this.dob = dob;
 }

 public String getGender() {
  return gender;
 }

 public void setGender(String gender) {
  this.gender = gender;
 }

 public String getAge() {
  return age;
 }

 public void setAge(String age) {
  this.age = age;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPhoto() {
  return photo;
 }

 public void setPhoto(String photo) {
  this.photo = photo;
 }

 public String getQr() {
  return qr;
 }

 public void setQr(String qr) {
  this.qr = qr;
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

}
