package Objects.MovableObjects.Collisions;

import Objects.Util;
import Objects.MovableObjects.Ball.Ball;

public interface CircleCollision {
	
	/**
	 * Returns true if both balls collide
	 */
	public static boolean checkCollision(Ball ball1, Ball ball2) {
		//when the radii of both circles combined is smaller than the distance between both balls they collide
		return ball2.getRadius()+ball1.getRadius() > Util.getDistance(ball2.getX(), ball2.getY(), ball1.getX(), ball1.getY());
	}

	/**
	 * Removes collision between the balls to prevent them from stucking inside each other
	 */ 
	public static void removeCollision(Ball ball1, Ball ball2) {
		//distance between both balls
		float distance = Util.getDistance(ball2.getX(), ball2.getY(), ball1.getX(), ball1.getY());
		
		//the overlap of both balls = distance - radius1 - radius2
		//move each ball away by half of the overlap 
		float overlap = 0.5f*(distance-ball1.getRadius()-ball2.getRadius());
		
		//get the distance between each ball and divide it by the distance to get the normalized vector
		//move along the normalized vector by half the overlap and subtract/add it from the original position
		ball1.setX(ball1.getX()-overlap*(ball1.getX()-ball2.getX())/distance);
		ball1.setY(ball1.getY()-overlap*(ball1.getY()-ball2.getY())/distance);
		
		ball2.setX(ball2.getX()+overlap*(ball1.getX()-ball2.getX())/distance);
		ball2.setY(ball2.getY()+overlap*(ball1.getY()-ball2.getY())/distance);
	}
	
	/**
	 * Calculates the new velocity of each ball after the collision
	 */
	public static void calculateNewVelocity(Ball ball1 , Ball ball2) {
		Collision.elasticCollision(ball1, ball2);
	}
	
}