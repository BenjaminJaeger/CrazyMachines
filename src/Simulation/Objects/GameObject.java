package Simulation.Objects;

import java.util.ArrayList;

import Simulation.Collisions.CollisionContext;
import Simulation.RenderEngine.Core.Models.Model;
import Simulation.RenderEngine.Core.Models.TriangleModel;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Primitive;

public abstract class GameObject {

	public static ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
	
	protected TriangleModel[] models;
	private boolean renderModel = true;
	
	protected float x,y;
	protected float rotation;
	protected float scaleX,scaleY;

	protected CollisionContext collisionContext;
	protected boolean renderBounding = false;
	
	
////////////////////
////Constructors////
////////////////////
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
	
	public GameObject(String[] files,Material material, float r, float g, float b,float x,float y) {
		this.x = x;
		this.y = y;
		models = new TriangleModel[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new TriangleModel(files[i],material,r,g,b,x,y);		
		scaleX=1; 
		scaleX=1;
		allObjects.add(this);
	}
	
	public GameObject(String file,Material material, float r, float g, float b,float x,float y) {
		this.x = x;
		this.y = y;
		models = new TriangleModel[1];
		models[0] = new TriangleModel(file,material,r,g,b,x,y);		
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
	
	
	
///////////////
////Methods////
///////////////
	public abstract void update();
	

	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public void increasePosition(float dx,float dy) {
		
		if (dx!=0) {
			this.x+=dx;
			collisionContext.setX(x);
		}
		
		if (dy!=0) {
			this.y+=dy;
			collisionContext.setY(y);
		}
		
		for (Model model : models) 
			model.increasePosition(dx, dy,0);	
	}
	
	public void increaseRotation(float dz) {
		if(dz!=0) {
			this.rotation+=dz;
			for (Model model : models) 
				model.increaseRotation(0, 0, dz);		
			collisionContext.setRotation(rotation);
		}
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
		collisionContext.setX(x);
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y=y;
		for (Model model : models) 
			model.setY(y);
		collisionContext.setY(y);
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
		for (Model model : models) 
			model.setRotationZ(rotation);
		collisionContext.setRotation(rotation);
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
		
		collisionContext.setScale(scale);
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
	
	public CollisionContext getCollisionContext() {
		return collisionContext;
	}

	public boolean renderBounding() {
		return renderBounding;
	}
	
	public void renderBounding(boolean renderBounding) {
		this.renderBounding = renderBounding;
	}
	
	public float getRotation() {
		return rotation;
	}

	public boolean renderModel() {
		return renderModel;
	}
	
	public void renderModel(boolean renderModel) {
		this.renderModel  = renderModel;
	}

}