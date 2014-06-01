package com.shakeSuppression.app.shakedetection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.shakeSuppression.app.animation.ShakeAnimationController;
import com.shakeSuppression.app.animation.ShakeParameters;
import com.shakeSuppression.app.shakedetection.utils.Coordinates;
import com.shakeSuppression.app.shakedetection.utils.DisplayCoordinates;

import java.util.Timer;

public class ShakeEventListener implements SensorEventListener {

    private ShakeAnimationController animationController;
    private Coordinates actualAcceleration;
    private Coordinates previousAcceleration;

    private boolean firstUpdate = true;
    private boolean shakeInitiated = false;
    private float[] accelerationValues = new float[3];

    public ShakeEventListener(ShakeAnimationController animationController) {
        this.animationController = animationController;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new DisplayCoordinates(accelerationValues), 1000, 500);
        animationController.setProbesToTake(ShakeParameters.PROBES_FOR_AVERGING);
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        accelerationValues = lowPass(se.values.clone(), accelerationValues);

        updateAccelParameters(new Coordinates(accelerationValues[0], accelerationValues[1], accelerationValues[2]));
        Coordinates delta = countDelta(previousAcceleration, actualAcceleration);

        if ((!shakeInitiated) && isAccelerationChanged(delta)) {
            shakeInitiated = true;
        } else if ((shakeInitiated) && isAccelerationChanged(delta)) {
            animationController.takeNConsecutiveProbesAndExecuteAnimation(delta);
        } else if ((shakeInitiated) && (!isAccelerationChanged(delta))) {
            animationController.triggerAnimation();
            shakeInitiated = false;
        }
    }

    protected float[] lowPass(float[] input, float[] output) {
        if (output == null) return input;
        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + ShakeParameters.LOW_PASS_ALPHA * (input[i] - output[i]);
        }
        return output;
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
        return (absDelta.x > ShakeParameters.SHAKE_THRESHOLD && absDelta.y > ShakeParameters.SHAKE_THRESHOLD)
                || (absDelta.x > ShakeParameters.SHAKE_THRESHOLD && absDelta.z > ShakeParameters.SHAKE_THRESHOLD)
                || (absDelta.y > ShakeParameters.SHAKE_THRESHOLD && absDelta.z > ShakeParameters.SHAKE_THRESHOLD);
    }

    private Coordinates countDelta(Coordinates a, Coordinates b) {
        return new Coordinates(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
