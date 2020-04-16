package Objects.MovableObjects.Box;

import Engine.Core.Math.Vector3f;
import Engine.Core.Shaders.Core.Material;

public class MetallBox extends Box{

	private static float[] colors = {0.8f,0.8f,0.8f};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 16f);
	
	public MetallBox(float width, float height, float depth, float x, float y) {
		super(width, height, depth, material, colors, x, y);
	}
	
	public MetallBox(float size, float x, float y) {
		super(size, material, colors, x, y);
	}

}
 