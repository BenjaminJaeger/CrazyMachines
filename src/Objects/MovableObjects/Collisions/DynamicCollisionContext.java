package Objects.MovableObjects.Collisions;

import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;

public class DynamicCollisionContext extends CollisionContext{

 static float counter;
////////////////////
////Constructors////
////////////////////
	public DynamicCollisionContext(GameObject gameObject,BoundingCircle[] circles, BoundingRectangle[] rectangles) {
		super(gameObject, circles, rectangles);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingCircle[] boundingCircles) {
		super(gameObject, boundingCircles);
	}

	public DynamicCollisionContext(GameObject gameObject,BoundingCircle boundingCircle) {
		super(gameObject, boundingCircle);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingRectangle[] boundingRectangles) {
		super(gameObject, boundingRectangles);
	}
	
	public DynamicCollisionContext(GameObject gameObject,BoundingRectangle boundingRectangle) {
		super(gameObject, boundingRectangle);
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
		//checkCollisionCircleCircle(context);
		//checkCollisionRectangleCircle(context);
		checkCollisionRectangleRectangle(context);
	}
	
	public void checkCollisionCircleCircle(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2)) {
					//circle1.getModel().changeColor(1, 0, 0);
					
					Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}else {
					//circle1.getModel().changeColor(0, 1, 0);
				}
	}
	
	public void checkCollisionRectangleRectangle(CollisionContext context) {
		for (BoundingPolygon polygon1 : boundingPolygons) 
			for (BoundingPolygon polygon2 : context.getBoundingPolygons()) 
				if(polygon1.checkCollision( polygon2)) {
					//rectangle1.getModel().changeColor(1, 0, 0);
					System.out.println(counter++);
					//remove collision
					//Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}else {
					//rectangle1.getModel().changeColor(0, 1, 0);
				}
	}
	
	public void checkCollisionRectangleCircle(CollisionContext context) {
		for (BoundingPolygon polygon : boundingPolygons) 
			for (BoundingCircle circle : context.getBoundingCircles()) 
				if(circle.checkCollision(polygon)) {
					//rectangle.getModel().changeColor(1, 0, 0);
					
					//remove collision
					//Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}else {
					//rectangle.getModel().changeColor(0, 1, 0);
				}
	}
	
}
