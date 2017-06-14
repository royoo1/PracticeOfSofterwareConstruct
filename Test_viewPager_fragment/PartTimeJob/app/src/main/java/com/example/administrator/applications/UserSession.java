package com.example.administrator.applications;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by Administrator on 2017/6/13.
 */

public class UserSession extends Application {
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
        super.onCreate();

        EaseUI.getInstance().init(this, null);
        EMClient.getInstance().setDebugMode(true);

    }
}
