package tester;



import java.awt.Rectangle.*;


import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import CollisionDetection.CollisionDetection;
import Obstacles.Obstacles;
import Players.Player;
import Weapons.*;
import processing.core.PApplet;
import processing.core.PImage;
import states.GameOverScreen;

public class DrawingSurface extends PApplet  {

	public static final int WIDTH= 800;
	public static final int HEIGHT = 600;
	

	private Player player;
	private PImage [] running;
	private PImage [] jumping;
	private PImage [] standing;
	private PImage [] gliding;
	private int count = 0;
	private PImage trash;
	private PImage crate;
	private PImage gun;
	private PImage bullet;
	private Gun weapon;
	private Bullet b;
	private ArrayList<Obstacles> obstacles;
	private CollisionDetection c;
	GameOverScreen g;
	private float score;
	private boolean released;
	private boolean gunCondition;
	private int a = 0;

	
	public static enum STATE{
		Normal,Boss,GameOver
	};
	public static STATE State = STATE.Normal;
	/**
	 * creates a drawing surface object
	 */
	public DrawingSurface() {
		super();
		running = new PImage[9];
		jumping = new PImage[1];
		standing = new PImage[1];
		gliding = new PImage[1];
		obstacles = new ArrayList<Obstacles>();
		count = 0;
		score = 0;
		gunCondition = false;
		g = new GameOverScreen();
		released = true;
		c = new CollisionDetection();
	}

	/**
	 * spawns a new player object
	 */
	public void spawnNewPlayer() {		
		player= new Player(running,0,125);		
	}
	/**
	 * spawns a new bullet object
	 */
	public void spawnNewBullet() {
		b = new Bullet(bullet,width,height);
	}
	/**
	 * returns a player thats jumping
	 * @return  player object
	 */
	public Player jumper(){
		Player p = new Player(jumping,0,125);
		return p;
	}
	/**
	 * returns a player thats running
	 * @return player object
	 */
	public Player runner() {
		Player p =  new Player(running,(int)(player.getX()),(int)(player.getY()));
		return p;
	}
	/**
	 * returns a player thats gliding
	 * @return player object
	 */
	public Player glider() {
		Player p =  new Player(gliding,(int)(player.getX()),(int)(player.getY()));
		return p;
	}
	/**
	 * returns a obstacle object thats a trash can
	 * @param x x coordinate of the trash can
	 * @param y y coordinate of the trash can
	 * @return  obstacle objeect
	 */
	public Obstacles trashCan(int x,int y) {
		Obstacles o = new Obstacles(trash,x,y,40,60);
		return o;
	}
	/**
	 * returns a obstacle object thats a crate
	 * @param x x coordinate of the crate
	 * @param y y coordinate of the crate
	 * @return obstacle object
	 */
	public Obstacles crate(int x, int y) {
		Obstacles o = new Obstacles(crate,x,y,80,60);
		return o;
	}
	/**
	 * returns a gun object
	 * @param x x coordinate of the gun
	 * @param y y coordinate of the gun
	 * @return a gun object
	 */
	public Gun gun(int x, int y) {
		Gun g = new Gun(gun,x,y,10,10);
		return g;
	}
	
	
	

	public void setup() {
		running[0] = loadImage("Run1.gif");
		for(int i = 1; i < 9; i++) {
			int a = i+1;
			running[i] = loadImage("Run" + a + ".gif");
		}
		trash = loadImage("Trash.png");
		crate = loadImage("Crate.png");
		gun = loadImage("Gun.png");
		bullet = loadImage("Bullet.png");
		jumping[0] = loadImage("Run2.gif");
		standing[0] = loadImage("StickFigure.png");
		gliding[0] = loadImage("Gliding.png");
		spawnNewPlayer();
		spawnNewBullet();
		obstacles.add(trashCan(250,130));
		obstacles.add(crate(350,130));
		
	}
	public void keyPressed() {
		
		 if(keyCode == UP) {
			 count++;
			 
			 if(count > 1 ) {
				 player = glider();
				 
			 }
			 else {
				player = jumper();
				player.jump();
				
			 }
			
		 } 
		 if(keyCode == 'E' && State == STATE.GameOver) {
			 	
				State = STATE.Normal;
				obstacles.set(0, trashCan(350,130));
				obstacles.set(1, crate(400,130));
			}
		 if(keyCode == 32) {
			 gunCondition = true;
		 }
		 
	}

	public void keyReleased() {
		if(keyCode == UP) {
			player = runner();
			
		}
		if(keyCode == 32) {
			gunCondition = false;
		}
	
			
	}
	

	public void draw() {
		if(player.getY() >= 118	) {
			count = 0;
		 }
		weapon = gun((int)player.getX() + 43,(int)player.getY() + 5);
		background(255,255,255);   
		pushMatrix();
		
		float ratioX = (float)width/WIDTH * 3;
		float ratioY = (float)height/HEIGHT * 3;
		scale(ratioX,ratioY);
		if(c.obstacleCollision(player, obstacles.get(0)) || c.obstacleCollision(player, obstacles.get(1))) {
			State = STATE.GameOver;
		}
		if(score/5 >= 500) {
			State = STATE.Boss;
		}
		if(State == STATE.Normal) {
			player.draw(this);
			line(0,150,500,150);
			text("score:" + score/5, 0,10);
			player.fall();
			obstacles.get(0).draw(this);
			obstacles.get(0).move();
			obstacles.get(1).draw(this);
			obstacles.get(1).move();
			if(gunCondition) {
				weapon.draw(this);
				b.setStartLocation((int) player.getX() + 43, (int)(player.getY() + 10),0);
			}
			b.update();
			b.draw(this);
			if(obstacles.get(0).getX() < -50) {
				obstacles.set(0, trashCan(350,130));
			}
			if(obstacles.get(1).getX() < -100) {
				obstacles.set(1, crate(450,130));		
			}
			if(c.checkObstacleBulletCollision(b, obstacles.get(0))){
				obstacles.get(0).subtractHealth(50);
				if(obstacles.get(0).getHealth() == 0) {
					obstacles.set(0, trashCan(500,130));
				}
				b.reset();
			}
			if(c.checkObstacleBulletCollision(b, obstacles.get(1))){
				obstacles.get(1).subtractHealth(50);
				if(obstacles.get(1).getHealth() == 0) {
					obstacles.set(1, crate(600,130));
				}
				b.reset();
			}
			if(score / 5 <= 99 ) {
				obstacles.get(0).setDifficulty(0);
				obstacles.get(1).setDifficulty(0);
				score += 1;
			}
			if(score / 5 > 99 && score / 5 <= 200 ) {
				obstacles.get(0).setDifficulty(1);
				obstacles.get(1).setDifficulty(1);
				score += 2;
			}
			if(score / 5 > 200 && score / 5 <= 500 ) {
				obstacles.get(0).setDifficulty(2);
				obstacles.get(1).setDifficulty(2);
				score += 3;
			}
			
			if(score / 5 >= 475) {
				obstacles.set(0, trashCan(500,130));
				obstacles.set(1, crate(500,130));
			}
	}
		if(State == STATE.Boss) {
			player.draw(this);
			text("score:" + score/5, 0,10);
			textSize(15);
			fill(0,0,0);
			text("BOSS FIGHT NOT IMPLEMENTED YET",0,50);
			fill(255,255,255);
			
			
			
		}
		if(State == STATE.GameOver) {
			score = 0;
			g.draw(this);
		}
		
		popMatrix();	
		
			
	}



	

	


}

