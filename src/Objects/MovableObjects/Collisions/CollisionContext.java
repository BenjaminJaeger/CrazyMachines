package Objects.MovableObjects.Collisions;

import Objects.GameObject;

public class CollisionContext {
	
	protected static int counter;
	protected int id;
	
	protected BoundingCircle[] boundingCirlces;
	protected BoundingRectangle[] boundingRectangles;
	
	protected GameObject gameObject;
	
	public CollisionContext(GameObject gameObject,BoundingCircle[] circles, BoundingRectangle[] rectangles) {
		this.boundingCirlces= circles;
		this.boundingRectangles = rectangles;
		id=counter;
		counter++;
		this.gameObject=gameObject;
	}
	
	public CollisionContext(GameObject gameObject,BoundingCircle[] boundingCircles) {
		this.boundingCirlces= boundingCircles;
		this.boundingRectangles = new BoundingRectangle[0];
		id=counter;
		counter++;
		this.gameObject=gameObject;
	}

	public CollisionContext(GameObject gameObject,BoundingCircle boundingCircle) {
		this.boundingCirlces = new BoundingCircle[1];
		this.boundingCirlces[0]=boundingCircle;
		this.boundingRectangles = new BoundingRectangle[0];
		id=counter;
		counter++;
		this.gameObject=gameObject;
	}
	
	public CollisionContext(GameObject gameObject,BoundingRectangle[] boundingRectangles) {
		this.boundingRectangles = boundingRectangles;
		this.boundingCirlces = new BoundingCircle[0];
		id=counter;
		counter++;
		this.gameObject=gameObject;
	}
	
	public CollisionContext(GameObject gameObject,BoundingRectangle boundingRectangle) {
		this.boundingRectangles = new BoundingRectangle[1];
		this.boundingRectangles[0]=boundingRectangle;
		this.boundingCirlces = new BoundingCircle[0];
		id=counter;
		counter++;
		this.gameObject=gameObject;
	}
		
	public BoundingCircle[] getBoundingCircles() {
		return boundingCirlces;
	}
	
	public BoundingRectangle[] getBoundingRectangles() {
		return boundingRectangles;
	}
	
	public int getID() {
		return id;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	
}
