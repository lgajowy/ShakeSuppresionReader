package com.shakeSuppression.app;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.shakeSuppression.app.model.MovableBitmap;

public class ReaderPanel extends SurfaceView implements
        SurfaceHolder.Callback {

    private static final String TAG = ReaderPanel.class.getSimpleName();

    private MovableBitmap bookImage;
    private Point panelSize;

    public ReaderPanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        panelSize = new Point();
        display.getSize(panelSize);

        bookImage = new MovableBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.book), panelSize.x / 2, panelSize.y / 2);

        // to be able to handle events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawMovableBitmap(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void drawMovableBitmap(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        synchronized (holder) {
            renderCanvas(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void renderCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        bookImage.draw(canvas);
    }
}
