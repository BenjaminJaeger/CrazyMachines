package Objects.MovableObjects.Ball;


import Engine.Core.Config;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Sphere;
import Objects.MovableObjects.MoveableObject;

public abstract class Ball extends MoveableObject{
	
	protected float radius;
	
	public Ball(float radius,int resolution, Material material, float[] colors, float x, float y) {
		super(new Sphere(resolution,radius), material, colors, x, y);
		this.radius=radius;
	}
	
	public void checkEdges() {
		if(y+radius>=Config.CANVAS_HEIGHT) {
			setY(Config.CANVAS_HEIGHT-radius);
			velocityY*=-1;
		}
		
		if(y-radius<=0) {
			setY(radius);
			velocityY*=-1;
		}
		
		if(x+radius>=Config.CANVAS_WIDTH) {
			setX(Config.CANVAS_WIDTH-radius);
			velocityX*=-1;
		}
		
		if(x-radius<=0) {
			setX(radius);
			velocityX*=-1;
		}
	}
	
	public float getRadius() {
		return radius;
	}
	
}
