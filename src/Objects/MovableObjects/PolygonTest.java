package Objects.MovableObjects;

import Collisions.BoundingPolygon;
import Collisions.DynamicCollisionContext;
import RenderEngine.Core.Math.Vector2f;
import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.Sphere;

public class PolygonTest extends MoveableObject{

	
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 16f);
	
	public PolygonTest() {
		super(new Sphere(50,40), material, 0, 1, 0, 300, 300);
		collisionContext = new DynamicCollisionContext(this, new BoundingPolygon(0, 300, 300, new Vector2f[] {new Vector2f(150, 150) , new Vector2f(150, -150) , new Vector2f(-50, -50) , new Vector2f(-50, 50)}));
	}

	@Override
	public void checkEdges() {
		
	}

}
