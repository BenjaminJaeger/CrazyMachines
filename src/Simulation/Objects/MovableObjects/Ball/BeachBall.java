package Simulation.Objects.MovableObjects.Ball;

import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class BeachBall extends Ball{
	
	public static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(0.1f,0.1f,0.1f), 4f);

////////////////////
////Constructors////
////////////////////
	public BeachBall(float x, float y) {
		super(40, 50, material,"BeachBall.png", x, y);
	}
	
}
 