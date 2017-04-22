package com.lifeistech.android.clap;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ImageButton button;
    Spinner spinner;
    Clap clapInstance;
    int repeat=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton)findViewById(R.id.ImgBtn);
        spinner = (Spinner)findViewById(R.id.spinner);
        //button.setOnClickListener(this);

        String[] strArray = new String[5];
        strArray[0]="パンッ！";
        strArray[1]="パンパンッ！";
        strArray[2]="パンパンパンッ！";
        strArray[3]="4回";
        strArray[4]="5回";

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,strArray
        );
        arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_item
        );
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        clapInstance = new Clap(this.getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        clapInstance.repeatPlay(repeat);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        repeat=position+1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
