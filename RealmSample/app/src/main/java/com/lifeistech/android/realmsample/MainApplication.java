package com.lifeistech.android.realmsample;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Hitoe Watarai on 2017/05/14.
 */

public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
    }
}
