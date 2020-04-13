package Engine.Primitives;

import Engine.Core.Config;

public class Sphere extends Primitive {

	private int resolution;
	private float radius;
	
	public Sphere(int resolution,float radius) {
		this.resolution=resolution;
		this.radius=radius/Config.CANVAS_WIDTH;
		constructMesh();
	}
		
	public void constructMesh() {
		
		vertices = new float[resolution*3+3];
		vertices[0]=0;
		vertices[1]=0;
		vertices[2]=0;
		
		
		
		for (int i = 3; i < vertices.length; i+=3) {
			float angle = (float)(i-3)/(vertices.length-3)*2*(float)Math.PI;
			vertices[i] = (float)Math.cos(angle)*radius;
			vertices[i+1] = (float)Math.sin(angle)*radius;
			vertices[i+2] = 0;
		}
		
		indices = new int[resolution*3];
		
		for (int i = 0; i < indices.length; i+=3) {
			indices[i]=0;
			indices[i+2]=i/3+2;
			indices[i+1]=i/3+1;		
			if (i==indices.length-3) 
				indices[i+2]=1;
		}
		
			
	}
	
		

}
