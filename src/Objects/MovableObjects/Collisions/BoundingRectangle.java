package Objects.MovableObjects.Collisions;

import Engine.Core.Models.LineModel;
import Engine.Primitives.RectangleLine;

public class BoundingRectangle extends Bounding{
	
	private float width,height;

	public BoundingRectangle(float x,float y,float offset,float width,float height) {
		super(x,y,offset);
		this.width=width;
		this.height=height;
		model = new LineModel(new RectangleLine(width,height),0,1,0,x,y);
	}
	
	public BoundingRectangle(float x,float y,float offset,float size) {
		super(x,y,offset);
		this.width=size;
		this.height=size;
		model = new LineModel(new RectangleLine(size),0,1,0,x,y);
	}
	
	public boolean checkCollision(BoundingCircle circle) {
		float distanceX = (float)Math.abs(circle.getX()-x);
		float distanceY = (float)Math.abs(circle.getY()-y);
		
		if(distanceX > width/2 + circle.getRadius())		return false;	
		if(distanceY > height/2 + circle.getRadius()) 	return false;
		
		if(distanceX <= width/2) 	return true;
		if(distanceY <= height/2) 	return true;
			
		float cornerDistance = (float)Math.pow((distanceX-height/2),2) + (float)Math.pow((distanceY-height/2),2);
		
		return cornerDistance <= circle.getRadius()*circle.getRadius();
	} 
	
	public boolean checkCollision(BoundingRectangle rectangle) {
		return false;
	} 

	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}