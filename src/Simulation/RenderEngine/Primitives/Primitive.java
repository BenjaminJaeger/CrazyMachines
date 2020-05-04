package Simulation.RenderEngine.Primitives;

public abstract class Primitive {

	protected float[] vertices;
	protected int[] indices;
	
	protected abstract void constructMesh();

	public float[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}
}
