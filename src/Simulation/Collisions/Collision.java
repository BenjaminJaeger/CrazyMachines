package Simulation.Collisions;

import Simulation.Util;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.StaticObjects.StaticObject;
import Simulation.RenderEngine.Core.Math.Vector2f;

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
	
	public static void elasticCollision( MoveableObject object1 , StaticObject object2) {	
		float angle = (float)Math.atan2(object2.getY()-object1.getY(), object2.getX()-object1.getX());
		
		float m1 = object1.getMass();
		float m2 = 999999;
		
		Vector2f u1 = Util.rotate(object1.getVelocityX(),object1.getVelocityY(),-angle);
		
		float v1x = (u1.x * (m1 - m2) ) / (m1+m2);
		float v1y = (u1.y * (m1 - m2) ) / (m1+m2);
		
		Vector2f finalv1 = Util.rotate(v1x,v1y,-angle);
		
		object1.setVelocityX(finalv1.x);
		object1.setVelocityY(finalv1.y);
	}
	
	
	public static void removeCollision(MoveableObject object1, StaticObject object2) {	
		DynamicCollisionContext context1 = (DynamicCollisionContext) object1.getCollisionContext();
		CollisionContext context2 = object2.getCollisionContext();
		
		do {	
			
			float distance = Util.getDistance(object2.getX(), object2.getY(), object1.getX(), object1.getY());
			
			float overlap = 0.1f;
			
			float object1X = object1.getX() + overlap*(object1.getX()-object2.getX())/distance;
			float object1Y = object1.getY() + overlap*(object1.getY()-object2.getY())/distance;
					
			object1.setX(object1X);
			object1.setY(object1Y);		
			
		} while(context1.checkCollision(context2));
	
	}

	public static void removeCollision(MoveableObject object1, MoveableObject object2) {	
		DynamicCollisionContext context1 = (DynamicCollisionContext) object1.getCollisionContext();
		DynamicCollisionContext context2 = (DynamicCollisionContext) object2.getCollisionContext();
		
		do {		
			float distance = Util.getDistance(object2.getX(), object2.getY(), object1.getX(), object1.getY());
			
			float overlap = 0.1f;
			
			float object1X = object1.getX()+overlap*(object1.getX()-object2.getX())/distance;
			float object1Y = object1.getY()+overlap*(object1.getY()-object2.getY())/distance;
			
			float object2X = object2.getX()-overlap*(object1.getX()-object2.getX())/distance;
			float object2Y = object2.getY()-overlap*(object1.getY()-object2.getY())/distance;
			
			object1.setX(object1X);
			object1.setY(object1Y);
			
			object2.setX(object2X);
			object2.setY(object2Y);
			
		} while(context1.checkCollision(context2));
		
	}
	
//	public static void removeCollision(Ball object1, Ball object2) {
//		//distance between both balls
//		float distance = Util.getDistance(object2.getX(), object2.getY(), object1.getX(), object1.getY());
//			
//		//the overlap of both balls = distance - radius1 - radius2
//		//move each ball away by half of the overlap 
//		float overlap = 0.5f*(distance-object1.getRadius()-object2.getRadius());
//					
//		//get the distance between each ball and divide it by the distance to get the normalized vector
//		//move along the normalized vector by half the overlap and subtract/add it from the original position
//		
//		float object1X = object1.getX()-overlap*(object1.getX()-object2.getX())/distance;
//		float object1Y = object1.getY()-overlap*(object1.getY()-object2.getY())/distance;
//		
//		float object2X = object2.getX()+overlap*(object1.getX()-object2.getX())/distance;
//		float object2Y = object2.getY()+overlap*(object1.getY()-object2.getY())/distance;
//				
//		object2.setX(object2X);
//		object2.setY(object2Y);
//		
//		object1.setX(object1X);
//		object1.setY(object1Y);
//	}
	
}