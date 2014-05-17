package com.shakeSuppression.app.viewutils;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.shakeSuppression.app.R;

public class FullscreenView {

    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;
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

    public FullscreenView(Activity controlledActivity) {
        this.controlledActivity = controlledActivity;
        setSystemUIHider(controlledActivity);
    }

    private void setSystemUIHider(Activity controlledActivity) {
        View controlsView = controlledActivity.findViewById(R.id.fullscreen_content_controls);
        View contentView = controlledActivity.findViewById(R.id.fullscreen_content);

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
