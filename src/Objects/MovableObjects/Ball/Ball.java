package Objects.MovableObjects.Ball;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Sphere;
import Objects.MovableObjects.MoveableObject;

public class Ball extends MoveableObject{
	
	public Ball(float radius,int resolution, Material material, float[] colors, float x, float y) {
		super(new Sphere(resolution,radius), material, colors, x, y);
	}

	@Override
	public void checkEdges() {
		
		
	}

	
	
}
