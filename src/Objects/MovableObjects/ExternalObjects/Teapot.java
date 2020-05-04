package Objects.MovableObjects.ExternalObjects;

import RenderEngine.Core.Shaders.Core.Material;

public class Teapot extends ExternalObject{

	public Teapot(Material material, float r, float g, float b, float x, float y) {
		super("teapot", material, r, g, b, x, y);
		calculateConvexeHull();
	}

	private void calculateConvexeHull() {
	
	}
}
