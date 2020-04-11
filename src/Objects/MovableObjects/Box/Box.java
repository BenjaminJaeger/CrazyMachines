package Objects.MovableObjects.Box;

import Engine.Core.Config;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Cube;
import Objects.MovableObjects.MoveableObject;

public class Box extends MoveableObject{
	
	float width;
	float height;
	float depth;


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
	
	
	public void checkEdges() {
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
	
}
