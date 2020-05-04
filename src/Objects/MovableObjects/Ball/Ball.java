package Objects.MovableObjects.Ball;


import Collisions.DynamicCollisionContext;
import Collisions.Boundings.BoundingCircle;
import Objects.MovableObjects.MoveableObject;
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
		collisionContext = new DynamicCollisionContext(this,new BoundingCircle(x, y,radius));
	}
	
	
///////////////
////Methods////
///////////////

	
	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public float getRadius() {
		return radius;
	}
	
}
