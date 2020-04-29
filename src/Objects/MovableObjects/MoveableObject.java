package Objects.MovableObjects;

import Collisions.DynamicCollisionContext;
import Objects.GameObject;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Primitive;

public abstract class MoveableObject extends GameObject{

	protected float velocityX,velocityY; //Geschwindigkeit
	protected float accelerationX,accelerationY; //Beschleunigung
	
	protected float mass=1;
	
////////////////////
////Constructors////
////////////////////
	public MoveableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}
	
	public MoveableObject(Primitive primitive, Material material,float r, float g,float b,float x, float y) {
		super(primitive, material, r,g,b, x, y);
	}

	public MoveableObject(String[] files,Material material, float[][] colors,float x,float y) {
		super(files, material, colors, x, y);
	}

	public MoveableObject(String[] files, Material material, float r, float g, float b, float x, float y) {
		super(files, material, r,g,b, x, y);
	}
	
	public MoveableObject(String file, Material material, float r, float g, float b, float x, float y) {
		super(file, material, r,g,b, x, y);
	}

	///////////////
////Methods////
///////////////
	/**
	 * checks if the object collides with the edges of the screen
	 */
	public abstract void checkEdges();

	
	public void update() {
		
		//collisionContext.update(x,y,rotation);
		
		//applyForce(0, 0.5f);
			 
		checkEdges();
			
		increaseVelocity(accelerationX, accelerationY);
		
//		if (Math.abs(velocityX) <0.01f) 
//			velocityX=0;
//		if (Math.abs(velocityY)  <0.01f) 
//			velocityY=0;
			
		increasePosition(velocityX, velocityY);
		//increaseRotation(velocityX);
		resetAcceleration();
			
		((DynamicCollisionContext) collisionContext).checkCollisions();
			
//		accelerationX = -velocityX*0.0005f;
//		accelerationY = -velocityY*0.0005f;
	}
	
	public void applyForce(float x,float y) {
		x/=mass;
		y/=mass;
		increaseAcceleration(x, y);
	}
	

	public void increaseVelocity(float dx,float dy) {
		this.velocityX+=dx;
		this.velocityY+=dy;
	}
	
	public void increaseAcceleration(float dx,float dy) {
		this.accelerationX+=dx;
		this.accelerationY+=dy;
	}
	
	public void resetAcceleration() {
		this.accelerationX=0;
		this.accelerationY=0;
	}
	
	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}


	public float getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public float getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	public float getMass() {
		return mass;
	}
	
	public void setMass(float mass) {
		this.mass=mass;
	}
	
}
