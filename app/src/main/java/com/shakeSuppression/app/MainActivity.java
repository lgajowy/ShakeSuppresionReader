package com.shakeSuppression.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    private View controlsView;
    private View contentView;

    private FullscreenViewController fullscreenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlsView = findViewById(R.id.fullscreen_content_controls);
        contentView = findViewById(R.id.fullscreen_content);
        fullscreenController = new FullscreenViewController(this, controlsView, contentView);

        contentView.setOnClickListener(onClickListener);
        findViewById(R.id.dummy_button).setOnTouchListener(onTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreenController.delayedFullscreenHide(100);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fullscreenController.toggleFullscreen();
        }
    };

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            fullscreenController.delayedFullscreenHide();
            return false;
        }
    };
}
