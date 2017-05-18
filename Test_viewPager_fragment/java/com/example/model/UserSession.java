package com.example.model;

import android.app.Application;

/**
 * Created by Administrator on 2017/4/24.
 */
public class UserSession extends Application{
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void onCreate() {
        id = null;
        //id = "lc";
        super.onCreate();
    }
}
