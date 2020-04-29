package Objects.MovableObjects.ExternalObjects;

import Collisions.BoundingCreator.ConcaveHull;
import Collisions.Boundings.BoundingPolygon;
import RenderEngine.Core.Shaders.Core.Material;

public class Teapot extends ExternalObject{

	public Teapot(Material material, float r, float g, float b, float x, float y) {
		super("teapot", material, r, g, b, x, y);
		calculateConvexeHull();
	}

	private void calculateConvexeHull() {
		BoundingPolygon[] convexeHulls = new BoundingPolygon[models.length];
		
		for (int i = 0; i < models.length; i++) 
			convexeHulls[i] = new  BoundingPolygon(0, x, y, ConcaveHull.calculate(models[i],28,100));
		
		collisionContext.setBoundingPolygons(convexeHulls);
	}
}
