package Simulation.Objects;

import java.util.ArrayList;

import Simulation.Collisions.CollisionContext;
import Simulation.RenderEngine.Core.Models.Model;
import Simulation.RenderEngine.Core.Models.TriangleModel;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Primitive;
import UI.BenjaminController.ObjectTransformer;

public abstract class GameObject {

	public static ArrayList<GameObject> allObjects = new ArrayList<GameObject>();

	private static BasicShader shader = new BasicShader("GameObject");
	
	protected TriangleModel[] models;
	private boolean renderModel = true;
	
	protected float x,y;
	protected float rotation;
	protected float scale;

	protected CollisionContext collisionContext;
	protected boolean renderBounding = false;
	
	protected float originalX,originalY;
	protected float originalrotation;
	protected float originalscale;
	
	protected float mass = 1;
	
	protected boolean selected;

	protected ObjectTransformer objectTransformer;
	
	
////////////////////
////Constructors////
////////////////////
	public GameObject(String[] files,Material material, float r, float g, float b,float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new TriangleModel(files[i],material,r,g,b,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	public GameObject(String[] files , String[] textures, Material material, float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[files.length];
		for (int i = 0; i < models.length; i++) 
			models[i] = new TriangleModel(files[i],textures[i],material,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	public GameObject(String file , String texture, Material material, float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[1];
		models[0] = new TriangleModel(file,texture,material,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	public GameObject(String file,Material material, float r, float g, float b,float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[1];
		models[0] = new TriangleModel(file,material,r,g,b,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	public GameObject(Primitive primitive,Material material, float r, float g,float b, float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[1];
		models[0] = new TriangleModel(primitive,material,r,g,b,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	public GameObject(Primitive primitive,Material material, String texture, float x,float y) {
		this.x = x;
		this.y = y;
		scale=1;
		
		models = new TriangleModel[1];
		models[0] = new TriangleModel(primitive,material,texture,x,y);		
		
		allObjects.add(this);
		
		originalX=x;
		originalY=y;
		originalrotation=0;
		originalscale=1;
	}
	
	
///////////////
////Methods////
///////////////
	public abstract void update();
	
	public void reset() {
		setY(originalY);
		setX(originalX);
		setScale(originalscale);
		setRotation(originalrotation);
	}
	
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
		this.scale = scale;
		for (Model model : models) 
			model.setScaleX(scale);
		for (Model model : models) 
			model.setScaleY(scale);	
		for (Model model : models) 
			model.setScaleZ(scale);
		
		collisionContext.setScale(scale);
	}
	
	public float getScale() {
		return scale;
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

	public float getOriginalX() {
		return originalX;
	}

	public void setOriginalX(float originalX) {
		this.originalX = originalX;
	}

	public float getOriginalY() {
		return originalY;
	}

	public void setOriginalY(float originalY) {
		this.originalY = originalY;
	}

	public float getOriginalrotation() {
		return originalrotation;
	}

	public void setOriginalrotation(float originalrotation) {
		this.originalrotation = originalrotation;
	}

	public float getOriginalscale() {
		return originalscale;
	}

	public void setOriginalscale(float originalscale) {
		this.originalscale = originalscale;
	}

	public float getMass() {
		return mass;
	}
	
	public void selectObject() {
		selected=true;
	}
	
	public void unSelectObject() {
		selected=false;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public ObjectTransformer getObjectTransformer() {
		return objectTransformer;
	}

	public void setObjectTransformer(ObjectTransformer objectTransformer) {
		this.objectTransformer = objectTransformer;
	}

	public BasicShader getShader() {
		return shader;
	}

}