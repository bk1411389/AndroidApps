package com.lifeistech.android.techdragon;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class infoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void back(View v){
        finish();
    }
}
