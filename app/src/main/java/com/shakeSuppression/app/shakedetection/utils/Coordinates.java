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

    public Coordinates() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Coordinates abs() {
        return new Coordinates(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Coordinates add (Coordinates that) {
        this.x = this.x + that.x;
        this.y = this.y + that.y;
        this.z = this.z + that.z;
        return this;
    }

    public Coordinates divide (float value) {
        this.x = this.x / value;
        this.y = this.y / value;
        this.z = this.z / value;
        return this;
    }
}
