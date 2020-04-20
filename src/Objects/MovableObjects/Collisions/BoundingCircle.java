package Objects.MovableObjects.Collisions;

import Engine.Core.Models.LineModel;
import Engine.Primitives.CircleLine;
import Objects.Util;

public class BoundingCircle  extends Bounding{

	private float radius;

////////////////////
////Constructors////
////////////////////
	public BoundingCircle(float x, float y,float offset,float radius) {
		super(offset, x, y);
		this.radius = radius;
		model = new LineModel(new CircleLine(radius, 30), 0, 0, 1, x,y);
	}
	

/////////////////
////Collision////
/////////////////
	public boolean checkCollision(BoundingCircle circle) {
		// when radii of both circles is smaller than the distance between them
		return circle.getRadius() + radius > Util.getDistance(circle.getX(), circle.getY(), x, y);
	}

	
	public boolean checkCollision(BoundingPolygon polygon) {
		return false;
	}
	
	
//	//https://www.gamedevelopment.blog/collision-detection-circles-rectangles-and-polygons/
//	public boolean checkCollision(BoundingRectangle rectangle) {
//			
//		Vector2f newP = Util.rotateArroundPoint(x, y, rectangle.getRotation(), rectangle.getX(), rectangle.getY());
//				
//		float distanceX = (float) Math.abs(newP.x - rectangle.getX());
//		float distanceY = (float) Math.abs(newP.y - rectangle.getY());
//
//		if (distanceX > rectangle.getWidth() / 2 + radius)		return false;
//		if (distanceY > rectangle.getHeight() / 2 + radius) 	return false;
//				
//		if (distanceX <= rectangle.getWidth() / 2)		return true;
//		if (distanceY <= rectangle.getHeight() / 2)		return true;
//				
//		float cornerDistance =(float) Math.pow((distanceX - rectangle.getWidth() / 2), 2) + (float) Math.pow((distanceY - rectangle.getHeight() / 2), 2);
//
//		return cornerDistance <= radius * radius;
//	}

	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public float getRadius() {
		return radius;
	}

}