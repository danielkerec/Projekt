package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MyApplication extends Application {
    public static final String APP_ID = "APP_ID_KEY";
    public static final String TAG_NAME = "NAME";
    public static final String TAG_NUM = "NUMBER";
    public static final String TAG_ID = "ID";
    public static final String TAG_COM = "COMMENT";



    static SharedPreferences sp;

    private String idAPP;
    private String playerID;
    public static final String TAG = MyApplication.class.getSimpleName();

    private Restavracija_item data; //All data
    private Gson gson;
    private File file;

    private static final String MY_FILE_NAME = "izhod.txt";

    @Override
    public void onCreate() {
        super.onCreate();

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        if (sp.contains(APP_ID)) //READ IT FROM FILE
            idAPP = sp.getString(APP_ID,"DEFAULT VALUE ");
        else { //FIRST TIME GENERATE ID AND SAVE IT
            idAPP = UUID.randomUUID().toString().replace("-", "");
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(APP_ID,idAPP);
            editor.apply();
        }
        Log.d(TAG,idAPP);

        if (sp.contains(TAG_ID)) //READ IT FROM FILE
            playerID = sp.getString(TAG_ID,"DEFAULT VALUE ");
        else { //FIRST TIME GENERATE ID AND SAVE IT
            playerID = UUID.randomUUID().toString().replace("-", "");
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(TAG_ID,playerID);
            editor.apply();
        }
        Log.d(TAG,playerID);

        if(!readFromFile())
        {
            if(sp.contains(TAG_NAME))
            {
                if(sp.contains(TAG_NUM))
                {
                    data = (new Restavracija_item(sp.getString(TAG_NAME, ""),sp.getString(TAG_NUM, "")));
                }
            }
            else
            {
                data = (new Restavracija_item());
            }
        }

    }

    public String getAppId() {
        return idAPP;
    }

    public String getTagName() {
        return sp.getString(TAG_NAME, "");
    }

    public String getTagNum() {
        return sp.getString(TAG_NUM, "");
    }

    public String getTagId() {
        return sp.getString(TAG_ID, "");
    }

    public String getTagCom() {
        return sp.getString(TAG_COM, "");
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setTagName(String temp){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TAG_NAME,temp);

        editor.apply();
    }

    public void setTagCom(String temp){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TAG_COM,temp);

        editor.apply();
    }

    public void setTagSt(String temp){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TAG_NUM,temp);

        editor.apply();
    }

    private Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson;
    }

    private File getFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_FILE_NAME);
        }
        Log.i(TAG, file.getPath());
        return file;
    }

    private void saveToFile() {
        try {
            FileUtils.writeStringToFile(getFile(), getGson().toJson(data));
        } catch (IOException e) {
            Log.d(TAG, "Can't save "+file.getPath());
        }
    }
    private boolean readFromFile() {
        if (!getFile().exists())  return false;
        try {
            data = getGson().fromJson(FileUtils.readFileToString(getFile()) , Restavracija_item.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Restavracija_item getData() {
        if (data==null) {
            if (!readFromFile())
                data = new Restavracija_item(); //use demo data
        }
        return data;
    }

    public void save() {
        saveToFile();
    }

}

