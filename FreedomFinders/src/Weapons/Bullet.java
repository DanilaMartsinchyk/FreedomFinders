package Weapons;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {
	private float x,y,w,h;
	private float screenWidth, screenHeight;
	private float speed, rotation, maxSpeed, minSpeed;
	private boolean firing;
	private PImage Image;
	/**
	 * creates a bullet object
	 * @param i the bullet image
	 * @param screenWidth the width of the screen
	 * @param screenHeight the height of the screen
	 */
	public Bullet(PImage i,float screenWidth, float screenHeight) {
		x = 100;
		y = -100;
		w = 15;
		h = 10;
		speed = 0;
		rotation = 0;
		maxSpeed = 15;
		minSpeed = 5;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		Image = i;
		firing = false;
	}
	/**
	 * sets the start location of the bullet
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 * @param rotation rotation angle of the bullet
	 */
	public void setStartLocation(float x, float y, float rotation) {
		if(firing == false) {
			this.x = x;
			this.y = y;
			this.rotation = rotation;
			firing = true;
			speed = 20;
		}
	}
	/**
	 * updates the bullets position
	 */
	public void update() {
		if(firing == true) {
			if(speed > minSpeed) {
				speed -= 0.3;
			}
		}
		x += Math.cos(rotation) * speed * 0.5;
		y += Math.sin(rotation) * speed;
		if(x > screenWidth/2 || x < 0 || y > screenHeight || y < 0) {
			reset();
		}
	}
	/**
	 * resets the bullets position
	 */
	public void reset() {
		speed = 0;
		firing = false;
		y -= 100;
	}
	/**
	 * returns the x value
	 * @return the x coordinate of the bullet
	 */
	public float getX() {
		return x;
	}
	/**
	 * returns the y value
	 * @return the y coordinate of the bullet
	 */
	public float getY() {
		return y;
	}
	/**
	 * returns the width
	 * @return the width of the bullet
	 */
	public float getWidth() {
		return w;
	}
	/**
	 * returns the height
	 * @return the height of the bullet
	 */
	public float getHeight() {
		return h;
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.translate(x, y);
		drawer.rotate(rotation);
		if(firing) {
			drawer.image(Image, -w/2, -h/2,w,h);
		}
		
		drawer.popMatrix();
		
	}
}
