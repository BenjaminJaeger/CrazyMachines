package Collisions;

import Objects.Util;
import Objects.ImmovableObjects.ImmovableObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;
import RenderEngine.Core.Math.Vector2f;

public class Collision {

	public static void elasticCollision(MoveableObject object1, MoveableObject object2) {	
		float angle = (float)Math.atan2(object2.getY()-object1.getY(), object2.getX()-object1.getX());
		
		float m1 = object1.getMass();
		float m2 = object2.getMass();
		
		Vector2f u1 = Util.rotate(object1.getVelocityX(),object1.getVelocityY(),-angle);
		Vector2f u2 = Util.rotate(object2.getVelocityX(),object2.getVelocityY(),-angle);
		
		float v1x = (u1.x * (m1 - m2) + 2 * m2 * u2.x) / (m1+m2);
		float v1y = (u1.y * (m1 - m2) + 2 * m2 * u2.y) / (m1+m2);
		float v2x = (u2.x * (m2 - m1) + 2 * m1 * u1.x) / (m1+m2);
		float v2y = (u2.y * (m2 - m1) + 2 * m1 * u1.y) / (m1+m2);
		
		Vector2f finalv1 = Util.rotate(v1x,v1y,angle);
		Vector2f finalv2 = Util.rotate(v2x,v2y,angle);
		
		object1.setVelocityX(finalv1.x);
		object1.setVelocityY(finalv1.y);
		object2.setVelocityX(finalv2.x);
		object2.setVelocityY(finalv2.y);	
	}
	
	public static void elasticCollisionStaticObject(MoveableObject object1, ImmovableObject object2) {	
		float angle = (float)Math.atan2(object2.getY()-object1.getY(), object2.getX()-object1.getX());
		
		float m1 = object1.getMass();
		float m2 = object2.getMass();
		
		Vector2f u1 = Util.rotate(object1.getVelocityX(),object1.getVelocityY(),-angle);
		Vector2f u2 = Util.rotate(object2.getVelocityX(),object2.getVelocityY(),-angle);
		
		float v1x = (u1.x * (m1 - m2) + 2 * m2 * u2.x) / (m1+m2);
		float v1y = (u1.y * (m1 - m2) + 2 * m2 * u2.y) / (m1+m2);
		float v2x = (u2.x * (m2 - m1) + 2 * m1 * u1.x) / (m1+m2);
		float v2y = (u2.y * (m2 - m1) + 2 * m1 * u1.y) / (m1+m2);
		
		Vector2f finalv1 = Util.rotate(v1x,v1y,angle);
		Vector2f finalv2 = Util.rotate(v2x,v2y,angle);
		
		object1.setVelocityX(finalv1.x);
		object1.setVelocityY(finalv1.y);
		object2.setVelocityX(finalv2.x);
		object2.setVelocityY(finalv2.y);	
	}
	
	public static void removeCollision(Ball object1, Ball object2) {
		//distance between both balls
		float distance = Util.getDistance(object2.getX(), object2.getY(), object1.getX(), object1.getY());
			
		//the overlap of both balls = distance - radius1 - radius2
		//move each ball away by half of the overlap 
		float overlap = 0.5f*(distance-object1.getRadius()-object2.getRadius());
			
		//get the distance between each ball and divide it by the distance to get the normalized vector
		//move along the normalized vector by half the overlap and subtract/add it from the original position
		object1.setX(object1.getX()-overlap*(object1.getX()-object2.getX())/distance);
		object1.setY(object1.getY()-overlap*(object1.getY()-object2.getY())/distance);
			
		object2.setX(object2.getX()+overlap*(object1.getX()-object2.getX())/distance);
		object2.setY(object2.getY()+overlap*(object1.getY()-object2.getY())/distance);
	}
	
	public static void removeCollision(CollisionContext context1, CollisionContext context2) {
		
	}
	
}