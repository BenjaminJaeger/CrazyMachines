package Objects.MovableObjects.Collisions;

import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;

public class CollisionContext {

	private MoveableObject object;
	
	private Circle[] cirlces;
	private Rectangle[] rectangles;
	
	public CollisionContext(MoveableObject object, Circle[] circles, Rectangle[] rectangles) {
		this.cirlces= circles;
		this.rectangles = rectangles;
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, Circle[] circles) {
		this.cirlces= circles;
		this.rectangles = new Rectangle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, Rectangle[] rectangles) {
		this.rectangles = rectangles;
		this.cirlces = new Circle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, Rectangle rectangle) {
		this.rectangles = new Rectangle[1];
		this.rectangles[0]=rectangle;
		this.cirlces = new Circle[0];
		this.object=object;
	}
	
	public CollisionContext(MoveableObject object, Circle circle) {
		this.cirlces = new Circle[1];
		this.cirlces[0]=circle;
		this.rectangles = new Rectangle[0];
		this.object=object;
	}
	
	public void checkCollisions() {
		for (GameObject object : GameObject.allObjects) 
			checkCollision(object.getCollisionContext());
		
	}
	
	public void checkCollision(CollisionContext context) {
		for (Circle circle1 : cirlces) 
			for (Circle circle2 : context.getCircles()) 
				if(circle1.checkCollision(circle2) && context.getGameObject().getId()!=object.getId()) {
					Collision.removeCollision((Ball)object, (Ball)context.getGameObject());
					Collision.elasticCollision(object, context.getGameObject());
				}	
	}
	
	public void update(float x,float y) {
		for (Circle circle : cirlces) {
			circle.setX(x);
			circle.setY(y);
		}	
		for (Rectangle rectangle : rectangles) {
			rectangle.setX(x);
			rectangle.setY(y);
		}
	}
	
	public Circle[] getCircles() {
		return cirlces;
	}
	
	public Rectangle[] getRectangles() {
		return rectangles;
	}
	
	public MoveableObject getGameObject() {
		return object;
	}

}
