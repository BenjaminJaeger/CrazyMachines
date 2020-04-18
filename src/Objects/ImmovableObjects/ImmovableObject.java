package Objects.ImmovableObjects;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;
import Objects.GameObject;
import Objects.MovableObjects.Collisions.CollisionContext;

public abstract class ImmovableObject extends GameObject{

	protected CollisionContext collisionContext;
	
	public ImmovableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}
	
	public ImmovableObject(Primitive primitive, Material material,float r,float g,float b, float x, float y) {
		super(primitive, material, r,g,b, x, y);
	}
	
	public CollisionContext getCollisionContext() {
		return collisionContext;
	}
}
 