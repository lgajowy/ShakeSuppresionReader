package com.shakeSuppression.app.fullscreen;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

public class FullscreenView {

    private static final int AUTO_HIDE_DELAY_MILLIS = 4000;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    private SystemUiHider systemUiHider;
    private Activity controlledActivity;

    private Handler hideUIHandler = new Handler();

    private Runnable hideUIRunnable = new Runnable() {
        @Override
        public void run() {
            systemUiHider.hide();
        }
    };

    public FullscreenView(Activity controlledActivity, View controlsView, View contentView) {
        this.controlledActivity = controlledActivity;
        setSystemUIHider(controlledActivity, controlsView, contentView);
    }

    private void setSystemUIHider(Activity controlledActivity, View controlsView, View contentView) {
        systemUiHider = SystemUiHider.getInstance(controlledActivity, contentView, HIDER_FLAGS);
        systemUiHider.setup();
        systemUiHider.setOnVisibilityChangeListener(new FullscreenVisibilityChangeListener(controlsView, contentView, this));
    }

    private void delayedSystemUIHide(int delayMillis) {
        hideUIHandler.removeCallbacks(hideUIRunnable);
        hideUIHandler.postDelayed(hideUIRunnable, delayMillis);
    }

    public void delayedFullscreenHide(int timeInMilliseconds) {
        delayedSystemUIHide(timeInMilliseconds);
    }

    public void delayedFullscreenHide() {
        delayedSystemUIHide(AUTO_HIDE_DELAY_MILLIS);
    }

    public void toggleFullscreen() {
        systemUiHider.toggle();
    }

    public Activity getControlledActivity() {
        return controlledActivity;
    }
}
