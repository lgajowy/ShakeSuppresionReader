package com.shakeSuppression.app.shakedetection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.shakeSuppression.app.animation.ShakeAnimationController;
import com.shakeSuppression.app.shakedetection.utils.Coordinates;

import java.util.Timer;
import java.util.TimerTask;

public class ShakeEventListener implements SensorEventListener {

    private ShakeAnimationController animationController;
    private Coordinates actualAcceleration;
    private Coordinates previousAcceleration;

    private boolean firstUpdate = true;
    private final float shakeThreshold = 0.5f;
    private boolean shakeInitiated = false;
    private float[] accelVals = new float[3];

    public ShakeEventListener(ShakeAnimationController animationController) {
        this.animationController = animationController;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new viewCoord(), 1000, 500);
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        accelVals = lowPass(se.values.clone(), accelVals);

        updateAccelParameters(new Coordinates(accelVals[0], accelVals[1], accelVals[2]));
        Coordinates delta = countDelta(previousAcceleration, actualAcceleration);

        if ((!shakeInitiated) && isAccelerationChanged(delta)) {
            shakeInitiated = true;
        } else if ((shakeInitiated) && isAccelerationChanged(delta)) {
            animationController.executeSuppresionAnimation(delta, 500);
        } else if ((shakeInitiated) && (!isAccelerationChanged(delta))) {
            shakeInitiated = false;
        }
    }

    float ALPHA = 0.5f;
    protected float[] lowPass(float[] input, float[] output) {
        if (output == null) return input;
        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void updateAccelParameters(Coordinates newAcceleration) {
        if (firstUpdate) {
            previousAcceleration = newAcceleration;
            firstUpdate = false;
        } else {
            previousAcceleration = actualAcceleration;
        }
        actualAcceleration = newAcceleration;
    }

    private boolean isAccelerationChanged(Coordinates delta) {
        Coordinates absDelta = delta.abs();
        return (absDelta.x > shakeThreshold && absDelta.y > shakeThreshold)
                || (absDelta.x > shakeThreshold && absDelta.z > shakeThreshold)
                || (absDelta.y > shakeThreshold && absDelta.z > shakeThreshold);
    }

    private Coordinates countDelta(Coordinates a, Coordinates b) {
        return new Coordinates(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    class viewCoord extends TimerTask {
        public void run() {
            Log.d("+++", "============================================");
            Log.d("ACC", "x = " + accelVals[0]);
            Log.d("ACC", "y = " + accelVals[1]);
            Log.d("ACC", "z = " + accelVals[2]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
