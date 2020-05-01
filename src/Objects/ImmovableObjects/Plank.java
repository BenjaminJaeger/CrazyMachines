package Objects.ImmovableObjects;

import Collisions.CollisionContext;
import Collisions.Boundings.BoundingRectangle;
import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Cube;

public class Plank extends StaticObject{

	
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 64f);
	
	
////////////////////
////Constructors////
////////////////////
	public Plank(float width,float height,float[] colors,float x,float y) {
		super(new Cube(width, height, 10), material, colors, x, y);
		collisionContext = new CollisionContext(this,new BoundingRectangle(height, height, 0, width,height));
	}
	
	public Plank(float width,float height,float r,float g,float b,float x,float y) {
		super(new Cube(width, height, 10), material, r,g,b, x, y);
		collisionContext = new CollisionContext(this,new BoundingRectangle(height, height, 0, width,height));
	}

	
///////////////
////Methods////
///////////////
	@Override
	public void update() {
		
	}
	
}
