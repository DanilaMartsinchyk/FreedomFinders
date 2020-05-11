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

	public Obstacles(PImage i, int x, int y, int w, int h) {
		super(x, y, 30, 35);
		image = i;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		health = 100;
		difficulty = 0;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void move() {
		if (difficulty == 0) {
			this.x -= 3;
		}
		if (difficulty == 1) {
			this.x -= 3.5;
		}
		if (this.difficulty == 2) {
			this.x -= 4;
		}

	}

	public double getX() {
		return x;
	}

	public void subtractHealth(int health) {
		this.health -= health;
	}

	public int getHealth() {
		return health;
	}

	public void draw(PApplet drawer) {
		if (health == 50) {
			drawer.fill(255, 0, 0);
			drawer.rect(x + 5, y - 10, 25, 10);
		}
		drawer.image(image, (int) x, (int) y, (int) width, (int) height);
		drawer.fill(0, 0, 0);
	}

}