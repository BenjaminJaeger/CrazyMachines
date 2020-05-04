package Simulation.Objects.MovableObjects.ExternalObjects;

import Simulation.Collisions.Boundings.BoundingPolygon;
import Simulation.Collisions.Boundings.BoundingReader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;

public class Icosahedron extends ExternalObject{

	public Icosahedron(Material material, float r, float g, float b, float x, float y) {
		super("icosahedron", material, r, g, b, x, y);
		calculateConvexeHull();
	}

	private void calculateConvexeHull() {
		BoundingPolygon[] convexeHulls = BoundingReader.read("Icosahedron", x, y);
		
		collisionContext.setBoundingPolygons(convexeHulls);
	}
}
