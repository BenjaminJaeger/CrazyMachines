package Objects.MovableObjects.Box;

import Objects.MovableObjects.MoveableObject;
import RenderEngine.Core.Config;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Cube;

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
	@Override
	public void checkEdges() {		
		if(y-width<=0) {
			setY(height);
			velocityY*=-1;
		}
		
		if(y+height>=Config.CANVAS_HEIGHT) {
			setY(Config.CANVAS_HEIGHT-height);
			velocityY*=-1;
		}
		
		if(x+width>=Config.CANVAS_WIDTH) {
			setX(Config.CANVAS_WIDTH-width);
			velocityX*=-1;
		}
		
		if(x-width<=0) {
			setX(width);
			velocityX*=-1;
		}
	}

	
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
