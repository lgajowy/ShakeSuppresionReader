package com.shakeSuppression.app.animation;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.shakedetection.utils.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class ShakeAnimationController {

    private static final String TAG = "ANIMATION";
    private SuppressionAnimation suppression;
    private List<Vector3D> probesToTake;
    private int amountOfProbes;

    public ShakeAnimationController(View animatedView) {
        this.suppression = new SuppressionAnimation(animatedView);
        probesToTake = new ArrayList<Vector3D>();
    }

    public void setProbesToTake(int amountOfProbes) {
        this.amountOfProbes = amountOfProbes;
    }

    public synchronized void takeNConsecutiveProbesAndExecuteAnimation(Vector3D deltaXYZ) {
        if (probesToTake.size() < amountOfProbes) {
            probesToTake.add(deltaXYZ);
        } else {
            triggerAnimation();
        }
    }

    public void triggerAnimation() {
        if (!(probesToTake.size() == 0)) {
            Vector3D averagedDelta = averageProbes();
            probesToTake = new ArrayList<Vector3D>();
            //TODO: HOW TO GET SPEED??
            //int duration = (int) (  Math.sqrt((averagedDelta.x * averagedDelta.x) + (averagedDelta.y * averagedDelta.y)) * ShakeParameters.ANIMATION_DURATION);
            executeSuppressionAnimation(averagedDelta, ShakeParameters.ANIMATION_DURATION);
            //executeSuppressionAnimation(averagedDelta, duration);
        }
    }

    public Vector3D averageProbes() {
        Vector3D result = new Vector3D();
        for (Vector3D probe : probesToTake) {
            result.add(probe);
        }
        return result.divide(probesToTake.size());
    }

    public void executeSuppressionAnimation(Vector3D delta, int duration) {
        if (suppression.getState() == AnimationState.NotRunning) {
            Vector3D reversedDirectionDelta = delta.divide(-1);
            suppression.animate(reversedDirectionDelta.x , reversedDirectionDelta.y , duration);
        } else {
            Log.d(TAG, "ANIMATION IS BUSY!");
        }
    }
}
