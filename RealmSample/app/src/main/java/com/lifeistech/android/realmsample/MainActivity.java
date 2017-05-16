package com.lifeistech.android.realmsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button insert, get;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        insert = (Button)findViewById(R.id.button);
        get = (Button)findViewById(R.id.button2);
        realm = realm.getDefaultInstance();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        realm.beginTransaction();
                        Memo2 memo = realm.createObject(Memo2.class);
                        memo.title = editText.getText().toString();
                        realm.commitTransaction();
                    }
                });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memo2 memo = realm.where(Memo2.class).findFirst();
                Log.d("サンプルのメモ",memo.title);
            }
        });

    }
}
