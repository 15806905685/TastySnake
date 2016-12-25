package com.example.stevennl.tastysnake.controller.test;

import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import com.example.stevennl.tastysnake.R;
import com.example.stevennl.tastysnake.base.BaseActivity;
import com.example.stevennl.tastysnake.util.sensor.SensorController;

public class SensorTestActivity extends BaseActivity {
    private static final String TAG = "SensorTestActivity";

    private Handler sensorHandler;
    private Runnable sensorRunnable = new Runnable() {
        @Override
        public void run() {
            xShow.setText("X: " + sController.getXAccVal() + "");
            yShow.setText("Y: " + sController.getYAccVal() + "");
            xAccShow.setText("X Acc: " + sController.getAccXAcc() + "");
            yAccShow.setText("Y Acc: " + sController.getAccYAcc() + "");
            sensorHandler.postDelayed(sensorRunnable, 80);
        }
    };

    private SensorController sController;
    private TextView xShow;
    private TextView yShow;
    private TextView xAccShow;
    private TextView yAccShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);
        xShow = (TextView) findViewById(R.id.sensor_test_x_txt);
        yShow = (TextView) findViewById(R.id.sensor_test_y_txt);

        xAccShow = (TextView) findViewById(R.id.sensor_test_x_acc_txt);
        yAccShow = (TextView) findViewById(R.id.sensor_test_y_acc_txt);

        sController = SensorController.getInstance(this);
        sController.register();
        sensorHandler = new Handler();
        sensorHandler.post(sensorRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorHandler.removeCallbacks(sensorRunnable);
        sController.unregister();
    }
}
