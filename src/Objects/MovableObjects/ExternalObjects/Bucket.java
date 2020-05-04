package Objects.MovableObjects.ExternalObjects;

import Collisions.Boundings.BoundingPolygon;
import Collisions.Boundings.BoundingReader;
import RenderEngine.Core.Shaders.Core.Material;

public class Bucket extends ExternalObject{

	public Bucket(Material material, float r, float g, float b, float x, float y) {
		super("Bucket", material, r, g, b, x, y);
		calculateConvexeHull();
	}

	private void calculateConvexeHull() {
		BoundingPolygon[] convexeHulls = BoundingReader.read("Bucket", x, y);
		
		collisionContext.setBoundingPolygons(convexeHulls);
	}
}
