package Objects.MovableObjects.Ball;


import Collisions.DynamicCollisionContext;
import Collisions.Boundings.BoundingCircle;
import Objects.MovableObjects.MoveableObject;
import RenderEngine.Core.Config;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Sphere;

public abstract class Ball extends MoveableObject{
	
	protected float radius;
	
	
////////////////////
////Constructors////
////////////////////
	public Ball(float radius,int resolution, Material material, float r, float g,float b, float x, float y) {
		super(new Sphere(resolution,radius), material, r,g,b, x, y);
		this.radius=radius;
		collisionContext = new DynamicCollisionContext(this,new BoundingCircle(x, y, 0, radius));
	}
	
	
///////////////
////Methods////
///////////////
	public void checkEdges() {
		if(y+radius>=Config.CANVAS_HEIGHT) {
			setY(Config.CANVAS_HEIGHT-radius);
			velocityY*=-1;
		}
		
		if(y-radius<=0) {
			setY(radius);
			velocityY*=-1;
		}
		
		if(x+radius>=Config.CANVAS_WIDTH) {
			setX(Config.CANVAS_WIDTH-radius);
			velocityX*=-1;
		}
		
		if(x-radius<=0) {
			setX(radius);
			velocityX*=-1;
		}
	}
	
	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public float getRadius() {
		return radius;
	}
	
}
