package Objects.MovableObjects;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;
import Objects.GameObject;

public abstract class MoveableObject extends GameObject{

	protected float velocityX,velocityY; //Geschwindigkeit
	protected float accelerationX,accelerationY; //Beschleunigung
	
	protected float mass=1;
	
	public MoveableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}

	public MoveableObject(String[] files,Material material, float[][] colors,float x,float y) {
		super(files, material, colors, x, y);
	}

	public void update() {
			
	//		applyForce(0, 1f);
			
			collision();
			
			checkEdges();
			
			increaseVelocity(accelerationX, accelerationY);
			increasePosition(velocityX, velocityY);
			increaseRotation(velocityX);
			resetAcceleration();
			
	//		accelerationX = -velocityX*0.05f;
	//		accelerationY = -velocityY*0.05f;
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
