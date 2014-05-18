package com.shakeSuppression.app.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class MovableBitmap {

    private Bitmap bitmap;
    private Point position;
    private MovementConstraints movementConstraints;

    public MovableBitmap(Bitmap bitmap, int initxPos, int inityPos) {
        this.bitmap = bitmap;
        this.position = new Point(initxPos, inityPos);
        this.movementConstraints = new MovementConstraints();
    }

    public void setMovementConstraints(MovementConstraints movementConstraints) {
        this.movementConstraints = movementConstraints;
    }

    public void setPosition(Point newPosition) {
        position = newPosition;
    }

    public Point getPosition() {
        return position;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, position.x - (bitmap.getWidth() / 2), position.y - (bitmap.getHeight() / 2), null);
    }

    public void updatePosition() {
        position.x += (movementConstraints.getxSpeed() * movementConstraints.getxDirection());
        position.y += (movementConstraints.getySpeed() * movementConstraints.getyDirection());
    }
}
