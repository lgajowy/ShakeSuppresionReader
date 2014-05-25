package com.shakeSuppression.app;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.animation.Suppression;
import com.shakeSuppression.app.shakedetection.utils.Coordinates;

public class AnimationController {

    private static final String TAG = "ANIMATION";
    private View animatedView;
    private Suppression supression;

    public AnimationController(View animatedView) {
        this.animatedView = animatedView;
        this.supression = new Suppression();
    }

    public void executeSuppresionAnimation(Coordinates delta, int duration) {
        Log.d(TAG, "" + delta.x + " " + delta.y);
        supression.animate(animatedView, delta.x * 100, delta.y * 100, duration);
    }
}
