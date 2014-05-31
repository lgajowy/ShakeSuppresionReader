package com.shakeSuppression.app.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class Suppression {

    public void animate(View view, float deltaX, float deltaY, int duration) {
        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        TranslateAnimation anim = new TranslateAnimation(0, deltaX, 0, deltaY);
        setAnimationConstraints(duration, anim);
        view.startAnimation(anim);
    }

    private void setAnimationConstraints(int duration, TranslateAnimation anim) {
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setDuration(duration);
        anim.setFillAfter(true);
    }


}
