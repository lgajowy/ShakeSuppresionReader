package com.shakeSuppression.app.fullscreen;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class FullscreenVisibilityChangeListener implements SystemUiHider.OnVisibilityChangeListener {
    private int controlsHeight;
    private int animationTime;

    private View controlsView;
    private View contentView;

    private FullscreenView fullscreenViewController;

    public FullscreenVisibilityChangeListener(View controlsView, View contentView, FullscreenView fullscreenViewController) {
        this.controlsView = controlsView;
        this.contentView = contentView;
        this.fullscreenViewController = fullscreenViewController;
    }

    @Override
    public void onVisibilityChange(boolean visible) {
        animateUIControls(visible);
        scheduleHide(visible);
    }

    private void animateUIControls(boolean visible) {
        setControlsHeight();
        setShortAnimationTime();
        animate(visible);
    }

    private void scheduleHide(boolean visible) {
        if (visible) {
            fullscreenViewController.delayedFullscreenHide();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void animate(boolean visible) {
        controlsView.animate()
                .translationY(visible ? 0 : controlsHeight)
                .setDuration(animationTime);
    }

    private void setShortAnimationTime() {
        if (animationTime == 0) {
            animationTime = fullscreenViewController.getControlledActivity().getResources().getInteger(
                    android.R.integer.config_shortAnimTime);
        }
    }

    private void setControlsHeight() {
        if (controlsHeight == 0) {
            controlsHeight = controlsView.getHeight();
        }
    }
}
