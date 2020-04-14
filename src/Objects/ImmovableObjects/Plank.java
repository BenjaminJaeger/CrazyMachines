package Objects.ImmovableObjects;

import Engine.Core.Shaders.Core.Material;

public class Plank extends ImmovableObject{

	public Plank(String[] files, Material material, float[][] colors, float x, float y) {
		super(files, material, colors, x, y);
	}

	@Override
	protected void collision() {
		
	}

	@Override
	public void checkEdges() {
		
	}

	@Override
	public void update() {

	}

}
