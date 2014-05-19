package com.shakeSuppression.app;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;

import com.shakeSuppression.app.fullscreen.FullscreenView;
import com.shakeSuppression.app.shakedetection.ShakeEventListener;

public class ShakeSuppressionActivity extends Activity {

    private FullscreenView fullscreen;
    private SensorManager sensorManager;
    private ShakeEventListener shakeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullscreen = new FullscreenView(this, findViewById(R.id.fullscreen_content_controls),
                findViewById(R.id.fullscreen_content));

        shakeListener = new ShakeEventListener(new AnimationController(findViewById(R.id.bookImg)));
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(shakeListener, sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL); // (2)
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreen.delayedFullscreenHide(100);
    }
}
