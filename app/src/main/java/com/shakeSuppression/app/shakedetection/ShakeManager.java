package com.shakeSuppression.app.shakedetection;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;

import com.shakeSuppression.app.animation.ShakeAnimationController;

public class ShakeManager {

    private SensorManager sensorManager;
    private Context context;
    private ShakeEventListener shakeListener;

    public ShakeManager(Context context, View shakedView) {
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeEventListener(new ShakeAnimationController(shakedView));
    }

    public void turnOnShakeDetection() {
        sensorManager.registerListener(shakeListener, sensorManager
                .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void turnOffShakeDetection() {
        sensorManager.unregisterListener(shakeListener);
    }
}
