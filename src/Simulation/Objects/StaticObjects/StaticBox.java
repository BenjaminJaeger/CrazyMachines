package Simulation.Objects.StaticObjects;

import Simulation.Collisions.CollisionContext;
import Simulation.Collisions.Boundings.BoundingRectangle;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Cube;

public class StaticBox extends StaticObject{

	
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 0f);
	
	
////////////////////
////Constructors////
////////////////////
	public StaticBox(float width,float height,float[] colors,float x,float y) {
		super(new Cube(width, height, 10), material, colors, x, y);
		collisionContext = new CollisionContext(this,new BoundingRectangle(x, y, width,height));
	}
	
	public StaticBox(float width,float height,float r,float g,float b,float x,float y) {
		super(new Cube(width, height, height), material, r,g,b, x, y);
		collisionContext = new CollisionContext(this,new BoundingRectangle(x, y,width,height));
	}

	
///////////////
////Methods////
///////////////
	@Override
	public void update() {
		
	}
	
}
