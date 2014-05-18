package com.shakeSuppression.app.model.movement;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;

import com.shakeSuppression.app.ReaderPanel;
import com.shakeSuppression.app.model.MovableBitmap;

import java.util.Stack;

public class MoveAndReturn implements Runnable {

    private final SurfaceHolder surfaceHolder;
    private final ReaderPanel readerPanel;
    private MovableBitmap bitmap;
    private final int xDistance;
    private final int yDistance;
    private Stack<Point> positionBreadCrumbs;

    public MoveAndReturn(ReaderPanel readerPanel, final int xDistance, final int yDistance) {
        this.readerPanel = readerPanel;
        this.bitmap = readerPanel.getBitmap();
        this.surfaceHolder = readerPanel.getHolder();
        this.xDistance = xDistance;
        this.yDistance = yDistance;
        this.positionBreadCrumbs = new Stack<Point>();
    }

    @Override
    public void run() {
        int gonexDistance = 0;
        int goneyDistance = 0;

        while (gonexDistance <= xDistance && goneyDistance <= yDistance) {
            memorizePosition();
            this.bitmap.updatePosition();
            iterateMovement();
            gonexDistance++;
            goneyDistance++;
        }

        while (!positionBreadCrumbs.empty()) {
                this.bitmap.setPosition(getPreviousPosition());
                iterateMovement();
        }
    }

    private void iterateMovement() {
        Canvas canvas = null;
        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.readerPanel.renderWholeCanvas(canvas);
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void memorizePosition() {
        positionBreadCrumbs.push(new Point(bitmap.getPosition()));
    }

    private Point getPreviousPosition() {
        return positionBreadCrumbs.pop();
    }


}
