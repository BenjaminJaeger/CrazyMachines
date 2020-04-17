package Objects.MovableObjects.Collisions;

import Objects.Util;

public class Circle {
	
	private float radius;
	private float x,y;
	
	
	public Circle(float x,float y,float radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	
	/**
	 * Returns true if both balls collide
	 */
	public boolean checkCollision(Circle circle) {
		//when the radii of both circles combined is smaller than the distance between both balls they collide
		return circle.getRadius()+radius > Util.getDistance(circle.getX(), circle.getY(),x, y);
	}
	
	public boolean checkCollision(Rectangle rectangle) {
		float distanceX = (float)Math.abs(x-rectangle.getX());
		float distanceY = (float)Math.abs(y-rectangle.getY());
		
		if(distanceX > rectangle.getWidth()/2 + radius)		return false;	
		if(distanceY > rectangle.getHeight()/2 + radius) 	return false;
		
		if(distanceX <= rectangle.getWidth()/2) 	return true;
		if(distanceY <= rectangle.getHeight()/2) 	return true;
			
		float cornerDistance = (float)Math.pow((distanceX-rectangle.getWidth()/2),2) + (float)Math.pow((distanceY-rectangle.getHeight()/2),2);
		
		return cornerDistance <= radius*radius;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	

}