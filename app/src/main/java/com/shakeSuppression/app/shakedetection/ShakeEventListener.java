package com.shakeSuppression.app.shakedetection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.shakeSuppression.app.AnimationController;
import com.shakeSuppression.app.shakedetection.utils.Coordinates;

public class ShakeEventListener implements SensorEventListener {

    private AnimationController animationController;
    private Coordinates actualAcceleration;
    private Coordinates previousAcceleration;

    private boolean firstUpdate = true;
    private final float shakeThreshold = 0.5f;
    private boolean shakeInitiated = false;

    public ShakeEventListener(AnimationController animationController) {
        this.animationController = animationController;
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        updateAccelParameters(new Coordinates(se.values[0], se.values[1], se.values[2]));
        Coordinates delta = countDelta(previousAcceleration, actualAcceleration);

        if ((!shakeInitiated) && isAccelerationChanged(delta)) {
            shakeInitiated = true;
        } else if ((shakeInitiated) && isAccelerationChanged(delta)) {
            animationController.executeSuppresionAnimation(delta, 500);
        } else if ((shakeInitiated) && (!isAccelerationChanged(delta))) {
            shakeInitiated = false;
        }
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
}
