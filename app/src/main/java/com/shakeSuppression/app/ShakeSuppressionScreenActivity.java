package com.shakeSuppression.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.shakeSuppression.app.model.movement.MoveAndReturn;
import com.shakeSuppression.app.model.movement.MovementManager;

public class ShakeSuppressionScreenActivity extends Activity {

    private ReaderPanel readerPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readerPanel = new ReaderPanel(this);
        setContentView(readerPanel);
    }

    // for testing purposes.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MoveAndReturn movementTask = new MoveAndReturn(readerPanel, 15, 15);
        MovementManager.runTask(movementTask);
        return super.onTouchEvent(event);
    }
}
