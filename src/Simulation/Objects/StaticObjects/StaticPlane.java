package Simulation.Objects.StaticObjects;

import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Collisions.Boundings.BoundingReader;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class StaticPlane extends StaticObject{

	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 4f);
	
	public StaticPlane(float x, float y) {
		super("PlaneTriangles","PlaneTexture.jpg", material, x, y);
		
		BoundingPolygon[] convexeHulls = BoundingReader.read("Plane", x, y);
		collisionContext.setBoundingPolygons(convexeHulls);
		models[0].setScale(0.1f);
	}

	@Override
	public void update() {
		
	}

}
