package com.shakeSuppression.app.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.shakeSuppression.app.model.Speed;

public class MovableBitmap {

	private Bitmap bitmap;
	private int x;
	private int y;
	private boolean touched;
	private Speed speed;
	
	public MovableBitmap(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.speed = new Speed();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}

	public void update() {
		if (!touched) {
			x += (speed.getxVelocity() * speed.getxDirection());
			y += (speed.getyVelocity() * speed.getyDirection());
		}
	}
}
