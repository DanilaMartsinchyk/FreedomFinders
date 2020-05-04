package Players;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Obstacles.Obstacles;
import processing.core.PApplet;
import processing.core.PImage;
import tester.DrawingSurface;

public class Player extends Sprite {

	public static final int STICK_FIGURE_LENGTH = 60;
	public static final int STICK_FIGURE_HEIGHT = 40;

	private int i;
	private int jumps;
	private boolean canJump;
	private boolean run;
	private int delay;
	private int amount = 0;
	private int a;
	PImage[] image;
	DrawingSurface d = new DrawingSurface();

	public Player(PImage[] i, int x, int y) {
		super(i, x, y, STICK_FIGURE_LENGTH, STICK_FIGURE_HEIGHT);
		jumps = 0;
		image = i;
		canJump = true;
		delay = 0;
	}

	public void jump() {
		if (canJump) {
			accelerate(0, -80);
			moveByVelocities();
			jumps++;
		}
		if (jumps >= 1) {
			canJump = false;
		}
	}

	public void fall() {
		if (this.y < 120) {
			setVelocity(0, 0);
			moveByAmount(0, 1.5);
		} else {
			canJump = true;
			jumps = 0;
		}

	}

	public void move(int dir) {
		if (dir == 1) {
			amount = 2;
		}
		if (dir == -1) {
			amount = -2;
		}
	}

	public void shoot() {

	}

	public boolean act(ArrayList<Obstacles> obstacles) {
		double a = obstacles.get(0).getX();
		double b = obstacles.get(1).getX();

		if ((a > 0 && a < 45) || (b > 0 && b < 45)) {
			if (y > 115 && y < 130) {
				
				return true;
			}
		}
		return false;

	}

	public void draw(PApplet drawer) {
		a++;
		x += amount;
		drawer.image(image[a % image.length], (int) x, (int) y, (int) width, (int) height);

	}
}