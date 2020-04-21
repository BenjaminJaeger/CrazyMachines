package Objects.MovableObjects.Ball;

import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Shaders.Core.Material;

public class MetallBall extends Ball{
	
	//private static float[] colors = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 64f);

	
////////////////////
////Constructors////
////////////////////
	public MetallBall(float radius, int resolution, float r,float g,float b,float x, float y) {
		super(radius, resolution, material, r,g,b, x, y);
	}

}
 