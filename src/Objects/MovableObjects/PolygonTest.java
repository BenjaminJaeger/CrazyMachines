package Objects.MovableObjects;

import Engine.Core.Math.Vector2f;
import Engine.Core.Math.Vector3f;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Sphere;
import Objects.MovableObjects.Collisions.BoundingPolygon;
import Objects.MovableObjects.Collisions.DynamicCollisionContext;

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
