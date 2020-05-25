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
import Weapons.*;

import Weapon.*;

import enemy.PatrolManJohn;
import processing.core.PApplet;
import processing.core.PImage;
import states.GameOverScreen;
import states.MenuScreen;
import states.WinnerScreen;
import javax.swing.*;
import sun.audio.*;
import java.io.*;



public class DrawingSurface extends PApplet  {

	public static final int WIDTH= 800;
	public static final int HEIGHT = 600;
	

	private Player player;
	private PImage [] running;
	private PImage [] jumping;
	private PImage [] standing;
	private PImage [] gliding;
	private PImage[] patrolManJohn;
	private PImage johnsHead;
	private PImage[] john;
	private int count = 0;
	private PImage[] trash;
	private PImage[] crate;
	private PImage[] guard;
	private PImage gun;
	private PImage bullet;
	private Gun weapon;
	private Bullet b;
	private ArrayList<Obstacles> obstacles;
	private CollisionDetection c;
	private PImage[] background;
	private GameOverScreen g;
	private PatrolManJohn boss1;
	private int score;
	private boolean released;
	private boolean gunCondition;
	private boolean enemyGunCondition;
	private float backgroundX;
	private int backgroundY;
	private float background2X;
	private float background3X;
	private PImage theBackground;
	private PImage theBackground2;
	private PImage theBackground3;
	private boolean a;
	private boolean d;
	private Bullet b1;
	private Bullet b2;
	private Bullet b3;
	private int prevHealth;
	private int prevBossHealth;
	private WinnerScreen w;
	private boolean glidingCondition;
	private ArrayList<Obstacles> obstaclesSet1 = new ArrayList<Obstacles>();
	private ArrayList<Obstacles> obstaclesSet2 = new ArrayList<Obstacles>();
	private ArrayList<Obstacles> obstaclesSet3 = new ArrayList<Obstacles>();
	private PImage[] sawblade;
	private int johnX;
	private int johnHeadX;
	private int bossX;
	MenuScreen menu = new MenuScreen();
	boolean firstShot;

	
	public static enum STATE{
		Menu,Normal,Boss,GameOver,Winner
	};
	public static STATE State = STATE.Menu;
	/**
	 * creates a drawing surface object
	 */
	public DrawingSurface() {
		super();
		running = new PImage[9];
		jumping = new PImage[1];
		standing = new PImage[1];
		gliding = new PImage[1];
		john = new PImage[21];
		patrolManJohn = new PImage[11];
		background = new PImage[20];
		sawblade = new PImage[5];
		trash = new PImage[1];
		crate = new PImage[1];
		guard = new PImage[1];
		obstacles = new ArrayList<Obstacles>();
		count = 0;
		score = 0;
		backgroundX = 0;
		background2X = 150;
		background3X = 300;
		gunCondition = false;
		g = new GameOverScreen(score);
		released = true;
		w = new WinnerScreen();
		c = new CollisionDetection();
		a = true;
		d = true;
		prevHealth = 0;
		prevBossHealth = 100;
		johnX = 325;
		johnHeadX = 355;
		glidingCondition = false;
		bossX = 280;
		firstShot = false;
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
		b1 = new Bullet(bullet,width,height);
		b2 = new Bullet(bullet,width,height);
		b3 = new Bullet(bullet,width,height);		
	}
	/**
	 * spawns patrolman John
	 */
	public PatrolManJohn spawnNewPatrolManJohn() {
		if(boss1!= null) {
			prevBossHealth = boss1.getHealth();
		}
		boss1 = new PatrolManJohn(patrolManJohn,bossX,130);
		boss1.setHealth(prevBossHealth);
		return boss1;
	}
	/**
	 * returns a player thats jumping
	 * @return  player object
	 */
	public Player jumper(){
		prevHealth = player.getHealth();
		Player p = new Player(jumping,0,125);
		p.setHealth(prevHealth);
		return p;
	}
	/**
	 * returns a player thats running
	 * @return player object
	 */
	public Player runner() {
		prevHealth = player.getHealth();
		Player p =  new Player(running,(int)(player.getX()),(int)(player.getY()));
		p.setHealth(prevHealth);
		return p;
	}
	/**
	 * returns a player thats gliding
	 * @return player object
	 */
	public Player glider() {
		prevHealth = player.getHealth();
		Player p =  new Player(gliding,(int)(player.getX()),(int)(player.getY()));
		p.setHealth(prevHealth);
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
	public Obstacles guard(int x,int y) {
		Obstacles o = new Obstacles(guard,x,y,40,60);
		return o;
	}
	public Obstacles sawblade(int x, int y) {
		Obstacles o = new Obstacles(sawblade,x,y,40,60);
		return o;
	}
	public int getScore() {
		return score;
	}
	
	

	public void setup() {
		running[0] = loadImage("RunGif/Run1.gif");
		for(int i = 0; i < john.length; i++) {
			john[i] = loadImage("JohnGif/John" + i + ".jpg");
		}
		for(int i = 1; i < running.length; i++) {
			int a = i+1;
			running[i] = loadImage("RunGif/Run" + a + ".gif");
		}
		for(int i = 0; i < patrolManJohn.length; i++) {
			patrolManJohn[i] = loadImage("TruckGif/Car" + i + ".jpg");
		}
		for(int i = 0; i < background.length; i++) {
			background[i] = loadImage("BackgroundGIF/Background" + i + ".gif");
		}
		for(int i = 0; i < sawblade.length; i++) {
			sawblade[i] = loadImage("Sawblade/Sawblade" + i + ".gif");
		}
		trash[0] = loadImage("Trash.png");
		crate[0] = loadImage("Crate.png");
		gun = loadImage("Gun.png");
		bullet = loadImage("Bullet.png");
		jumping[0] = loadImage("RunGif/Run2.gif");
		standing[0] = loadImage("StickFigure.png");
		gliding[0] = loadImage("Gliding.png");
		johnsHead = loadImage("Head.png");
		guard[0] = loadImage("Guard.png");
		spawnNewPlayer();
		theBackground = loadImage("background.png");
		theBackground2 = loadImage("background2.png");
		theBackground3 = loadImage("background.png");
		spawnNewBullet();
		obstacles.add(trashCan(250,130));
		obstacles.add(crate(350,130));
		obstacles.add(guard(400,130));
		
		obstaclesSet1.add(trashCan(250,130));
		obstaclesSet1.add(trashCan(280,130));
		obstaclesSet1.add(trashCan(310,130));
		obstaclesSet1.add(trashCan(340,130));
		obstaclesSet2.add(crate(500,130));
		obstaclesSet2.add(sawblade(500,0));
		obstaclesSet3.add(crate(1250,130));
		obstaclesSet3.add(guard(1290,130));
		playMusic("BackgroundMusic.wav");
	}
	public void keyPressed() {
		
		 if(keyCode == UP) {
			 count++;
			 
			 if(count > 1 ) {
				 player = glider();
				 glidingCondition = true;
				 
			 }
			 else {
				player = jumper();
				if(State == STATE.Normal) {
					player.jump(100);
				}
				else player.jump(60);
				
			 }
			
		 } 
		 if(keyCode == 'E' && State == State.Menu) {
			 State = State.Normal;
		 }
		 if(keyCode == 'E' && State == STATE.GameOver) {
			 	player.setHealth(100);
			 	score = 0;
			 	for(int j = 0; j < obstaclesSet1.size(); j++) {
					obstaclesSet1.set(j, trashCan(250 + j * 30, 130));
				}
			 	obstaclesSet2.set(0, crate(500,130));
				obstaclesSet2.set(1, sawblade(500,0));
				obstaclesSet3.set(0, crate(1250,130));
				obstaclesSet3.set(1,guard(1290,130));
				State = STATE.Normal;
			
			}
		if(keyCode == 'E' && State == STATE.Winner) {
			score += 5;
		 	for(int j = 0; j < obstaclesSet1.size(); j++) {
				obstaclesSet1.set(j, trashCan(250 + j * 30, 130));
			}
		 	obstaclesSet2.set(0, crate(500,130));
			obstaclesSet2.set(1, sawblade(500,0));
			obstaclesSet3.set(0, crate(1250,130));
			obstaclesSet3.set(1,guard(1290,130));
			State = STATE.Normal;
		}
		 if(keyCode == 32) {
			 gunCondition = true;
			 if(b.getX() < 190) {
				 playMusic("GunFire.wav");
			 }
			
			 System.out.println(background3X);
		 }
		 
	}

	public void keyReleased() {
		if(keyCode == UP) {
			player = runner();
			glidingCondition = false;
			
		}
		if(keyCode == 32) {
			gunCondition = false;
		}
	
			
	}
	public void playMusic(String filepath) {
		InputStream music;
		try {
			music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		}
		catch(Exception e) {
			System.out.println("Pepega");
		}
	}
	

	public void draw() {
		if(player.getY() >= 118	) {
			count = 0;
		 }
		weapon = gun((int)player.getX() + 50,(int)player.getY() + 5);
		background(255,255,255);   
		pushMatrix();
		float ratioX = (float)width/WIDTH * 3.4f;
		float ratioY = (float)height/HEIGHT * 3.2f;
		scale(ratioX,ratioY);
		if(player.getHealth() <= 0) {
			State = STATE.GameOver;
		}
		if(State == State.Menu) {
			image(theBackground,backgroundX,backgroundY,150,200);
			image(theBackground2,background2X,backgroundY,150,202);
		    image(theBackground3,background3X,backgroundY ,150,200);
			backgroundX -= 1;
			background2X -=1;
			background3X -= 1;
			if(background3X < 110 && a && d) {
				backgroundX = 240;
				a = false;
				d = false;
			}
			if(background3X  < -40 && a == false) {
				background2X = 238;
				a = true;
			}
			if(background3X < -190 && a && d == false) {
				background3X = 238;
			d = true;
			}
			menu.draw(this);
		}
		if(State == STATE.Normal) {
			
			//System.out.println(frameRate);
			for(int i = 0; i < obstaclesSet1.size(); i++) {
				if(c.obstacleCollision(player,obstaclesSet1.get(i))) {
					player.decreaseHealth(45);
					obstaclesSet1.set(i, trashCan(15000,15000));
				}
			}
			for(int i = 0; i < obstaclesSet1.size(); i++) {
				if(obstaclesSet1.get(i).getX() < - 415) {
					for(int j = 0; j < obstaclesSet1.size(); j++) {
						obstaclesSet1.set(j, trashCan(250 + j * 30, 130));
					}
				}
			}
			for(int i = 0; i < obstaclesSet2.size(); i++) {
				if(c.obstacleCollision(player,obstaclesSet2.get(i))) {
					player.decreaseHealth(45);
					obstaclesSet2.set(i, trashCan(15000,15000));
				}
			}
			for(int i = 0; i < obstaclesSet2.size(); i++) {
				if(obstaclesSet2.get(i).getX() < - 315) {
					obstaclesSet2.set(0, crate(500,130));
					obstaclesSet2.set(1, sawblade(500,0));
					
				}
			}
			for(int i = 0; i < obstaclesSet2.size(); i++) {
				if(c.obstacleCollision(player,obstaclesSet3.get(i))) {
					player.decreaseHealth(45);
					obstaclesSet3.set(i, trashCan(15000,15000));
				}
			}
			for(int i = 0; i < obstaclesSet3.size(); i++) {
				if(obstaclesSet3.get(i).getX() < - 115) {
					obstaclesSet3.set(0, crate(1000,130));
					obstaclesSet3.set(1, guard(1000,130));
					
				}
			}
			image(theBackground,backgroundX,backgroundY,150,200);
			image(theBackground2,background2X,backgroundY,150,202);
		    image(theBackground3,background3X,backgroundY ,150,200);
			backgroundX -= 1;
			background2X -=1;
			background3X -= 1;
			if(background3X < 110 && a && d) {
				backgroundX = 240;
				a = false;
				d = false;
			}
			if(background3X  < -40 && a == false) {
				background2X = 238;
				a = true;
			}
			if(background3X < -190 && a && d == false) {
				background3X = 238;
			d = true;
			}
			
			player.draw(this);
			text("score:" + score/5, 0,10);
			if(glidingCondition == false) {
				player.fall(2.3f);
			}
			else {
				player.fall(1);
			}
			
			for(int i = 0; i < obstaclesSet1.size(); i++) {
				obstaclesSet1.get(i).draw(this);
				obstaclesSet1.get(i).move();
			}
			for(int i = 0; i < obstaclesSet2.size(); i++) {
				obstaclesSet2.get(i).draw(this);
				obstaclesSet2.get(i).move();
			}
			for(int i = 0; i < obstaclesSet3.size(); i++) {
				obstaclesSet3.get(i).draw(this);
				obstaclesSet3.get(i).move();
			}
			if(gunCondition) {
				weapon.draw(this);
				b.setStartLocation((int) player.getX() + 43, (int)(player.getY() + 10),0);
			}
			b.update();
			b.draw(this);
			
			
			for(int i = 0; i < obstaclesSet1.size(); i++) {
				if(c.checkObstacleBulletCollision(b, obstaclesSet1.get(i))) {
					obstaclesSet1.get(i).subtractHealth(20, this);
					if(obstaclesSet1.get(i).getHealth() <= 0) {
						obstaclesSet1.set(i, trashCan(15000,15000));
					}
					b.reset();
				}
				
			}
			for(int i = 0; i < obstaclesSet2.size(); i++) {
				if(c.checkObstacleBulletCollision(b, obstaclesSet2.get(i))) {
					obstaclesSet2.get(i).subtractHealth(20, this);
					if(obstaclesSet2.get(i).getHealth() <= 0) {
						obstaclesSet2.set(i, trashCan(15000,15000));
					}
					b.reset();
				}
				
			}
			for(int i = 0; i < obstaclesSet3.size(); i++) {
				if(c.checkObstacleBulletCollision(b, obstaclesSet3.get(i))) {
					obstaclesSet3.get(i).subtractHealth(20, this);
					if(obstaclesSet3.get(i).getHealth() <= 0) {
						obstaclesSet3.set(i, trashCan(15000,15000));
					}
					b.reset();
				}
				
			}
			if(score/5 % 400 == 0 && score > 6) {
				State = STATE.Boss;
			}
	
			else if(score/5 > 405) {
				score += 5;
				for(int i = 0; i < obstaclesSet1.size(); i++) {
					obstaclesSet1.get(i).setDifficulty(3);
				}
				for(int i = 0; i < obstaclesSet2.size(); i++) {
					obstaclesSet2.get(i).setDifficulty(3);
				}
			}
			else if(score/5 >= 250) {
				score += 4;
				for(int i = 0; i < obstaclesSet1.size(); i++) {
					obstaclesSet1.get(i).setDifficulty(2);
				}
				for(int i = 0; i < obstaclesSet2.size(); i++) {
					obstaclesSet2.get(i).setDifficulty(2);
				}
			}
			else if(score/5 > 150) {
				score += 3;
				for(int i = 0; i < obstaclesSet1.size(); i++) {
					obstaclesSet1.get(i).setDifficulty(1);
				}
				for(int i = 0; i < obstaclesSet2.size(); i++) {
					obstaclesSet2.get(i).setDifficulty(1);
				}
			}
			else {
				score += 2;
				for(int i = 0; i < obstaclesSet1.size(); i++) {
					obstaclesSet1.get(i).setDifficulty(0);
				}
				for(int i = 0; i < obstaclesSet2.size(); i++) {
					obstaclesSet2.get(i).setDifficulty(0);
				}
			}
			
			
			
	}
		if(State == STATE.Boss) {			
			image(theBackground,backgroundX,backgroundY,150,200);
			image(theBackground2,background2X,backgroundY,150,202);
			image(theBackground3,background3X,backgroundY ,150,200);
			backgroundX -= 3;
			background2X -= 3;
			background3X -= 3;
			if(background3X < 110 && a && d) {
				backgroundX = 240;
				a = false;
				d = false;
			}
			if(background3X  < -40 && a == false) {
				background2X = 238;
				a = true;
			}
			if(background3X < -190 && a && d == false) {
				background3X = 238;
				d = true;
			}
			image(johnsHead,johnHeadX,130,10,10);
			image(john[frameCount% john.length], (int) johnX, (int) 110, (int) 30, (int) 30);
			if(johnHeadX > 175) {
				johnHeadX -= 3;
			}
			if(johnX > 145) {
				johnX -= 3;
			}
			backgroundX -= 3;
			background2X -= 3;
			background3X -= 3;
			if(background3X < 110 && a && d) {
				backgroundX = 240;
				a = false;
				d = false;
			}
			if(background3X  < -40 && a == false) {
				background2X = 238;
				a = true;
			}
			if(background3X < -190 && a && d == false) {
				background3X = 238;
				d = true;
			}
			player.draw(this);
			player.fall(1.5f);
			text("score:" + score/5, 0,10);
			textSize(15);
			fill(0,0,0);
			fill(255,255,255);
			boss1 = spawnNewPatrolManJohn();
			if(bossX > 107) {
				bossX -= 3;
			}
			if(gunCondition) {
				weapon.draw(this);
				b.setStartLocation((int) player.getX() + 50, (int)(player.getY() + 10),0);
			}
			b.update();
			b.draw(this);
			if(frameCount % 50 == 0 && firstShot) {
				b2.setStartLocation(160, 130, 145* PI/150);
				playMusic("shotgun.wav");
			}
			
			if(frameCount % 100 == 0 && firstShot == false) {
				b2.setStartLocation(160, 130, 145* PI/150);
				playMusic("shotgun.wav");
				firstShot = true;
			}
			if(c.playerBulletCollision(b2, player)) {
				player.decreaseHealth(45);
				b2.reset();
			}
			if(player.getHealth() <= 0) {
				State = STATE.GameOver;
			}
			if(c.johnBulletCollision(b)) {
				boss1.decreaseHealth(5);
				b.reset();
					
			}
			if(boss1.getHealth() <= 0 && score/5 % 400 == 0) {
				State = STATE.Winner;
			}
			boss1.draw(this);
			b2.update();
			b2.draw(this);
			
			
		}
		if(State == STATE.Winner) {
			w.draw(this);
		}
		if(State == STATE.GameOver) {
			g = new GameOverScreen(score/5);
			g.draw(this);
		}
		
		popMatrix();	
		
			
	}



	

	


}

