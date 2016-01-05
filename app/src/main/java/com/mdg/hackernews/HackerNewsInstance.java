package com.mdg.hackernews;

import android.app.Application;
import android.content.Context;

public class HackerNewsInstance extends Application {

    private static HackerNewsInstance sInstance;

    public static HackerNewsInstance getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

}
