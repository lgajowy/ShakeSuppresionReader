package com.shakeSuppression.app.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class SuppressionAnimation {

    private AnimationState state;

    public SuppressionAnimation() {
        state = AnimationState.NotRunning;

    }

    public void animate(View view, float deltaX, float deltaY, int duration) {
        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        TranslateAnimation anim = new TranslateAnimation(0, deltaX, 0, deltaY);
        anim.setAnimationListener(new AnimationListener());
        setAnimationConstraints(duration, anim);
        if(state == AnimationState.NotRunning) {
            view.startAnimation(anim);
        }
    }

    public synchronized AnimationState getState() {
        return state;
    }

    private void setAnimationConstraints(int duration, TranslateAnimation anim) {
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setDuration(duration);
        anim.setFillAfter(true);
    }

    private class AnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            state = AnimationState.Running;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            state = AnimationState.NotRunning;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            state = AnimationState.Retreat;
        }
    }
}
