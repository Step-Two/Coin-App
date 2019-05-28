package com.example.astrat.diecoinsim;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayFlipActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_flip);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int numD6 = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);


        String disp = "";
        int tot = 0;
        for(int ind = 0; ind < numD6; ind++)
        {
            int val = (int)(1+Math.random()*6);
            tot += val;
            disp += Integer.toString(val);
            disp += " | ";
        }
        disp += " Total:";
        disp += Integer.toString(tot);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.flipResult);
        textView.setText(disp);

        mAccelerometerSensor = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

    }


    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometerSensor, 10000);
    }
    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        // we received a sensor event. it is a good practice to check
        // that we received the proper event
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


            TextView accX = findViewById(R.id.accX);
            accX.setText(Double.toString(event.values[0]));

            TextView accY = findViewById(R.id.accY);
            accY.setText(Double.toString(event.values[1]));

            TextView accZ = findViewById(R.id.accZ);
            accZ.setText(Double.toString(event.values[2]));
        }
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
