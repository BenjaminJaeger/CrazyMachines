package Engine.Core.Models;

import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Primitive;

public class TriangleModel extends Model{
	
	private Material material;
	
	public TriangleModel(Primitive primitive, Material material, float[] colors, float x, float y) {
		super(primitive, colors, x, y);
		this.material=material;
	}

	public TriangleModel(String file, Material material, float[] colors, float x, float y) {
		super(file, colors, x, y);
		this.material=material;
	}
	
	public TriangleModel(Primitive primitive, Material material, float r,float g,float b, float x, float y) {
		super(primitive, r,g,b, x, y);
		this.material=material;
	}

	public TriangleModel(String file, Material material,  float r,float g,float b, float x, float y) {
		super(file, r,g,b, x, y);
		this.material=material;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
}