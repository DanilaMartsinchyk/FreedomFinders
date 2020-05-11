package Players;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import tester.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * Represents a moving image.
 *
 * by: Shelby
 */

public class Sprite extends Rectangle2D.Double {

	// FIELDS
	private PImage[] image;
	private double vx, vy;
	private int a;

	/**
	 * Creates a sprite object
	 * 
	 * @param img A PImage representing the sprite object
	 * @param x   x coordinate for the top left corner of the sprite object
	 * @param y   y coordinate for the top left corner of the sprite object
	 * @param w   width of the sprite object
	 * @param h   height of the sprite object
	 */
	public Sprite(PImage[] img, int x, int y, int w, int h) {
		super(x, y, w, h);

		image = img;
		vx = 0;
		vy = 0;
	}

	public Sprite(ArrayList<PImage> a, int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	// METHODS
	/**
	 * moves the sprite to another location
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}

	/**
	 * moves the sprite by a certain amount
	 * 
	 * @param x amount moved in the x direction
	 * @param y amount moved in the y direction
	 */

	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}

	/**
	 * moves the sprite by velocities
	 */
	public void moveByVelocities() {
		super.x += vx;
		super.y += vy;
	}

	/**
	 * sets the velocity
	 * 
	 * @param vx the x velocity
	 * @param vy the y velocity
	 */
	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}

	/**
	 * accelerates the sprite object
	 * 
	 * @param ax acceleration in the x direction
	 * @param ay acceleration in the y direction
	 */
	public void accelerate(double ax, double ay) {
		vx += ax;
		vy += ay;
	}

	/**
	 * applys window boundrys
	 * 
	 * @param windowWidth  window width
	 * @param windowHeight window height
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x, windowWidth - width);
		y = Math.min(y, windowHeight - height);
		x = Math.max(0, x);
		y = Math.max(0, y);
	}

	/**
	 * draws the sprite object
	 * 
	 * @param g a PApplet object
	 */
	public void draw(PApplet g) {
		a++;
		g.image(image[a % image.length], (int) x, (int) y, (int) width, (int) height);
	}

	/**
	 * returns the sprites x velocity
	 * 
	 * @return x velocity as a double
	 */
	public double getVX() {
		return vx;
	}

	/**
	 * returns the sprites y velocity
	 * 
	 * @return y velocity as a double
	 */
	public double getVY() {
		return vy;
	}
}
