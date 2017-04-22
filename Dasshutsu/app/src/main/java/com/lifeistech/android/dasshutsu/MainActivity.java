package com.lifeistech.android.dasshutsu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button buttonKey;

    private boolean hasKey;
    private boolean isOpened;
    private Button buttonDoor;

    //アプリが起動した時に呼ばれる関数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasKey=false;
        isOpened=false;

        //SharedPreferencesを使うための初期設定
        pref = getSharedPreferences("dasshutsu_data", MODE_PRIVATE);
        //buttonKey変数 と activity_main.xmlの@+id/buttonKeyを結びつける
        buttonKey = (Button) findViewById(R.id.buttonKey);
        buttonDoor=(Button) findViewById(R.id.buttonDoor);

        //鍵をとっていれば
        if (hasKey) {
            //鍵を見えなくする
            buttonKey.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //ドアがクリックされた時のに呼ばれる関数
    public void clickDoor(View v) {
        //開いているかどうか
        if(isOpened) {
            //画面の移動(インテント)
            Intent intent = new Intent(this, Stage2Activity.class);
            startActivity(intent);
        }else{
            if (hasKey) { //鍵がある
                buttonDoor.setBackgroundResource(R.drawable.door2);
                isOpened=true;
                //トーストを表示
                Toast.makeText(this, "ドアが開いたよ！", Toast.LENGTH_SHORT).show();
            } else { //鍵がない
                Toast.makeText(this, "鍵がないよ！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //鍵がクリックされた時に呼ばれる関数
    public void clickKey(View v) {
        //トーストを表示
        Toast.makeText(this, "鍵を手に入れたよ！", Toast.LENGTH_SHORT).show();
        hasKey=true;
        //SharedPreferencesに鍵を手に入れたことを書き込む
        editor = pref.edit();
        editor.putBoolean("key1", true);
        editor.commit();
        //鍵を見えなくする
        buttonKey.setVisibility(View.INVISIBLE);

    }
}