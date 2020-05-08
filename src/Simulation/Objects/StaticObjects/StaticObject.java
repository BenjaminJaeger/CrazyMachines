package Simulation.Objects.StaticObjects;

import Simulation.Collisions.CollisionContext;
import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Primitive;

public abstract class StaticObject extends GameObject{
	
////////////////////
////Constructors////
////////////////////
	public StaticObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
		collisionContext = new CollisionContext(this);
	}
	
	public StaticObject(Primitive primitive, Material material,float r,float g,float b, float x, float y) {
		super(primitive, material, r,g,b, x, y);
		collisionContext = new CollisionContext(this);
	}

	public StaticObject(String file, Material material, float r, float g, float b, float x, float y) {
		super(file, material, r,g,b, x, y);
		collisionContext = new CollisionContext(this);
	}
	
}
 