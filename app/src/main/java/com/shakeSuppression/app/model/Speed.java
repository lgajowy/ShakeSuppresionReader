package com.shakeSuppression.app.model;

public class Speed {
	
	public static final int DIRECTION_RIGHT	= 1;
	public static final int DIRECTION_LEFT	= -1;
	public static final int DIRECTION_UP	= -1;
	public static final int DIRECTION_DOWN	= 1;
	
	private float xVelocity = 1;	// velocity value on the X axis
	private float yVelocity = 1;	// velocity value on the Y axis
	
	private int xDirection = DIRECTION_RIGHT;
	private int yDirection = DIRECTION_DOWN;
	
	public Speed() {
		this.xVelocity = 1;
		this.yVelocity = 1;
	}

	public Speed(float xv, float yv) {
		this.xVelocity = xv;
		this.yVelocity = yv;
	}

	public float getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}
	public float getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
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

	// changes the direction on the X axis
	public void toggleXDirection() {
		xDirection = xDirection * -1;
	}

	// changes the direction on the Y axis
	public void toggleYDirection() {
		yDirection = yDirection * -1;
	}

}
