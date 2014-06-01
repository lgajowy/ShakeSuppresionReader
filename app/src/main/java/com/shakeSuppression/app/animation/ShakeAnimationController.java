package com.shakeSuppression.app.animation;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.shakedetection.utils.Coordinates;

public class ShakeAnimationController {

    private static final String TAG = "ANIMATION";
    private View animatedView;
    private SuppressionAnimation supression;

    public ShakeAnimationController(View animatedView) {
        this.animatedView = animatedView;
        this.supression = new SuppressionAnimation();
    }

    public void executeSuppresionAnimation(Coordinates delta, int duration) {
        Log.d(TAG, "animation" +  "x = " + delta.x);
        Log.d(TAG, "animation" +  "y = " + delta.y);
        Log.d(TAG, "animation" +  "z = " + delta.z);
        supression.animate(animatedView, -1 * (delta.x * 70), -1 * (delta.y * 70), duration);
    }
}
