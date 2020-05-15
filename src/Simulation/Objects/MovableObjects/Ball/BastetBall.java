package Simulation.Objects.MovableObjects.Ball;

import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class BastetBall extends Ball{
	
	public static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(0.1f,0.1f,0.1f), 4f);

////////////////////
////Constructors////
////////////////////
	public BastetBall(float x, float y) {
		super(30, 30, material,"basketball.png", x, y);
	}
	
}
 