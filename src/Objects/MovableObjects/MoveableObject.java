package Objects.MovableObjects;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;
import Objects.GameObject;

public abstract class MoveableObject extends GameObject{

	public MoveableObject(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, material, colors, x, y);
	}

	public MoveableObject(String[] files,Material material, float[][] colors,float x,float y) {
		super(files, material, colors, x, y);
	}

	
}
