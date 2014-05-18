package com.shakeSuppression.app;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shakeSuppression.app.model.MovableBitmap;
import com.shakeSuppression.app.view.utils.PanelSize;

public class ReaderPanel extends SurfaceView implements SurfaceHolder.Callback {

    private MovableBitmap bookImage;

    public ReaderPanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        PanelSize readerPanelSize = new PanelSize(context);
        bookImage = new MovableBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.book), readerPanelSize.x / 2, readerPanelSize.y / 2);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        synchronized (holder) {
            renderWholeCanvas(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void renderWholeCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        bookImage.draw(canvas);
    }

    public MovableBitmap getBitmap() {
        return bookImage;
    }
}
