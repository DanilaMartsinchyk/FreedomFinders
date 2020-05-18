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
	public static final int STICK_FIGURE_HEIGHT= 40;
	
	private int jumps;
	private boolean canJump;
	private boolean run;
	private int delay;
	private int amount = 0;
	private int a;
	private int health;
	private int maxHealth;
	PImage [] image;
	DrawingSurface d = new DrawingSurface();

	/**
         * creates an obstacle object
         * @param i image of the obstacle
         * @param x top left x coordinate of the object
         * @param y top left y coordinate of the object
         * @param w width of the object
         * @param h height of the object
         **/
	public Player(PImage[] i, int x, int y) {
		super(i,x,y,STICK_FIGURE_LENGTH,STICK_FIGURE_HEIGHT);
		jumps = 0;
		image = i;
		canJump = true;
		delay = 0;
		health = 100;
		maxHealth = 100;
	}
	
	
	/**
	 * Method which allows the player object to jump
         * @param acc the acceleration at which the player jumps at
	 **/
	public void jump(int acc) {
		if(canJump){
			accelerate(0,-acc);
			moveByVelocities();
			jumps++;
		}
		if(jumps >= 1) {
			canJump = false;
		}
	}

	/**
	 * Makes the current player object fall
	 * @amount the amount the player falls 
         **/
	public void fall(float amount) {
		if(this.y <120) {
			setVelocity(0,0);
			moveByAmount(0,amount);
		}
		else {
			canJump = true;
			jumps = 0;
		}
	
	}

	/**
	 * Decreases the health by the parameter, health
	 * @param health the amount of health to be decreased
	 **/
	public void decreaseHealth(int health) {
		this.health -= health;
	}

	/**
	 * Sets the health of the player to the health in the parameter
	 * @param health The new health of the player
	 **/
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Gets the health of the player
	 * @return returns the health of the currentPlayer
	 **/
	public int getHealth() {
		return health;
	}

	/**
	 * Draws the player
	 * @param drawer the PApplet object used to draw the player
	 **/
	public void draw(PApplet drawer) {
		a++;
		drawer.image(image[a% image.length], (int) x, (int) y, (int) width, (int) height);
		int rectWidth = 40;
		 drawer.noFill();
		 if(health < 100) {
			 drawer.rect((float)x + 25, (float)y - 11, rectWidth, 10);
		 }
		 if (health < 25)
		  {
		    drawer.fill(255, 0, 0);
		  }  
		  else if (health < 50)
		  {
		    drawer.fill(255, 200, 0);
		  }
		  else if(health < 100)
		  {
		    drawer.fill(0, 255, 0);
		  }
		 if(health < 100)
		 drawer.rect((float)x + 25, (float)y-11,drawer.map(health,0,40,0,16) , 10);
		 drawer.fill(0,0,0);
		
	}
}
                      
