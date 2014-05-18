package com.shakeSuppression.app.model;

import android.graphics.Point;

import com.shakeSuppression.app.model.movement.utils.Direction;

public class MovementConstraints {

    private float xSpeed = 1;
    private float ySpeed = 1;

    private int xDirection = Direction.Horizontal.Right.getValue();
    private int yDirection = Direction.Vertical.Down.getValue();

    public MovementConstraints() {
        this.xSpeed = 1;
        this.ySpeed = 1;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void toggleXDirection() {
        xDirection = xDirection * -1;
    }

    public void toggleYDirection() {
        yDirection = yDirection * -1;
    }

}
