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
	public Player(PImage[] i, int x, int y) {
		super(i,x,y,STICK_FIGURE_LENGTH,STICK_FIGURE_HEIGHT);
		jumps = 0;
		image = i;
		canJump = true;
		delay = 0;
		health = 100;
		maxHealth = 100;
	}
	
	
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
	public void decreaseHealth(int health) {
		this.health -= health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
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
                      
