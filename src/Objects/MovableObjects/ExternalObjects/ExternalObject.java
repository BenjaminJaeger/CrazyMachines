package Objects.MovableObjects.ExternalObjects;

import Collisions.DynamicCollisionContext;
import Objects.MovableObjects.MoveableObject;
import RenderEngine.Core.Shaders.Core.Material;

public abstract class ExternalObject extends MoveableObject{

	public ExternalObject(String[] files, Material material, float r, float g, float b, float x, float y) {
		super(files, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this);
	}
	
	public ExternalObject(String file, Material material, float r, float g, float b, float x, float y) {
		super(file, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this);
	}


}
