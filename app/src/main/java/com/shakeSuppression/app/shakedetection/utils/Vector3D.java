package com.shakeSuppression.app.shakedetection.utils;

public class Vector3D {

    public float x;
    public float y;
    public float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3D abs() {
        return new Vector3D(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector3D add (Vector3D that) {
        this.x = this.x + that.x;
        this.y = this.y + that.y;
        this.z = this.z + that.z;
        return this;
    }

    public Vector3D divide (float value) {
        try {
        if (value == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        this.x = this.x / value;
        this.y = this.y / value;
        this.z = this.z / value;
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public static Vector3D countDelta(Vector3D a, Vector3D b) {
        return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z);
    }

}
