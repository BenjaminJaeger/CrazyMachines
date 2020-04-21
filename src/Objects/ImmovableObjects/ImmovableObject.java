package Objects.ImmovableObjects;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;
import Objects.GameObject;

public abstract class ImmovableObject extends GameObject{

	public ImmovableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}
	
}
 