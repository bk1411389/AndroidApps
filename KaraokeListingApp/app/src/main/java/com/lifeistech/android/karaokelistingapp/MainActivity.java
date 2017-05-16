package com.lifeistech.android.karaokelistingapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class MainActivity extends ListActivity {

    MediaRecorder recorder;
    private Button recButton;
    private Boolean recJudge = true;
    private int recNum = 0;

    Realm realm;

    // サンプル用のデータを準備
    List<Data> dataList = new ArrayList<>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = realm.getDefaultInstance();

        recButton = (Button)findViewById(R.id.recordButton);

        // リストにサンプル用のデータを受け渡す
        adapter = new ListAdapter(this, dataList);
        setListAdapter(adapter);
    }

    public void onClick(View v) {
        // Activity implements OnClickListener
        if(recJudge){

            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

            //保存先
            String filePath = Environment.getExternalStorageDirectory() + "/audio"+recNum+".3gp";
            recorder.setOutputFile(filePath);

            //録音準備＆録音開始
            try {
                recorder.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            recorder.start();   //録音開始

            recButton.setText("録音中");
            recButton.setTextSize(14);
            recJudge = false;

        }else{
            recorder.stop();
            recorder.reset();   //オブジェクトのリセット
            //release()前であればsetAudioSourceメソッドを呼び出すことで再利用可能
            recorder.release(); //Recorderオブジェクトの解放

            displayDialog(null);

            recButton.setText("●");
            recButton.setTextSize(30);
            recJudge = true;
        }
    }

    //カスタムダイアログ表示
    public void displayDialog(View v){

        // カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.custom_dialog,
                (ViewGroup)findViewById(R.id.layout_root));

        // アラーとダイアログ を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("データ追加");
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OK ボタンクリック処理
                // ID と PASSWORD を取得
                EditText id
                        = (EditText)layout.findViewById(R.id.customDlg_songTitle);
                EditText pass
                        = (EditText)layout.findViewById(R.id.customDlg_artist);
                String songTitle = id.getText().toString();
                String artist = pass.getText().toString();

                if(songTitle.equals("")){
                    songTitle = "タイトル無し";
                }

                //Realmに入れる　ここから
                realm.beginTransaction();

                Data data = realm.createObject(Data.class);
                //Data data = new Data();
                Date dt = new Date();

                long timestamp = dt.getTime();
                data.setLongData(timestamp);
                data.setStringData(songTitle);
                data.setArtistData(artist);

                //realmに移行するときこの辺をどうしたらいいか分からない
                //dataList.add(data);
                //adapter.notifyDataSetChanged();

                //Realmに入れる　ここまで
                realm.commitTransaction();

                recNum++;
            }
        });
        // 表示
        builder.create().show();

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Cancel ボタンクリック処理
            }
        });
    }

}
