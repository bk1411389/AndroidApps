package com.lifeistech.android.karaokelistingapp;

import android.app.ListActivity;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends ListActivity {

    MediaRecorder recorder;
    private Button recButton;
    private Boolean recJudge = true;
    private int recNum = 0;

    // サンプル用のデータを準備
    List<Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recButton = (Button)findViewById(R.id.recordButton);

        // リストにサンプル用のデータを受け渡す
        ListAdapter adapter = new ListAdapter(this, dataList);
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
            //String filePath = Environment.getExternalStorageDirectory() + String.valueOf(getNowDate());
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

            recButton.setText("●");
            recButton.setTextSize(30);
            recJudge = true;

            Data data = new Data();
            //data.setLongData(Long.parseLong(getNowDate()));
            Date dt = new Date();
            long timestamp = dt.getTime();
            data.setLongData(timestamp);
            data.setStringData("title");
            data.setIntData(0);
            dataList.add(data);

            recNum++;
        }
    }

    public static String getNowDate(){
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE);
        final Date date = new Date(System.currentTimeMillis());

        return df.format(date);
    }

}



// データ格納用クラス
class Data {
    private long longData;       //日付用
    private String stringData;  //文言用
    private int intData;        //数値用

    public void setLongData(Long tmp) {
        this.longData = tmp;
    }

    public long getLongData() {
        return longData;
    }

    public void setStringData(String tmp) {
        this.stringData = tmp;
    }

    public String getStringData() {
        return stringData;
    }

    public void setIntData(int tmp) {
        this.intData = tmp;
    }

    public long getIntData() {
        return intData;
    }
}

// リスト表示制御用クラス
class ListAdapter extends ArrayAdapter<Data> {
    private LayoutInflater inflater;
    // values/colors.xmlより設定値を取得するために利用。
    private Resources r;

    public ListAdapter(Context context, List<Data> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        r = context.getResources();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        // layout/raw.xmlを紐付ける
        if (view == null) {
            view = inflater.inflate(R.layout.row, parent, false);
        }
        final Data data = this.getItem(position);
        TextView tvData1 = (TextView) view.findViewById(R.id.row1);
        TextView tvData2 = (TextView) view.findViewById(R.id.row2);
        TextView tvData3 = (TextView) view.findViewById(R.id.row3);
        if (data != null) {
            //1列目は日付データとしてフォーマット変更の上、表示
            SimpleDateFormat ymd = new SimpleDateFormat("yy/MM/dd", Locale.JAPANESE);
            tvData1.setText(ymd.format(data.getLongData()));
            //2列目は文字列なのでそのまま表示
            tvData2.setText(data.getStringData());
            //3列目は数値データのため、3桁ごとにカンマを入れて表示
            tvData3.setText(String.format("%1$,3d", data.getIntData()));
        }
        //偶数行の場合の背景色を設定
        if (position % 2 == 0) {
            tvData1.setBackgroundColor(r.getColor(R.color.data1));
            tvData2.setBackgroundColor(r.getColor(R.color.data1));
            tvData3.setBackgroundColor(r.getColor(R.color.data1));
        }
        //奇数行の場合の背景色を設定
        else {
            tvData1.setBackgroundColor(r.getColor(R.color.data2));
            tvData2.setBackgroundColor(r.getColor(R.color.data2));
            tvData3.setBackgroundColor(r.getColor(R.color.data2));
        }
        return view;
    }
}
