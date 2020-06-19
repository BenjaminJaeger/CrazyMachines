package Simulation.Objects.MovableObjects.Ball;

import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class MetalBall extends Ball{
	
	public static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1), 2f);

////////////////////
////Constructors////
////////////////////
	public MetalBall(float x, float y) {
		super(10, 50, material,"PlaneTexture.jpg", x, y);
		setCoefficientOfRestitution(0.1f);
		setMass(5);
		setOriginalMass(5);
	}
	
}
 