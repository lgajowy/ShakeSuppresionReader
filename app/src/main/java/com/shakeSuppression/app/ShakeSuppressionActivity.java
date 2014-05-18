package com.shakeSuppression.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shakeSuppression.app.fullscreenutil.FullscreenView;

public class ShakeSuppressionActivity extends Activity {

    private FullscreenView fullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullscreen = new FullscreenView(this, findViewById(R.id.fullscreen_content_controls),
                findViewById(R.id.fullscreen_content));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreen.delayedFullscreenHide(100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        fullscreen.delayedFullscreenHide();

        int x = (int) event.getX();
        int y = (int) event.getY();
        animateSuppression(findViewById(R.id.bookImg), x, y, 300);

        return super.onTouchEvent(event);
    }

    private void animateSuppression(View view, int x, int y, int duration) {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        int xDest = x;
        xDest -= (view.getMeasuredWidth() / 2);
        int yDest = y;

        TranslateAnimation anim = new TranslateAnimation(0, xDest - originalPos[0], 0, yDest - originalPos[1]);
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);

    }
}
