package com.shakeSuppression.app;

import android.util.Log;
import android.view.View;

import com.shakeSuppression.app.animation.Suppression;
import com.shakeSuppression.app.shakedetection.utils.Coordinates;

public class AnimationController {

    private static final String TAG = "ANIMATION";
    private View animatedView;

    public AnimationController(View animatedView) {
        this.animatedView = animatedView;
    }

    public void executeSuppresionAnimation(Coordinates delta) {
        Log.d(TAG, "" + delta.x + " " + delta.y);
        Suppression.animate(animatedView, delta.x * 100, delta.y * 100, 500);
    }
}
