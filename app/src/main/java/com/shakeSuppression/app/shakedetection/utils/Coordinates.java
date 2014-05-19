package com.shakeSuppression.app.shakedetection.utils;

public class Coordinates {

    public float x;
    public float y;
    public float z;

    public Coordinates(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinates abs() {
        return new Coordinates(Math.abs(x), Math.abs(y), Math.abs(z));
    }
}
