package com.lifeistech.android.sampledebugapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private int[] numbers;
    private String[] str = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        numbers = new int[]{1,2,3,4,5};
        int number = numbers[4];
        Log.d("number",number+"");
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                getWeather();
            }
        });
    }

    public void getWeather(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getApplicationContext(),
                "http://weather.livedoor.com/forecast/webservice/json/v1?city=270000",
                null,
                new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                        Log.d("結果",response.toString());
                        try{
                            //Log.d("結果",response.getJSONArray("forecasts").getJSONObject(0).getString("dateLabel"));//get(0)は今日の分
                            str[0] = response.getJSONArray("forecasts").getJSONObject(0).getString("dateLabel");
                            str[1] = response.getJSONArray("forecasts").getJSONObject(0).getString("telop");
                            str[2] = response.getJSONArray("forecasts").getJSONObject(0).getString("date");
                            finish();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Log.d("結果","通信失敗");
                    }
                });
        //return new String[]{};
    }

    public void finish() {
        textView.setText(str[0]+";"+str[1]+";"+str[2]);
    }
}
