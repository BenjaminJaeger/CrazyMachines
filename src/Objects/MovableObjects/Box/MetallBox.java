package Objects.MovableObjects.Box;

import Collisions.BoundingRectangle;
import Collisions.DynamicCollisionContext;
import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Shaders.Core.Material;

public class MetallBox extends Box{

	//private static float[] colors = {0.8f,0.8f,0.8f};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 16f);
	
	
////////////////////
////Constructors////
////////////////////
	public MetallBox(float width, float height, float depth,float[] colors, float x, float y) {
		super(width, height, depth, material, colors, x, y);
		collisionContext = new DynamicCollisionContext(this,new BoundingRectangle(x, y, 0, width, height));
	}
	
	public MetallBox(float width, float height, float depth,float r,float g,float b, float x, float y) {
		super(width, height, depth, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this,new BoundingRectangle(x, y, 0, width, height));
	}
	
	public MetallBox(float size,float[] colors, float x, float y) {
		super(size, material, colors, x, y);
		collisionContext = new DynamicCollisionContext(this,new BoundingRectangle(x, y, 0, 0, size));
	}
	
	public MetallBox(float size,float r,float g,float b, float x, float y) {
		super(size, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this,new BoundingRectangle(x, y, 0, size));
	}

}
 