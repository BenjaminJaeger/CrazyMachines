package Collisions;

import Collisions.Boundings.BoundingCircle;
import Collisions.Boundings.BoundingPolygon;
import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;

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
				checkCollision(object.getCollisionContext());	
	}
	
	
///////////////
////Methods////
///////////////
	public void checkCollision(CollisionContext context) {
		checkCollisionCircleCircle(context);
		checkCollisionPolygonCircle(context);
		checkCollisionPolygonPolygon(context);
	}
	
	public void checkCollisionCircleCircle(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2)) {
					
					//remove collision
					//Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}
	}
	
	public void checkCollisionPolygonPolygon(CollisionContext context) {
		for (BoundingPolygon polygon1 : boundingPolygons) 
			for (BoundingPolygon polygon2 : context.getBoundingPolygons()) 			
				if(polygon1.checkCollision(polygon2)) {
					
					//remove collision
					//Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}
			
	}
	
	public void checkCollisionPolygonCircle(CollisionContext context) {
		for (BoundingPolygon polygon : boundingPolygons) 
			for (BoundingCircle circle : context.getBoundingCircles()) 
				if(circle.checkCollision(polygon)) {

					//remove collision
					//Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}
	}
	
}
