package Objects.MovableObjects.Box;

import java.util.ArrayList;

import Engine.Core.Config;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Cube;
import Objects.MovableObjects.MoveableObject;

public class Box extends MoveableObject{
	
	public static ArrayList<Box> allBoxes = new ArrayList<Box>();
	
	private float width;
	private float height;
	private float depth;


	public Box(float size, Material material, float[] colors, float x, float y) {
		super(new Cube(size), material, colors, x, y);
		this.width=size;
		this.height=size;
		this.depth=size;
		allBoxes.add(this);
	}
	
	public Box(float width,float height ,float depth , Material material, float[] colors, float x, float y) {
		super(new Cube(width,height,depth), material, colors, x, y);
		this.width=width;
		this.height=height;
		this.depth=depth;
		allBoxes.add(this); 
	}
	
	
	@Override
	protected void collision() {

	}
	
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

	public static ArrayList<Box> getAllBoxes() {
		return allBoxes;
	}


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
