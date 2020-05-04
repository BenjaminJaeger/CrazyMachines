package Objects.StaticObjects;

import Objects.GameObject;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Primitive;

public abstract class StaticObject extends GameObject{
	
////////////////////
////Constructors////
////////////////////
	public StaticObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}
	
	public StaticObject(Primitive primitive, Material material,float r,float g,float b, float x, float y) {
		super(primitive, material, r,g,b, x, y);
	}

}
 