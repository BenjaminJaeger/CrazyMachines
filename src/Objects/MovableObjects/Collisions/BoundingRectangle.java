package Objects.MovableObjects.Collisions;

public class BoundingRectangle {
	
	private float width,height;
	private float x,y;
	
	
	public BoundingRectangle(float x,float y, float width,float height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}