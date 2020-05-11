package CollisionDetection;

import Obstacles.Obstacles;
import Players.Player;
import Weapon.Bullet;

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
}
