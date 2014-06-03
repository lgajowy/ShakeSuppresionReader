package com.shakeSuppression.app.shakedetection;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.shakeSuppression.app.animation.ShakeAnimationController;
import com.shakeSuppression.app.animation.ShakeParameters;
import com.shakeSuppression.app.shakedetection.utils.Filter;
import com.shakeSuppression.app.shakedetection.utils.Vector3D;

public class ShakeEventListener implements SensorEventListener {

    private ShakeAnimationController animationController;
    private Vector3D actualAcceleration;
    private Vector3D previousAcceleration;

    private boolean firstUpdate = true;
    private boolean shakeInitiated = false;
    private float[] accelerationValues = new float[3];

    public ShakeEventListener(ShakeAnimationController animationController) {
        this.animationController = animationController;
        animationController.setProbesToTake(ShakeParameters.PROBES_FOR_AVERAGING);
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        accelerationValues = Filter.lowPass(se.values.clone(), accelerationValues);

        updateAccelParameters(new Vector3D(accelerationValues[0], accelerationValues[1], accelerationValues[2]));
        Vector3D delta = Vector3D.countDelta(previousAcceleration, actualAcceleration);

        if ((!shakeInitiated) && isThresholdExceeded(delta)) {
            shakeInitiated = true;
        } else if ((shakeInitiated) && isThresholdExceeded(delta)) {
            animationController.takeNConsecutiveProbesAndExecuteAnimation(delta);
        } else if ((shakeInitiated) && (!isThresholdExceeded(delta))) {
            animationController.triggerAnimation();
            shakeInitiated = false;
        }
    }

    private void updateAccelParameters(Vector3D newAcceleration) {
        if (firstUpdate) {
            previousAcceleration = newAcceleration;
            firstUpdate = false;
        } else {
            previousAcceleration = actualAcceleration;
        }
        actualAcceleration = newAcceleration;
    }

    private boolean isThresholdExceeded(Vector3D delta) {
        Vector3D absDelta = delta.abs();
        return (absDelta.x > ShakeParameters.SHAKE_THRESHOLD
                || absDelta.y > ShakeParameters.SHAKE_THRESHOLD
                || absDelta.z > ShakeParameters.SHAKE_THRESHOLD);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
