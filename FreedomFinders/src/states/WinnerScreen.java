package states;

import processing.core.PApplet;

public class WinnerScreen {

	/**
	 * A draw method which prints the words "You Won Congrats"
	 * 
	 * @param surface the PApplet marker used to display this on the graphics window
	 **/
	public void draw(PApplet surface) {
		surface.background(255, 255, 255);
		surface.textSize(25);
		surface.text("You Won Congrats :)", 0, 80);
	}
}