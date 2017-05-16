package com.lifeistech.android.karaokelistingapp;

import io.realm.RealmObject;

/**
 * Created by Hitoe Watarai on 2017/05/14.
 */

// データ格納用クラス
public class Data extends RealmObject{
    private long longData;       //日付用
    private String songData;  //曲名用
    private String artistData;        //アーティスト用


    public void setLongData(Long tmp) {
        this.longData = tmp;
    }

    public long getLongData() {
        return longData;
    }

    public void setStringData(String tmp) {
        this.songData = tmp;
    }

    public String getStringData() {
        return songData;
    }

    public void setArtistData(String artistData) {
        this.artistData = artistData;
    }

    public String getArtistData() {
        return artistData;
    }
}