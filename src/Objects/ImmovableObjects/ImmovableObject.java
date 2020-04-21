package Objects.ImmovableObjects;

import Objects.GameObject;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Primitive;

public abstract class ImmovableObject extends GameObject{

	public ImmovableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}
	
}
 