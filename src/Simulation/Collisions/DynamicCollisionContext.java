package Simulation.Collisions;

import Simulation.Collisions.Boundings.BoundingCircle;
import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.StaticObjects.StaticObject;
import Simulation.RenderEngine.Core.Math.Vector2f;

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
			if (object.getCollisionContext().getID()!=id && object.getCollisionContext().canCollide()) 
				checkCollision(object.getCollisionContext());							
	}
	
	
///////////////
////Methods////
///////////////
	public void checkCollision(CollisionContext context) {	
		checkCollisionCircleCircle(context);
		checkCollisionPolygonCircle(context);		
//		checkCollisionPolygonPolygon(context);
		}
	

	public void collisionCircleCircle(BoundingCircle circle1 , BoundingCircle circle2, GameObject object) {
					
		float r1 = circle1.getRadius();
		float r2 = circle2.getRadius();
		
		if(object instanceof MoveableObject) {
			CollisionCircleCircle.removeCollision((MoveableObject)gameObject, r1, (MoveableObject)object, r2);
			CollisionCircleCircle.elasticCollision((MoveableObject)gameObject, (MoveableObject)object);
		}else {
			CollisionCircleCircle.removeCollision((MoveableObject)gameObject, r1, (StaticObject)object, r2);
			CollisionCircleCircle.elasticCollision((MoveableObject)gameObject, (StaticObject)object);
		}
	}
	
	public void collisionCirclePolygon(BoundingPolygon polygon , BoundingCircle circle, GameObject object) {
		Vector2f p1 = null;
		Vector2f p2 = null;
		
		for (int i = 0; i < polygon.getPoints().length; i++) {
			 Vector2f testP1 = polygon.getPoints()[i];
			 Vector2f testP2 = polygon.getPoints()[i + 1 == polygon.getPoints().length ? 0 : i + 1];
			
			 if(circle.checkCollisionLineCircle(testP1,testP2)) {
				 p1 = testP1;
				 p2 = testP2;
			 }
		}
		
		if(object instanceof MoveableObject) {			
			CollisionCirclePolygon.removeCollision((MoveableObject)gameObject, circle.getRadius(), (MoveableObject)object, p1, p2);
			CollisionCirclePolygon.elasticCollision((MoveableObject)gameObject, circle.getRadius(), (MoveableObject)object, p1, p2,polygon,circle);
		}else {			
			CollisionCirclePolygon.removeCollision((MoveableObject)gameObject, circle.getRadius(), (StaticObject)object, p1, p2);
			CollisionCirclePolygon.elasticCollision((MoveableObject)gameObject, circle.getRadius(), (StaticObject)object, p1, p2);	
		}
			
	}
	
	public void collisionResolutionPolygonPolygon(GameObject object) {
		if(object instanceof MoveableObject) {
			
		}else {
				
		}
			
	}
	
	public void checkCollisionCircleCircle(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2)) {
					collisionCircleCircle(circle1,circle2,context.getGameObject());
					context.getGameObject().onCollision();
					this.getGameObject().onCollision();
				}
	}
	
	public void checkCollisionPolygonPolygon(CollisionContext context) {
		for (BoundingPolygon polygon1 : boundingPolygons) 
			for (BoundingPolygon polygon2 : context.getBoundingPolygons()) 			
				if(polygon1.checkCollision(polygon2)) 
					return;

	}
	
	public void checkCollisionPolygonCircle(CollisionContext context) {
		for (BoundingPolygon polygon : boundingPolygons) 
			for (BoundingCircle circle : context.getBoundingCircles()) 
				if(circle.checkCollision(polygon)) {
					collisionCirclePolygon(polygon,circle,context.getGameObject());
					context.getGameObject().onCollision();
					this.getGameObject().onCollision();
				}
				

		for (BoundingPolygon polygon : context.getBoundingPolygons()) 
			for (BoundingCircle circle : boundingCirlces) 
				if(circle.checkCollision(polygon)) {
					collisionCirclePolygon(polygon,circle,context.getGameObject());
					context.getGameObject().onCollision();
					this.getGameObject().onCollision();
				}
	}
	
	public boolean checkCollisionPolygonCircle2(CollisionContext context) {
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
