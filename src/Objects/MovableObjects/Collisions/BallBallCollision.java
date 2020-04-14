package Objects.MovableObjects.Collisions;

import Objects.Util;
import Objects.MovableObjects.Ball.Ball;

public class BallBallCollision {
	
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
	 * The collision is elastic and no energy gets lost due to friction
	 */
	public static void calculateNewVelocity(Ball ball1 , Ball ball2) {
		float distance =  Util.getDistance(ball2.getX(), ball2.getY(), ball1.getX(), ball1.getY());

		float nx = (ball1.getX()-ball2.getX())/distance;
		float ny = (ball1.getY()-ball2.getY())/distance;
		
		float tx = -ny;
		float ty = nx;
	
		float dpTan1 = ball2.getVelocityX()*tx + ball2.getVelocityY()*ty;
		float dpTan2 = ball1.getVelocityX()*tx + ball1.getVelocityY()*ty;
		
		float dpNorm1 = ball2.getVelocityX() *nx + ball2.getVelocityY() * ny;
		float dpNorm2 = ball1.getVelocityX() *nx + ball1.getVelocityY() * ny;
		
		float m1 = (dpNorm1 * (ball2.getMass() - ball1.getMass()) + 2 * ball1.getMass() * dpNorm2) / (ball2.getMass() + ball1.getMass());
		float m2 = (dpNorm2 * (ball1.getMass() - ball2.getMass()) + 2 * ball2.getMass() * dpNorm1) / (ball2.getMass() + ball1.getMass());
		
		ball2.setVelocityX(tx*dpTan1 + nx*m1);
		ball2.setVelocityY(ty*dpTan1 + ny*m1);
		
		ball1.setVelocityX(tx * dpTan2 + nx * m2);
		ball1.setVelocityY(ty * dpTan2 + ny * m2);
	}
	
	
}
