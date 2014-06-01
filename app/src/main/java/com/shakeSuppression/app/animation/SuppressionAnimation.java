package com.shakeSuppression.app.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class SuppressionAnimation {

    private AnimationState state;
    private View animatedView;

    public SuppressionAnimation(View view) {
        state = AnimationState.NotRunning;
        this.animatedView = view;
    }

    public void animate( float deltaX, float deltaY, int duration) {
        int originalPos[] = new int[2];
        animatedView.getLocationOnScreen(originalPos);
        int shiftX = (int) (animatedView.getWidth() * deltaX * ShakeParameters.SHAKE_X_MAGNITUDE_MULTIPLIER);
        int shiftY = (int) (animatedView.getHeight() * deltaY * ShakeParameters.SHAKE_Y_MAGNITUDE_MULTIPLIER);

        TranslateAnimation anim = new TranslateAnimation(0, shiftX, 0, shiftY);
        anim.setAnimationListener(new AnimationListener());
        setAnimationConstraints(duration, anim);
        if(state == AnimationState.NotRunning) {
            animatedView.startAnimation(anim);
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
