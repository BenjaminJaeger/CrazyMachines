package Objects.MovableObjects.Collisions;

import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;

public class CollisionContext {

	private MoveableObject object;
	
	private BoundingCircle[] boundingCirlces;
	private BoundingRectangle[] boundingRectangles;
	
	public CollisionContext(MoveableObject object, BoundingCircle[] circles, BoundingRectangle[] rectangles) {
		this.boundingCirlces= circles;
		this.boundingRectangles = rectangles;
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, BoundingCircle[] circles) {
		this.boundingCirlces= circles;
		this.boundingRectangles = new BoundingRectangle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, BoundingRectangle[] rectangles) {
		this.boundingRectangles = rectangles;
		this.boundingCirlces = new BoundingCircle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, BoundingRectangle rectangle) {
		this.boundingRectangles = new BoundingRectangle[1];
		this.boundingRectangles[0]=rectangle;
		this.boundingCirlces = new BoundingCircle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, BoundingCircle circle) {
		this.boundingCirlces = new BoundingCircle[1];
		this.boundingCirlces[0]=circle;
		this.boundingRectangles = new BoundingRectangle[0];
		this.object=object;
	}
	
	public void update() {
		for (BoundingCircle circle : boundingCirlces) {
			circle.getModel().setX(object.getX());
			circle.getModel().setY(object.getY());
		}
		
		for (BoundingRectangle rectangle : boundingRectangles) {
			
		}
	}
	
	public void checkCollisions() {
		for (GameObject object : GameObject.allObjects) 
			checkCollision(object.getCollisionContext());	
	}
	
	public void checkCollision(CollisionContext context) {
		for (BoundingCircle circle1 : boundingCirlces) 
			for (BoundingCircle circle2 : context.getBoundingCircles()) 
				if(circle1.checkCollision(circle2) && context.getGameObject().getId()!=object.getId()) {
					Collision.removeCollision((Ball)object, (Ball)context.getGameObject());
					Collision.elasticCollision(object, context.getGameObject());
				}	
	}
	
	public BoundingCircle[] getBoundingCircles() {
		return boundingCirlces;
	}
	
	public BoundingRectangle[] getBoundingRectangles() {
		return boundingRectangles;
	}
	
	public MoveableObject getGameObject() {
		return object;
	}

}
