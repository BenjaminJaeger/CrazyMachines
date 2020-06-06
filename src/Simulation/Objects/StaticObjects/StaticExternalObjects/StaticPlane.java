package Simulation.Objects.StaticObjects.StaticExternalObjects;

import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class StaticPlane extends StaticExternalObject{

	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 4f);
	
	public StaticPlane(float x, float y) {
		super("PlaneTriangles","PlaneTriangles","PlaneTexture.jpg", material, x, y);
		setScale(0.4f);
		setOriginalscale(0.4f);
	}

	@Override
	public void update() {
		
	}

}
