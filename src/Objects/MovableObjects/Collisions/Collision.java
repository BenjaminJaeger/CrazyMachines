package Objects.MovableObjects.Collisions;

import Engine.Core.Math.Vector2f;
import Objects.Util;
import Objects.MovableObjects.MoveableObject;

public class Collision {

	public static void elasticCollision(MoveableObject object1, MoveableObject object2) {	
		float angle = (float)Math.atan2(object2.getY()-object1.getY(), object2.getX()-object1.getX());
		angle *=-1;
		
		float m1 = object1.getMass();
		float m2 = object2.getMass();
		
		Vector2f u1 = Util.rotate(object1.getVelocityX(),object1.getVelocityY(),angle);
		Vector2f u2 = Util.rotate(object2.getVelocityX(),object2.getVelocityY(),angle);
		
		float v1x = (u1.x * (m1 - m2) + 2 * m2 * u2.x) / (m1+m2);
		float v1y = (u1.y * (m1 - m2) + 2 * m2 * u2.y) / (m1+m2);
		float v2x = (u2.x * (m2 - m1) + 2 * m1 * u1.x) / (m1+m2);
		float v2y = (u2.y * (m2 - m1) + 2 * m1 * u1.y) / (m1+m2);
		
		Vector2f finalv1 = Util.rotate(v1x,v1y,-angle);
		Vector2f finalv2 = Util.rotate(v2x,v2y,-angle);
		
		object1.setVelocityX(finalv1.x);
		object1.setVelocityY(finalv1.y);
		object2.setVelocityX(finalv2.x);
		object2.setVelocityY(finalv2.y);	
	}
	
}
