package com.example.mobilprogramlama;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.hardware.Sensor;
import android.widget.Toast;

import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;

public class SensorsActivity  extends Activity implements SensorEventListener {
    LinearLayout rootLayout;
    TextView control;
    SensorManager mySensorManager;
    ListView lv;
    SensorListAdapter[] sensorListAdapters; //for different light themes
    List<Sensor> mList;
    Sensor lightSensor,accSensor;
    CountDownTimer myTimer;
    int cctr = 0;
    float prevAccValue=0;
    float prevLux=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        //Tanımlamaları ayrı fonksiyonda yap

        sensorListAdapters = new SensorListAdapter[]{null, null, null, null}; //4 theme exist
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        control = (TextView) findViewById(R.id.control);
        lv = (ListView) findViewById(R.id.listView);
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        setTimer(5000);
        setListViewAdapter(3);


    }

    public void setTimer(int time_ms){
        myTimer = new CountDownTimer(time_ms, 1000) {

            public void onTick(long millisUntilFinished) {
                control.setText("Eğer hareket algılanmazsa uygulama " + millisUntilFinished / 1000+" saniye sonra sonlanacak");
            }

            public void onFinish() {
                Toast.makeText(SensorsActivity.this, "Uygulama Sonlandırılıyor", Toast.LENGTH_SHORT).show();
                control.setText("Bitti");
                myExit();
            }
        }.start();
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mySensorManager.unregisterListener(this);
        myTimer.cancel();
    }
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mySensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.i("lux degeri : ",valueOf(event.values[0]));
            float lux = event.values[0];
            changeLightMode(lux);
        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float currentAcc = event.values[0];
            if(Math.abs(currentAcc - prevAccValue)>0.5) {
                myTimer.cancel();
                setTimer(5000);
            }
            prevAccValue = currentAcc;
        }

    }
    public void changeLightMode(float lux){
        if( (int)lux/25 != (int)prevLux/25 ) {
            if (lux < 25) {
                //Very Dark Ambiance
                setListViewAdapter(0);
                rootLayout.setBackgroundColor(Color.BLACK);
                control.setTextColor(Color.WHITE);
            } else if (lux < 50) {
                //Dark Ambiance
                setListViewAdapter(1);
                rootLayout.setBackgroundColor(Color.DKGRAY);
                control.setTextColor(Color.parseColor("#e1eefa"));
            } else if (lux < 75) {
                //Daylight Ambiance
                setListViewAdapter(2);
                rootLayout.setBackgroundColor(Color.LTGRAY);
                control.setTextColor(Color.parseColor("#576373"));
            } else {
                //Sunlight Ambiance
                setListViewAdapter(3);
                rootLayout.setBackgroundColor(Color.WHITE);
                control.setTextColor(Color.BLACK);
            }
        }
        prevLux = lux;
    }

    private void setListViewAdapter(int lightMode){
        // The object is defined if the object has not been previously defined.
        // And The listview adjusted to the desired brightness theme.
        if(sensorListAdapters[lightMode] == null ) {
            sensorListAdapters[lightMode] = new SensorListAdapter(mList, this, lightMode);
        }
        lv.setAdapter(sensorListAdapters[lightMode]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void myExit(){
        this.finish();
    }





}