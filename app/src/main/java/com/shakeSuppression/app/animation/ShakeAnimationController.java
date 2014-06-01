package com.shakeSuppression.app.animation;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.shakedetection.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class ShakeAnimationController {

    private static final String TAG = "ANIMATION";
    private View animatedView;
    private SuppressionAnimation supression;

    private List<Coordinates> probesToTake;
    private int amountOfProbes;

    public ShakeAnimationController(View animatedView) {
        this.animatedView = animatedView;
        this.supression = new SuppressionAnimation();
        probesToTake = new ArrayList<Coordinates>();
    }


    public void setProbesToTake(int amountOfProbes) {
        this.amountOfProbes = amountOfProbes;
    }

    public synchronized void takeNConsecutiveProbesAndExecuteAnimation(Coordinates deltaXYZ, int animationDuration) {
        if (probesToTake.size() < amountOfProbes) {
            probesToTake.add(deltaXYZ);
        } else {
            triggerAnimation(animationDuration);
        }
    }

    public void triggerAnimation(int animationDuration) {
        if (!(probesToTake.size() == 0)) {
            Coordinates averagedDelta = averageProbes();
            probesToTake = new ArrayList<Coordinates>();
            executeSuppresionAnimation(averagedDelta, animationDuration);
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
        if (supression.getState() == AnimationState.NotRunning) {
            Log.d(TAG, "animation" + "x = " + delta.x);
            Log.d(TAG, "animation" + "y = " + delta.y);
            Log.d(TAG, "animation" + "z = " + delta.z);
            supression.animate(animatedView, -1 * (delta.x * 70), -1 * (delta.y * 70), duration);
        } else {
            Log.d(TAG, "animation" + " ANIMATION WORKS! WAIT!!!!!");
        }
    }
}
