package Objects.MovableObjects.Collisions;

import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;

public class DynamicCollisionContext extends CollisionContext{

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
	
	public void update(float x, float y,float rotation) {
		for (BoundingCircle circle : boundingCirlces) 
			circle.setXY(x,y);
		
		
		for (BoundingRectangle rectangle : boundingRectangles) {
			rectangle.setXY(x, y);
			rectangle.getModel().setRotationZ(rotation);
		}
	}
	
	public void checkCollisions() {
		for (GameObject object : GameObject.allObjects) 
			checkCollision(object.getCollisionContext());	
	}
	
	public void checkCollision(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2) && context.getID()!= this.id) {
					Collision.removeCollision((Ball)gameObject, (Ball)context.getGameObject());
					if(context instanceof DynamicCollisionContext) {
						Collision.elasticCollision((MoveableObject) gameObject, (MoveableObject)context.getGameObject());
					}else {
						//collision with static object
					}
				}	
	}
}
