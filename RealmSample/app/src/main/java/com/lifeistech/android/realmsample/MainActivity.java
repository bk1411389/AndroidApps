package com.lifeistech.android.realmsample;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends ListActivity {

    EditText editText;
    Button insert, get;
    Realm realm;

    List<Memo2> dataList = new ArrayList<>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        insert = (Button)findViewById(R.id.button);
        get = (Button)findViewById(R.id.button2);
        realm = realm.getDefaultInstance();

        adapter = new ListAdapter(this, dataList); //ここでエラー（'ListAdapter' is abstract; cannot ne instantiated）が出る
        setListAdapter(adapter);

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
