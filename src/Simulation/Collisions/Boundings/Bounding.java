package Simulation.Collisions.Boundings;

import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;

public abstract class Bounding {

	protected float x,y;
	protected float rotation;
	protected float scale = 1;
	
	protected LineModel model;
	
	protected static BasicShader shader = new BasicShader("Line");
	
////////////////////
////Constructors////
////////////////////
	public Bounding (float x, float y) {
		this.x=x;
		this.y=y;
	}
		
	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public LineModel getModel() {
		return model;
	}

	public BasicShader getShader() {
		return shader;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		model.setX(x);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		model.setY(y);
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		model.setRotationZ(rotation);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		model.setScale(scale);	
	}
	
}