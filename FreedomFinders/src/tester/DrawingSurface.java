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
import Weapon.*;
import enemy.PatrolManJohn;
import processing.core.PApplet;
import processing.core.PImage;
import states.GameOverScreen;
import states.WinnerScreen;

public class DrawingSurface extends PApplet  {

	public static final int WIDTH= 800;
	public static final int HEIGHT = 600;
	

	private Player player;
	private PImage [] running;
	private PImage [] jumping;
	private PImage [] standing;
	private PImage [] gliding;
	private PImage[] patrolManJohn;
	private PImage guard;
	private PImage johnsHead;
	private PImage[] john;
	private int count = 0;
	private PImage trash;
	private PImage crate;
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
	
	public static enum STATE{
		Normal,Boss,GameOver,Winner
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
		john = new PImage[21];
		patrolManJohn = new PImage[11];
		background = new PImage[20];
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
		glidingCondition = false;
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
	public void spawnNewPatrolManJohn() {
		if(boss1!= null) {
			prevBossHealth = boss1.getHealth();
		}
		boss1 = new PatrolManJohn(patrolManJohn,120,130);
		boss1.setHealth(prevBossHealth);
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
		trash = loadImage("Trash.png");
		crate = loadImage("Crate.png");
		gun = loadImage("Gun.png");
		bullet = loadImage("Bullet.png");
		jumping[0] = loadImage("RunGif/Run2.gif");
		standing[0] = loadImage("StickFigure.png");
		gliding[0] = loadImage("Gliding.png");
		johnsHead = loadImage("Head.png");
		guard = loadImage("Guard.png");
		spawnNewPlayer();
		theBackground = loadImage("background.png");
		theBackground2 = loadImage("background2.png");
		theBackground3 = loadImage("background.png");
		spawnNewBullet();
		obstacles.add(trashCan(250,130));
		obstacles.add(crate(350,130));
		obstacles.add(guard(400,130));
		
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
		 if(keyCode == 'E' && State == STATE.GameOver) {
			 	player.setHealth(100);
			 	score = 0;
				State = STATE.Normal;
				obstacles.set(2, guard(450,130));
				obstacles.set(0, trashCan(350,130));
				obstacles.set(1, crate(400,130));
			}
		 if(keyCode == 32) {
			 gunCondition = true;
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
		if(player.getHealth() <= 0) {
			State = STATE.GameOver;
		}
		if(score/5 >= 350) {
			State = STATE.Boss;
		}
		if(State == STATE.Normal) {
			if(c.obstacleCollision(player, obstacles.get(0))) {
				player.decreaseHealth(45);
				obstacles.set(0, trashCan(500,130));
			}
			if(c.obstacleCollision(player,obstacles.get(1))) {
				player.decreaseHealth(45);
				obstacles.set(1, crate(600,130));
			}
			if(c.obstacleCollision(player,obstacles.get(2))) {
				player.decreaseHealth(45);
				obstacles.set(1, guard(650,130));
			}
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
			player.draw(this);
			text("score:" + score/5, 0,10);
			if(glidingCondition == false) {
				player.fall(2.3f);
			}
			else {
				player.fall(1);
			}
			
			obstacles.get(0).draw(this);
			obstacles.get(0).move();
			obstacles.get(1).draw(this);
			obstacles.get(1).move();
			obstacles.get(2).draw(this);
			obstacles.get(2).move();
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
			if(obstacles.get(2).getX() < -150) {
				obstacles.set(2, guard(500,130));		
			}
			if(c.checkObstacleBulletCollision(b, obstacles.get(0))){
				obstacles.get(0).subtractHealth(20,this);
				if(obstacles.get(0).getHealth() <= 0) {
					obstacles.set(0, trashCan(500,130));
				}
				b.reset();
			}
			if(c.checkObstacleBulletCollision(b, obstacles.get(1))){
				obstacles.get(1).subtractHealth(20,this);
				if(obstacles.get(1).getHealth() <= 0) {
					obstacles.set(1, crate(600,130));
				}
				b.reset();
			}
			if(c.checkObstacleBulletCollision(b, obstacles.get(2))){
				obstacles.get(2).subtractHealth(50,this);
				if(obstacles.get(2).getHealth() <= 0) {
					obstacles.set(2, guard(600,130));
				}
				b.reset();
			}
			if(score / 5 <= 99 ) {
				obstacles.get(0).setDifficulty(0);
				obstacles.get(1).setDifficulty(0);
				score += 3;                        
			}
			if(score / 5 > 99 && score / 5 <= 200 ) {                               
				obstacles.get(0).setDifficulty(1);
				obstacles.get(1).setDifficulty(1);
				score += 4;
			}
			if(score / 5 > 200 && score / 5 <= 350 ) {
				obstacles.get(0).setDifficulty(2);
				obstacles.get(1).setDifficulty(2);
				score += 5;
			}
			
			if(score / 5 >= 475) {
				obstacles.set(0, trashCan(500,130));
				obstacles.set(1, crate(500,130));
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
			image(johnsHead,190,130,10,10);
			image(john[frameCount% john.length], (int) 160, (int) 110, (int) 30, (int) 30);
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
			spawnNewPatrolManJohn();
			if(gunCondition) {
				weapon.draw(this);
				b.setStartLocation((int) player.getX() + 43, (int)(player.getY() + 10),0);
			}
			b.update();
			b.draw(this);
			if(frameCount % 50 == 0) {
				b2.setStartLocation(160, 130, 145* PI/150);
			}
			if(c.playerBulletCollision(b2, player)) {
				player.decreaseHealth(45);
				b2.reset();
			}
			if(player.getHealth() <= 0) {
				State = STATE.GameOver;
			}
			if(c.johnBulletCollision(b)) {
				boss1.decreaseHealth(15);
				b.reset();
				System.out.println("true");		
			}
			if(boss1.getHealth() <= 0) {
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

