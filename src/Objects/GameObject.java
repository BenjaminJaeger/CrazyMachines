package Objects;

import Engine.Core.Models.Model;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;

public abstract class GameObject {

	Model[] models;
	
	protected float x,y;
	protected float rotationX,rotationY;
	protected float scaleX,scaleY;
	
	protected float velocityX,velocityY; //Geschwindigkeit
	protected float accelerationX,accelerationY; //Beschleunigung
	
	protected float mass=1;
	
	
	public GameObject(String[] files,Material material, float[][] colors,float x,float y) {
		this.x = x;
		this.y = y;
		models = new Model[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new Model(files[i],material,colors[i],x,y);		
	}
	
	public GameObject(Primitive primitive,Material material,float[] colors, float x,float y) {
		this.x = x;
		this.y = y;
		models = new Model[1];
		models[0] = new Model(primitive,material,colors,x,y);		
	}
	
	
	public void update() {
		applyForce(0, 0.5f); //gravity
		applyForce(0.1f, 0); //wind
		
		checkEdges();
		
		increaseVelocity(accelerationX, accelerationY);
		increasePosition(velocityX, velocityY);
		resetAcceleration();
	}
	
	public abstract void checkEdges();
	
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
	
	/**
	 * increases the current xyz position by dx,dy,dz
	 */
	public void increasePosition(float dx,float dy) {
		this.x+=dx;
		this.y+=dy;
		for (Model model : models) 
			model.increasePosition(dx, dy,0);	
	}
	
	/**
	 * increases the current xyz rotation by dx,dy,dz
	 * rotation in angle
	 */
	public void increaseRotation(float dx,float dy) {
		this.rotationX+=dx;
		this.rotationY+=dy;
		for (Model model : models) 
			model.increaseRotation(dx, dy, 0);		
	}
	
	public Model[] getModels() {
		return models;
	}
	
	public void setModels(Model[] modesl) {
		this.models = modesl;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
		for (Model model : models) 
			model.setX(x);	
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y=y;
		for (Model model : models) 
			model.setY(y);
	}
	
	public float getRotationX() {
		return rotationX;
	}
	
	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
		for (Model model : models) 
			model.setRotationX(rotationX);
	}
	
	public float getRotationY() {
		return rotationY;
	}
	
	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
		for (Model model : models) 
			model.setRotationY(rotationY);
	}
	
	public void setScale(float scale) {
		this.scaleX = scale;
		for (Model model : models) 
			model.setScaleX(scale);
		this.scaleY = scale;
		for (Model model : models) 
			model.setScaleY(scale);
		
		for (Model model : models) 
			model.setScaleZ(scale);
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
		for (Model model : models) 
			model.setScaleX(scaleX);
	}
	
	public float getScaleY() {
		return scaleY;
	}
	
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
		for (Model model : models) 
			model.setScaleY(scaleY);
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
	
	
}
