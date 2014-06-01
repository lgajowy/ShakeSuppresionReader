package com.shakeSuppression.app.animation;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.shakedetection.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class ShakeAnimationController {

    private static final String TAG = "ANIMATION";
    private SuppressionAnimation suppression;
    private List<Coordinates> probesToTake;
    private int amountOfProbes;

    public ShakeAnimationController(View animatedView) {
        this.suppression = new SuppressionAnimation(animatedView);
        probesToTake = new ArrayList<Coordinates>();
    }

    public void setProbesToTake(int amountOfProbes) {
        this.amountOfProbes = amountOfProbes;
    }

    public synchronized void takeNConsecutiveProbesAndExecuteAnimation(Coordinates deltaXYZ) {
        if (probesToTake.size() < amountOfProbes) {
            probesToTake.add(deltaXYZ);
        } else {
            triggerAnimation();
        }
    }

    public void triggerAnimation() {
        if (!(probesToTake.size() == 0)) {
            Coordinates averagedDelta = averageProbes();
            probesToTake = new ArrayList<Coordinates>();
            executeSuppresionAnimation(averagedDelta, ShakeParameters.SHAKE_DURATION);
        }
    }

    public Coordinates averageProbes() {
        Coordinates result = new Coordinates();
        for (Coordinates probe : probesToTake) {
            result.add(probe);
        }
        return result.divide(probesToTake.size());
    }

    public void executeSuppresionAnimation(Coordinates delta, int duration) {
        if (suppression.getState() == AnimationState.NotRunning) {
            suppression.animate(-1 * delta.x, -1 * delta.y , duration);
        } else {
            Log.d(TAG, " ANIMATION IS WORKING NOW!");
        }
    }
}
