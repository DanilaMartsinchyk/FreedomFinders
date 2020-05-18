package Obstacles;

import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

public class Obstacles extends Rectangle2D.Double {
	private PImage image;
	private int x;
	private int y;
	private int w;
	private int h;
	private int health;
	private int difficulty;
	private int healthBar;

	/**
	 * creates an obstacle object
	 * 
	 * @param i the image of the obstacle
	 * @param x the top left corner x coordinate of the object
	 * @param y the top left corner y coordinate of the object
	 * @param w the width of the object
	 * @param h the height of the object
	 */
	public Obstacles(PImage i, int x, int y, int w, int h) {
		super(x, y, 30, 35);
		image = i;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		health = 100;
		healthBar = 100;
		difficulty = 0;
	}

	/**
	 * sets the difficulty of the game
	 * 
	 * @param difficulty the difficulty of how fast the objects move
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void move() {
		if (difficulty == 0) {
			this.x -= 4;
		}
		if (difficulty == 1) {
			this.x -= 4.5;
		}
		if (this.difficulty == 2) {
			this.x -= 5.1;
		}

	}

	/**
	 * @return the x coordinate of the object
	 */
	public double getX() {
		return x;
	}

	/**
	 * reduces health of the obect
	 * 
	 * @param health the amount of health reduced
	 * @param drawer
	 */
	public void subtractHealth(int health, PApplet drawer) {
		this.health -= health;
	}

	/**
	 * 
	 * @return the health of the object
	 */
	public int getHealth() {
		return health;
	}

	public void setXLoc(int x) {
		this.x = x;
	}

	public void draw(PApplet drawer) {
		int rectWidth = 40;
		drawer.noFill();
		if (health < 100) {
			drawer.rect(x - 5, y - 11, rectWidth, 10);
		}
		if (health < 25) {
			drawer.fill(255, 0, 0);
		} else if (health < 50) {
			drawer.fill(255, 200, 0);
		} else if (health < 100) {
			drawer.fill(0, 255, 0);
		}
		if (health < 100)
			drawer.rect(x - 5, y - 11, drawer.map(health, 0, 40, 0, 16), 10);

		drawer.image(image, (int) x, (int) y, (int) width, (int) height);
		drawer.fill(0, 0, 0);
	}

}
