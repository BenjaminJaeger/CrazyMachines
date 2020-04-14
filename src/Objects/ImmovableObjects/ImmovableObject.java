package Objects.ImmovableObjects;

import Engine.Core.Shaders.Core.Material;
import Objects.GameObject;

public abstract class ImmovableObject extends GameObject{

	public ImmovableObject(String[] files, Material material, float[][] colors, float x, float y) {
		super(files, material, colors, x, y);
	}
}
