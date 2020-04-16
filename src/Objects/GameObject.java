package Objects;

import Engine.Core.Models.Model;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;

public abstract class GameObject {

	Model[] models;
	
	protected static int counter;
	protected int id;
	
	protected float x,y;
	protected float rotation;
	protected float scaleX,scaleY;
	
	
	public GameObject(String[] files,Material material, float[][] colors,float x,float y) {
		this.x = x;
		this.y = y;
		models = new Model[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new Model(files[i],material,colors[i],x,y);		
		id=counter;
		counter++;
		scaleX=1; 
		scaleX=1;
	}
	
	public GameObject(Primitive primitive,Material material,float[] colors, float x,float y) {
		this.x = x;
		this.y = y;
		models = new Model[1];
		models[0] = new Model(primitive,material,colors,x,y);		
		id=counter;
		counter++;
		scaleX=1;
		scaleX=1;
	}
	
	
	public abstract void update();
	
	
	//Getters and Setters
	
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
	public void increaseRotation(float dz) {
		this.rotation+=dz;
		for (Model model : models) 
			model.increaseRotation(0, 0, dz);		
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
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
		for (Model model : models) 
			model.setRotationZ(rotation);
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
	
	public int getId() {
		return id;
	}
	
	
}
