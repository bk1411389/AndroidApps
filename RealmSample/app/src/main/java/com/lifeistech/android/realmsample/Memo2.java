package com.lifeistech.android.realmsample;

import io.realm.RealmObject;

/**
 * Created by Hitoe Watarai on 2017/05/14.
 */

public class Memo2 extends RealmObject{
    public String title;

    public void setStringData(String tmp){
        this.title = tmp;
    }

    public String getStringData(){
        return title;
    }

}
