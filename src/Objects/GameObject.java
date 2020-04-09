package Objects;

import Engine.Core.Models.Model;
import Engine.Core.Shaders.Core.Material;

public class GameObject {

	Model[] models;
	float[][] colors;
	
	
	float x,y;
	float rotationX,rotationY;
	float scaleX,scaleY;
	
	public GameObject(String[] files,Material material, float[][] colors,float x,float y) {
		this.colors = colors;
		this.x = x;
		this.y = y;
		models = new Model[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new Model(files[i],material,colors[i],x,y);		
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
	
	
	public Model[] getModesl() {
		return models;
	}
	
	public void setModesl(Model[] modesl) {
		this.models = modesl;
	}
	
	public float[][] getColors() {
		return colors;
	}
	
	public void setColors(float[][] colors) {
		this.colors = colors;
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
	
}
