package Objects.MovableObjects.Box;

import Engine.Core.Math.Vector3f;
import Engine.Core.Shaders.Core.Material;
import Objects.MovableObjects.Collisions.CollisionContext;
import Objects.MovableObjects.Collisions.Rectangle;

public class MetallBox extends Box{

	//private static float[] colors = {0.8f,0.8f,0.8f};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 16f);
	
	public MetallBox(float width, float height, float depth,float[] colors, float x, float y) {
		super(width, height, depth, material, colors, x, y);
	}
	
	public MetallBox(float size,float[] colors, float x, float y) {
		super(size, material, colors, x, y);
	}

	@Override
	public void setCollissionContext() {
		collisionContext = new CollisionContext(this, new Rectangle(x, y, width, height));
	}

}
 