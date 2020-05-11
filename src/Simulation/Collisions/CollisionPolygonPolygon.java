package Simulation.Collisions;

import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.StaticObjects.StaticObject;

public class CollisionPolygonPolygon {

/////////////////////////
////ELASTIC COLLISION////
/////////////////////////
	public static void elasticCollision(MoveableObject object1, MoveableObject object2) {
		
	}
	
	public static void elasticCollision(MoveableObject object1 , StaticObject object2) {
		
	}
	
	
	
////////////////////////
////REMOVE COLLISION////
////////////////////////
	public static void removeCollision(MoveableObject object1, StaticObject object2) {	

	}

	public static void removeCollision(MoveableObject object1, MoveableObject object2) {	
//		DynamicCollisionContext context1 = (DynamicCollisionContext) object1.getCollisionContext();
//		DynamicCollisionContext context2 = (DynamicCollisionContext) object2.getCollisionContext();
//		
//		do {		
//			float distance = Util.getDistance(object2.getX(), object2.getY(), object1.getX(), object1.getY());
//			
//			float overlap = 1f;
//			
//			float object1X = object1.getX()+overlap*(object1.getX()-object2.getX())/distance;
//			float object1Y = object1.getY()+overlap*(object1.getY()-object2.getY())/distance;
//			
//			float object2X = object2.getX()-overlap*(object1.getX()-object2.getX())/distance;
//			float object2Y = object2.getY()-overlap*(object1.getY()-object2.getY())/distance;
//			
//			object1.setX(object1X);
//			object1.setY(object1Y);
//			
//			object2.setX(object2X);
//			object2.setY(object2Y);
//			
//		} while(context1.checkCollision(context2));
		
	}
	
	
	
}