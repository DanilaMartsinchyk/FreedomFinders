package CollisionDetection;

import Obstacles.Obstacles;
import Players.Player;
import Weapons.Bullet;

public class CollisionDetection {
	public CollisionDetection() {

	}
	/**
	 * Checks if a play and an object are intersecting
	 * 
	 * @param p a player object
	 * @param o an obstacle object
	 * @return true if player is touching an obstacle
	 */
	public boolean obstacleCollision(Player p, Obstacles o) {
		double pWidth = (p.getWidth() - 20) / 2;
		double pHeight = (p.getHeight() - 20) / 2;
		double oWidth = (o.getWidth() - 20) / 2;
		double oHeight = (o.getHeight() - 20) / 2;
		double distanceX = (p.getX() + pWidth) - (o.getX() + oWidth);
		double distanceY = (p.getY() + pHeight) - (o.getY() + oHeight);
		double combinedHalfW = pWidth + oWidth;
		double combinedHalfH = pHeight + oHeight;

		if (Math.abs(distanceX) < combinedHalfW) {
			if (Math.abs(distanceY) < combinedHalfH) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a bullet and an obstacle are intersecting
	 * 
	 * @param b a bullet object
	 * @param o an obstacle object
	 * @return true if a bullet touches an obstacle
	 */
	public boolean checkObstacleBulletCollision(Bullet b, Obstacles o) {
		double bWidth = (b.getWidth() - 5) / 2;
		double bHeight = (b.getHeight() - 0) / 2;
		double oWidth = (o.getWidth() - 5) / 2;
		double oHeight = (o.getHeight() - 5) / 2;
		double distanceX = (b.getX() + bWidth) - (o.getX() + oWidth);
		double distanceY = (b.getY() + bHeight) - (o.getY() + oHeight);
		double combinedHalfW = bWidth + oWidth;
		double combinedHalfH = bHeight + oHeight;

		if (Math.abs(distanceX) < combinedHalfW) {
			if (Math.abs(distanceY) < combinedHalfH) {
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the player got hit by a bullet
	 * @param the enemy bullet
	 * @param p the main player
	 * @return true if the bullet touches the player
	 */
	public boolean playerBulletCollision(Bullet b, Player p) {
		double bWidth = (b.getWidth() - 5)/ 2;
		double bHeight = (b.getHeight() - 0)/2;
		double pWidth = (p.getWidth() - 5) / 2;
		double pHeight = (p.getHeight() - 5) /2;
		double distanceX = (b.getX() + bWidth) - (p.getX() + pWidth);
		double distanceY = (b.getY() + bHeight) - (p.getY() + pHeight );
		double combinedHalfW =bWidth + pWidth;
		double combinedHalfH = bHeight + pHeight;
		
		if(Math.abs(distanceX) < combinedHalfW) {
			if(Math.abs(distanceY) < combinedHalfH) {
				return true;
			}
		}
		return false;
	}
	/**
	 * checks if the enemy John gets hit by a bullet
	 * @param b players bullet
	 * @return true if it does hit him false if it does not
	 */
	public boolean johnBulletCollision(Bullet b) {
		if(b.getX() >= 150 && b.getY() < 225 && b.getY() > 115) {
			return true;
		}
		return false;
	}
}
