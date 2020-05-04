package tester;



import java.awt.Rectangle.*;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Obstacles.Obstacles;
import Players.Player;
import processing.core.PApplet;
import processing.core.PImage;
import states.GameOverScreen;

public class DrawingSurface extends PApplet {

	public static final int WIDTH= 800;
	public static final int HEIGHT = 600;
	

	private Player player;
	private PImage [] running;
	private PImage [] jumping;
	private PImage [] standing;
	private int count = 0;
	private PImage trash;
	private PImage spike;
	private ArrayList<Obstacles> obstacles;
	GameOverScreen g;
	private float score = 0;
	private boolean released;
	private int a = 0;

	public static enum STATE{
		Normal,Boss,GameOver
	};
	public static STATE State = STATE.Normal;

	public DrawingSurface() {
		super();
		running = new PImage[9];
		jumping = new PImage[1];
		standing = new PImage[1];
		obstacles = new ArrayList<Obstacles>();
		count = 0;
		score = 0;
		g = new GameOverScreen();
		released = true;
	}


	public void spawnNewPlayer() {		
		player= new Player(running,0,125);		
	}
	public Player standing() {
		Player p =  new Player(standing,(int)(player.getX()),(int)(player.getY()));
		return p;
		
	}
	public Player jumper(){
		Player p = new Player(jumping,0,125);
		return p;
	}
	public Player runner() {
		Player p =  new Player(running,(int)(player.getX()),(int)(player.getY()));
		return p;
	}
	
	public Obstacles trashCan(int x,int y) {
		Obstacles o = new Obstacles(trash,x,y,40,60);
		return o;
	}
	public Obstacles spike(int x, int y) {
		Obstacles o = new Obstacles(spike,x,y,60,60);
		return o;
	}
	

	public void setup() {
		running[0] = loadImage("Run1.gif");
		for(int i = 1; i < 9; i++) {
			int a = i+1;
			running[i] = loadImage("Run" + a + ".gif");
		}
		trash = loadImage("Trash.png");
		spike = loadImage("Spike.png");
		jumping[0] = loadImage("Run2.gif");
		standing[0] = loadImage("StickFigure.png");
		spawnNewPlayer();
		obstacles.add(trashCan(250,130));
		obstacles.add(spike(350,130));
		
	}
	public void keyPressed() {
		 if(keyCode == UP && State == STATE.Normal) {
			 count++;
			 if(count >= 2) {
				 
			 }
			 else {
				player = jumper();
				player.jump();
			 }
		 } 
		 if(keyCode == 'E') {
			 	System.out.println("E");
				State = STATE.Normal;
				obstacles.set(0, trashCan(350,130));
				obstacles.set(1, spike(400,130));
			}
		 if(keyCode == LEFT && State == STATE.Boss) {
			 player = runner();
			 player.move(-1);
			 released = false;
			 a++;
			 System.out.println(a);
			
		 }
		 if(keyCode == RIGHT && State == STATE.Boss) {
			 player = runner();
			 player.move(1);
			 released = false;
			 
		 }
		 
	}
	public void mouseClicked() {
		System.out.println(mouseY);
	}
	public void keyReleased() {
		if(keyCode == UP && State == STATE.Normal) {
			player = runner();
			
		}
		if(player.y <= 120) {
			count = 0;
		}
		if(keyCode == RIGHT && State == STATE.Boss) {
			player = standing();
		}
		if(keyCode == LEFT && State == STATE.Boss) {
			player = standing();
		}
	
			
	}
	

	public void draw() {
		background(255,255,255);   
		pushMatrix();
		float ratioX = (float)width/WIDTH * 3;
		float ratioY = (float)height/HEIGHT * 3;
		scale(ratioX,ratioY);
		score++;
		if(player.act(obstacles)) {
			State = STATE.GameOver;
		}
		if(score/5 >= 200) {
			State = STATE.Boss;
		}
		if(State == STATE.Normal) {
			player.draw(this);
			text("score:" + score/5, 0,10);
			player.fall();
			obstacles.get(0).draw(this);
			obstacles.get(0).move();
			obstacles.get(1).draw(this);
			obstacles.get(1).move();
			if(obstacles.get(0).getX() < -50) {
				obstacles.set(0, trashCan(350,130));
			}
			if(obstacles.get(1).getX() < -100) {
				obstacles.set(1, spike(450,130));
			}
			if(score / 5 >= 175) {
				obstacles.set(0, trashCan(500,130));
				obstacles.set(1, spike(500,130));
			}
	}
		if(State == STATE.Boss) {
			if(released) {
				player = standing();
			}
			textSize(15);
			fill(0,0,0);
			text("BOSS FIGHT NOT IMPLEMENTED YET",0,50);
			fill(255,255,255);
			
			
			player.draw(this);
		}
		if(State == STATE.GameOver) {
			score = 0;
			g.draw(this);
		}
		popMatrix();	
		
			
	}


	


}

