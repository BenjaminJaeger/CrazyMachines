package Simulation.Objects.MovableObjects.Box;

import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Cube;

public abstract class Box extends MoveableObject{
	
	protected float width;
	protected float height;
	protected float depth;


////////////////////
////Constructors////
////////////////////
	public Box(float size, Material material, float[] colors, float x, float y) {
		super(new Cube(size), material, colors, x, y);
		this.width=size;
		this.height=size;
		this.depth=size;
	}
	
	public Box(float width,float height ,float depth , Material material, float[] colors, float x, float y) {
		super(new Cube(width,height,depth), material, colors, x, y);
		this.width=width;
		this.height=height;
		this.depth=depth;
	}
	
	public Box(float size, Material material, float r,float g,float b, float x, float y) {
		super(new Cube(size), material, r,g,b, x, y);
		this.width=size;
		this.height=size;
		this.depth=size;
	}
	
	public Box(float width,float height ,float depth , Material material, float r,float g,float b, float x, float y) {
		super(new Cube(width,height,depth), material, r,g,b, x, y);
		this.width=width;
		this.height=height;
		this.depth=depth;
	}
	
	
///////////////
////Methods////
///////////////


	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

}