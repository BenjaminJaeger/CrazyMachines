package Objects.MovableObjects.Ball;

import Engine.Core.Math.Vector3f;
import Engine.Core.Shaders.Core.Material;
import Objects.MovableObjects.Collisions.Circle;
import Objects.MovableObjects.Collisions.CollisionContext;

public class MetallBall extends Ball{
	
	//private static float[] colors = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 64f);

	public MetallBall(float radius, int resolution, float[] colors,float x, float y) {
		super(radius, resolution, material, colors, x, y);
		collisionContext = new CollisionContext(this, new Circle(x, y, radius));
	}

	@Override
	public void setCollissionContext() {
		
	}

}
 