package RenderEngine.Primitives;

import RenderEngine.Core.Config;
import RenderEngine.Core.Math.Vector2f;

public class PolygonLine extends Primitive{

	Vector2f[] points;
	
	public PolygonLine(Vector2f[] points) {
		this.points=points;
		constructMesh();
	}
	
	@Override
	protected void constructMesh() {
		vertices = new float[points.length*3];
		for (int i = 0; i < vertices.length; i+=3) {
			vertices[i] = points[i/3].x / Config.CANVAS_WIDTH;
			vertices[i+1] = points[i/3].y / Config.CANVAS_WIDTH;
			vertices[i+2] = Config.BOUNDING_DISTANCE;
		}
		
	}

}
