package Weapon;

import processing.core.PApplet;
import processing.core.PImage;

public class Gun {
	private PImage image;
	private int x;
	private int y;
	private int w;
	private int h;
	private float rotationFactor;
	/**
	 * creates a gun object
	 * @param i the image of the gun
	 * @param x x coordinate of the gun
	 * @param y y coordinate of the gun
	 * @param w width of the gun
	 * @param h height of the gun
	 */
	public Gun(PImage i, int x, int y, int w, int h) {
		image = i;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rotationFactor = 0;
	}
	/**
	 * rotates the gun by an angle (not complete yet)
	 * @param rotationFactor the factor of rotating the gun
	 */
	public void rotate(float rotationFactor) {
		this.rotationFactor = rotationFactor;
	}
	/**
	 * the x value
	 * @return the x value of the gun
	 */
	public double getX() {
		return x;
	}
	public void draw(PApplet drawer) {
		rotate(rotationFactor);
		drawer.image(image, (int) x, (int) y, (int) w, (int) h);
		rotate(-rotationFactor);
	}
}
