package com.lifeistech.android.presentcatch;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gameView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView= new GameView(this);
        setContentView(gameView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(!sensors.isEmpty()){
            sensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void onPause(){
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(gameView.player != null){
            gameView.player.move(-event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
