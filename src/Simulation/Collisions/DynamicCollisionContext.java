package Simulation.Collisions;

import Simulation.Collisions.Boundings.BoundingCircle;
import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.StaticObjects.StaticObject;

public class DynamicCollisionContext extends CollisionContext{

 static float counter;
 
////////////////////
////Constructors////
////////////////////
 public DynamicCollisionContext(GameObject gameObject) {
		super(gameObject);
	}
 
	public DynamicCollisionContext(GameObject gameObject,BoundingCircle[] boundingCircles, BoundingPolygon[] boundingPolygons) {
		super(gameObject, boundingCircles, boundingPolygons);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingCircle[] boundingCircles) {
		super(gameObject, boundingCircles);
	}

	public DynamicCollisionContext(GameObject gameObject,BoundingCircle boundingCircle) {
		super(gameObject, boundingCircle);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingPolygon[] boundingPolygons) {
		super(gameObject, boundingPolygons);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingPolygon boundingPolygon) {
		super(gameObject, boundingPolygon);
	}
	
	public void checkCollisions() {
		for (GameObject object : GameObject.allObjects) 
			if (object.getCollisionContext().getID()!=id) 
				if(checkCollision(object.getCollisionContext())) 					
					collisionResolution(object);
				
	}
	
	
///////////////
////Methods////
///////////////
	public boolean checkCollision(CollisionContext context) {	
		if(checkCollisionPolygonCircle(context)) {
//			System.out.println("POLYGON CIRCLE COLLISION");
			return true;
		}
		
		if(checkCollisionCircleCircle(context)) {
//			System.out.println("CIRCLE CIRCLE COLLISION");
			return true;
		}
		
			
		if(checkCollisionPolygonPolygon(context)) {
//			System.out.println("POLYGON POLYGON COLLISION");
			return true;
		}
		
		return false;	
	}
	

	public void collisionResolution(GameObject object) {
		if(object instanceof MoveableObject) {
			Collision.removeCollision((MoveableObject) gameObject, (MoveableObject)object);
			Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)object);		
		}else {
			Collision.removeCollision((MoveableObject) gameObject , (StaticObject)object);
			Collision.elasticCollision((MoveableObject) gameObject , (StaticObject)object);
		}
			
	}
	
	public boolean checkCollisionCircleCircle(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2)) 
					return true;
		
		return false;	
	}
	
	public boolean checkCollisionPolygonPolygon(CollisionContext context) {
		for (BoundingPolygon polygon1 : boundingPolygons) 
			for (BoundingPolygon polygon2 : context.getBoundingPolygons()) 			
				if(polygon1.checkCollision(polygon2)) 
					return true;

		return false;	
	}
	
	public boolean checkCollisionPolygonCircle(CollisionContext context) {
		for (BoundingPolygon polygon : boundingPolygons) 
			for (BoundingCircle circle : context.getBoundingCircles()) 
				if(circle.checkCollision(polygon)) 
					return true;
				

		for (BoundingPolygon polygon : context.getBoundingPolygons()) 
			for (BoundingCircle circle : boundingCirlces) 
				if(circle.checkCollision(polygon)) 
					return true;

		return false;	
	}
	
}
