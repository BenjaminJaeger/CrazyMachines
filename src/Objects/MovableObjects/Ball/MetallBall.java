package Objects.MovableObjects.Ball;

import Engine.Core.Math.Vector3f;
import Engine.Core.Shaders.Core.Material;
import Objects.MovableObjects.Collisions.BoundingCircle;
import Objects.MovableObjects.Collisions.DynamicCollisionContext;

public class MetallBall extends Ball{
	
	//private static float[] colors = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 64f);

	public MetallBall(float radius, int resolution, float r,float g,float b,float x, float y) {
		super(radius, resolution, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this,new BoundingCircle(x, y, 0, radius));
	}

}
 