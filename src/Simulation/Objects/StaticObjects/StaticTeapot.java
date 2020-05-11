package Simulation.Objects.StaticObjects;

import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Collisions.Boundings.BoundingReader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class StaticTeapot extends StaticObject{

	public StaticTeapot(Material material, float r, float g, float b, float x, float y) {
		super("teapot", material, r, g, b, x, y);
		calculateConvexeHull();
	}

	private void calculateConvexeHull() {
		BoundingPolygon[] convexeHulls = BoundingReader.read("teapot", x, y);
		
		collisionContext.setBoundingPolygons(convexeHulls);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
