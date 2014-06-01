package com.shakeSuppression.app.shakedetection.utils;

import android.util.Log;

import java.util.TimerTask;

public class DisplayCoordinates extends TimerTask {

    private float[] values;

    public DisplayCoordinates(float[] values) {
        this.values = values;
    }

    public void run() {
        Log.d("+++", "============================================");
        Log.d("ACC", "x = " + values[0]);
        Log.d("ACC", "y = " + values[1]);
        Log.d("ACC", "z = " + values[2]);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
    }
}

