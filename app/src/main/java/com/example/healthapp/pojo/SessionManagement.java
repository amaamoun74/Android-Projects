package com.example.healthapp.pojo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.healthapp.UI.activity.StartingApp;

import java.util.HashMap;

public class SessionManagement {
    SharedPreferences sharedPreferences;
        SharedPreferences.Editor sharedPreferencesEditor;
    Context context;
    Intent intent;

    private static final String file_name = "file name";
    public static final String KEY_NAME= "name";
    public static final String KEY_MAIN_NAME= "mainName";
    public static final String KEY_MAIN_EMAIL= "mainEmail";
    public static final String KEY_ID= "id";
    public static final String KEY_userType= "userType";
    public static final String KEY_userIDForQrCode= "userid";
    public static final String KEY_PASSWORD= "password";
    public static final String KEY_Status= "status";
    public static final String KEY_Token= "token";
    public static final String KEY_COVID= "covid";



    public SessionManagement(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        sharedPreferencesEditor=sharedPreferences.edit();
    }


    public void saveData(String name,String password,boolean status){
        sharedPreferencesEditor.putString(KEY_NAME,name);
        sharedPreferencesEditor.putString(KEY_PASSWORD,password);
        sharedPreferencesEditor.putBoolean(KEY_Status,status);
        sharedPreferencesEditor.apply();
    }

    public HashMap<String,String> getData(){
        HashMap<String,String> User_HashMap = new HashMap<>();
        User_HashMap.put(KEY_NAME,null);
        User_HashMap.put(KEY_PASSWORD,null);

        return User_HashMap;
    }

    public Boolean isLogin(){
        return sharedPreferences.getBoolean(KEY_Status,false);
    }

    public void logOut(){
        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.commit();
        intent = new Intent(context, StartingApp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void saveID(int id){
        sharedPreferencesEditor.putInt(KEY_ID,id);
        sharedPreferencesEditor.apply();
    }

    public void saveUserState(String state){
        sharedPreferencesEditor.putString(KEY_userType,state);
        sharedPreferencesEditor.apply();
    }

    public void saveName(String name){
        sharedPreferencesEditor.putString(KEY_MAIN_NAME,name);
        sharedPreferencesEditor.apply();
    }

    public void saveMainEmail(String email){
        sharedPreferencesEditor.putString(KEY_MAIN_EMAIL,email);
        sharedPreferencesEditor.apply();
    }

    public void saveUserIdFromQR(String id){
        sharedPreferencesEditor.putString(KEY_userType,id);
        sharedPreferencesEditor.apply();
    }

    public void saveToken(String token){
        sharedPreferencesEditor.putString(KEY_Token,token);
        sharedPreferencesEditor.apply();
    }

    public void saveCovid(String covid){
        sharedPreferencesEditor.putString(KEY_COVID,covid);
        sharedPreferencesEditor.apply();
    }

    public String getCovid(){
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COVID,"NAN");
    }

    public int getID(){
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ID,0);
    }

    public int getUserIDFromQR(){
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_userIDForQrCode,0);
    }

    public String getUserState(){
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_userType,"doctor");
    }


    public String getToken() {
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Token,"");
    }

    public String getMainName() {
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MAIN_NAME,"");
    }

    public String getMainEmail() {
        sharedPreferences=context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MAIN_EMAIL,"");
    }

}
