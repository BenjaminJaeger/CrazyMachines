package Objects;

import java.util.ArrayList;

import Engine.Core.Models.Model;
import Engine.Core.Models.TriangleModel;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;
import Objects.MovableObjects.Collisions.CollisionContext;

public abstract class GameObject {

	public static ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
	
	protected TriangleModel[] models;
	
	protected float x,y;
	protected float rotation;
	protected float scaleX,scaleY;

	private boolean renderBounding = true;
	
	
	public GameObject(String[] files,Material material, float[][] colors,float x,float y) {
		this.x = x;
		this.y = y;
		models = new TriangleModel[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new TriangleModel(files[i],material,colors[i],x,y);		
		scaleX=1; 
		scaleX=1;
		allObjects.add(this);
	}
	
	public GameObject(Primitive primitive,Material material,float[] colors, float x,float y) {
		this.x = x;
		this.y = y;
		models = new TriangleModel[1];
		models[0] = new TriangleModel(primitive,material,colors,x,y);		
		scaleX=1;
		scaleX=1;
		allObjects.add(this);
	}
	
	public GameObject(Primitive primitive,Material material, float r, float g,float b, float x,float y) {
		this.x = x;
		this.y = y;
		models = new TriangleModel[1];
		models[0] = new TriangleModel(primitive,material,r,g,b,x,y);		
		scaleX=1;
		scaleX=1;
		allObjects.add(this);
	}
	
	
	public abstract void update();
	
	

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
	
	public TriangleModel[] getModels() {
		return models;
	}
	
	public void setModels(TriangleModel[] models) {
		this.models = models;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
		for (Model model : models) 
			model.setX(x);	
		//collisionContext.update(x, x);
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y=y;
		for (Model model : models) 
			model.setY(y);
		//collisionContext.update(x, x);
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
	
	public abstract CollisionContext getCollisionContext();

	public boolean renderBounding() {
		return renderBounding;
	}
	
	public void renderBounding(boolean renderBounding) {
		this.renderBounding = renderBounding;
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
