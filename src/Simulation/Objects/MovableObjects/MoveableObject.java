package Simulation.Objects.MovableObjects;

import Simulation.SimulationControler;
import Simulation.Collisions.DynamicCollisionContext;
import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Primitive;

public abstract class MoveableObject extends GameObject{

	protected float velocityX,velocityY; //Geschwindigkeit
	protected float accelerationX,accelerationY; //Beschleunigung
	
	protected float originalAccelerationX,originalAccelerationY;
	
////////////////////
////Constructors////
////////////////////
	public MoveableObject(Primitive primitive, Material material,float r, float g,float b,float x, float y) {
		super(primitive, material, r,g,b, x, y);
	}
	
	public MoveableObject(Primitive primitive, Material material,String texture,float x, float y) {
		super(primitive, material, texture, x, y);
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
	public void update() {	
		applyForce(0, -9.807f);
		
		calculateVelocity();
		calculatePosition();
		
//		increaseRotation(-velocityX);
		resetAcceleration();
	
//		if (Math.abs(velocityX) <0.1f) 
//			velocityX=0;
//		if (Math.abs(velocityY)  <0.1f) 
//			velocityY=0;
//		
//		accelerationX = -velocityX*0.005f; 
//		accelerationY = -velocityY*0.005f;
		

		((DynamicCollisionContext) collisionContext).checkCollisions();
	}

	public void calculatePosition() {
		//  s = s0 + v0 * t + 0,5 * a * t2
		//  velocity     = m/s
		//  acceleration = m/s²
		//  time         = s
		
		setX( x + velocityX * SimulationControler.getUpdateTimeInSeconds() + 0.5f * accelerationX * (float)Math.pow(SimulationControler.getUpdateTimeInSeconds(),2) );
		setY( y + velocityY * SimulationControler.getUpdateTimeInSeconds() + 0.5f * accelerationY * (float)Math.pow(SimulationControler.getUpdateTimeInSeconds(),2) );
	}
	
	public void applyForce(float x,float y) {
		x/=mass;
		y/=mass;
		increaseAcceleration(x, y);
	}
	
	public void calculateVelocity() {
		//  v = v + a * t
		//  acceleration = m/s²
		//  time         = s
		//  velocity     = m/s
		
		this.velocityX = velocityX + accelerationX * SimulationControler.getUpdateTimeInSeconds();
		this.velocityY = velocityY + accelerationY * SimulationControler.getUpdateTimeInSeconds();
	}
	
	public void increaseAcceleration(float dx,float dy) {
		this.accelerationX+=dx;
		this.accelerationY+=dy;
	}
	
	public void resetAcceleration() {
		this.accelerationX=0;
		this.accelerationY=0;
	}
	
	public void resetVelocity() {
		this.velocityX=0;
		this.velocityY=0;
	}
	
	public void reset() {
		super.reset();
		resetVelocity();
		setAccelerationX(originalAccelerationX);
		setAccelerationY(originalAccelerationY);		
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

	public float getOriginalAccelerationX() {
		return originalAccelerationX;
	}

	public void setOriginalAccelerationX(float originalAccelerationX) {
		this.originalAccelerationX = originalAccelerationX;
	}

	public float getOriginalAccelerationY() {
		return originalAccelerationY;
	}

	public void setOriginalAccelerationY(float originalAccelerationY) {
		this.originalAccelerationY = originalAccelerationY;
	}
	
}
