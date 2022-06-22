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
    public static final String KEY_ID= "id";
    public static final String KEY_userType= "userType";
    public static final String KEY_userIDForQrCode= "userid";
    public static final String KEY_PASSWORD= "password";
    public static final String KEY_Status= "status";


    public SessionManagement(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(file_name,context.MODE_PRIVATE);
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

    public void saveID(String id){
        sharedPreferencesEditor.putString(KEY_ID,id);
        sharedPreferencesEditor.apply();
    }

    public void saveUserState(String state){

        sharedPreferencesEditor.putString(KEY_userType,state);
        sharedPreferencesEditor.apply();
    }

    public void saveUserIdFromQR(String id){
        sharedPreferencesEditor.putString(KEY_userType,id);
        sharedPreferencesEditor.apply();
    }

    public String getID(){
        String ID = sharedPreferences.getString(KEY_ID,"");
        return ID;
    }

    public String getUserIDFromQR(){
        String UserID = sharedPreferences.getString(KEY_userIDForQrCode,"");
        return UserID;
    }

    public String getUserState(){
        String userType = sharedPreferences.getString(KEY_userType,"");
        return userType;
    }


}
