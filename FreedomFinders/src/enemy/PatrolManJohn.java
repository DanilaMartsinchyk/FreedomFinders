package enemy;

import Weapons.Bullet;
import processing.core.PApplet;
import processing.core.PImage;

public class PatrolManJohn {
	private PImage[] Image;
	private int x;
	private int y;
	private int width;
	private int height;
	Bullet b1;
	Bullet b2;
	Bullet b3;
	private int health;

	public PatrolManJohn(PImage[] i, int x, int y) {
		Image = i;
		this.x = x;
		this.y = y;
		width = 100;
		height = 35;
		health = 100;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void decreaseHealth(int health) {
		this.health -= health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void draw(PApplet drawer) {
		drawer.image(Image[drawer.frameCount % Image.length], (int) x + 35, (int) y + 10, (int) width, (int) height);
		int rectWidth = 40;
		drawer.noFill();
		if (health < 100) {
			drawer.rect((float) x + 45, (float) y - 30, rectWidth, 5);
		}
		if (health < 25) {
			drawer.fill(255, 0, 0);
		} else if (health < 50) {
			drawer.fill(255, 200, 0);
		} else if (health < 100) {
			drawer.fill(0, 255, 0);
		}
		if (health < 100)
			drawer.rect((float) x + 38, (float) y - 30, drawer.map(health, 0, 40, 0, 16), 5);
		drawer.fill(0, 0, 0);
	}
}