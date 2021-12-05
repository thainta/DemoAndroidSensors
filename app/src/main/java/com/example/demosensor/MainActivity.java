package com.example.demosensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private Temperature temperature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        temperature = new Temperature(this);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tx >1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else if(tx < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rz > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else if(rz < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
        });

        temperature.setListener(new Temperature.Listener() {
            @Override
            public void onTemperature(float t) {
                if(t > 45.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();
        //gyroscope.register();
        //temperature.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unRegister();
        //gyroscope.unregister();
        //temperature.unregister();
    }
}