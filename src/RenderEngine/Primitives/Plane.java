package RenderEngine.Primitives;

import RenderEngine.Core.Config;

public class Plane extends Primitive{

	float width;
	float height;
		
	public Plane(float width,float height) {
		this.height=height/Config.CANVAS_HEIGHT;
		this.width=width/Config.CANVAS_WIDTH;
		constructMesh();	
	}
	
	public Plane(float size) {
		this.height=size/Config.CANVAS_HEIGHT;
		this.width=size/Config.CANVAS_WIDTH;
		constructMesh();	
	}
		
	
	protected void constructMesh() {
		float[] vertices = {
			-width/2,height/2,0,
			-width/2,-height/2,0,			
			width/2,height/2,0,				
			width/2,-height/2,0
		};		
		this.vertices=vertices;
			
		int[] indices ={
				0,1,2,
				2,1,3				
		};
		this.indices = indices;
	}
	
}
