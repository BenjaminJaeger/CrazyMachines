package Objects.MovableObjects.Collisions;

import Objects.MovableObjects.Ball.Ball;
import Objects.MovableObjects.Box.Box;

public class BallBoxCollision {
	
	public static boolean checkCollision(Box box, Ball ball) {
		float distanceX = (float)Math.abs(ball.getX()-box.getX());
		float distanceY = (float)Math.abs(ball.getY()-box.getY());
		
		if(distanceX > box.getWidth()/2 + ball.getRadius())		return false;	
		if(distanceY > box.getHeight()/2 + ball.getRadius()) 	return false;
		
		if(distanceX <= box.getWidth()/2) 	return true;
		if(distanceY <= box.getHeight()/2) 	return true;
			
		float cornerDistance = (float)Math.pow((distanceX-box.getWidth()/2),2) + (float)Math.pow((distanceY-box.getHeight()/2),2);
		
		return cornerDistance <= ball.getRadius()*ball.getRadius();
	} 

	public static void removeCollision(Box box, Ball ball) {
		box.setVelocityX(-box.getVelocityX());
		ball.setVelocityX(-ball.getVelocityX());
		
		box.setVelocityY(-box.getVelocityY());
		ball.setVelocityY(-ball.getVelocityY());
	}

	public static void calculateNewVelocity(Box box, Ball ball) {
		
	}

	
}
