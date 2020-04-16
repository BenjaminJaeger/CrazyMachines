package Objects.ImmovableObjects;

import java.util.ArrayList;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Cube;

public class Plank extends ImmovableObject{
	
	public static ArrayList<Plank> allPlanks = new ArrayList<Plank>();

	private float width;
	private float height;
	private float depth;
	
	public Plank(float size, Material material, float[] colors, float x, float y) {
		super(new Cube(size), material, colors, x, y);
		this.width=size;
		this.height=size;
		this.depth=size;
		allPlanks.add(this);
	}
	
	public Plank(float width,float height,float depth, Material material, float[] colors, float x, float y) {
		super(new Cube(width,height,depth), material, colors, x, y);
		this.width=width;
		this.height=height;
		this.depth=depth;
		allPlanks.add(this);
	}
	

	@Override
	public void update() {

	}


	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getDepth() {
		return depth;
	}

	
}
