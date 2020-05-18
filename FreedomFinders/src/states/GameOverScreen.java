package states;

import CollisionDetection.CollisionDetection;
import processing.core.PApplet;
import tester.DrawingSurface;

public class GameOverScreen {
	private int score;

	public GameOverScreen(int score) {
		this.score = score;
	}

	public void draw(PApplet surface) {
		surface.background(255, 255, 255);
		surface.fill(255, 255, 255);
		surface.rect(75, 50, 125, 20);
		surface.rect(75, 75, 125, 20);
		surface.textSize(10);
		surface.fill(0, 0, 0);
		surface.text("Your Score:" + score, 80, 65);
		surface.text("Restart By Clicking E", 80, 90);
	}
}