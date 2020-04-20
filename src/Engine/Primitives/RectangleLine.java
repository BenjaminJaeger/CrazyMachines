package Engine.Primitives;

import Engine.Core.Config;

public class RectangleLine extends Primitive{

	float width;
	float height;
		
	public RectangleLine(float width,float height) {
		this.height=height/Config.CANVAS_HEIGHT;
		this.width=width/Config.CANVAS_WIDTH;
		constructMesh();	
	}
	
	public RectangleLine(float size) {
		this.height=size/Config.CANVAS_HEIGHT;
		this.width=size/Config.CANVAS_WIDTH;
		constructMesh();	
	}
		
	
	protected void constructMesh() {
		float[] vertices = {
			width/2,height/2,Config.BOUNDING_DISTANCE,	
			-width/2,height/2,Config.BOUNDING_DISTANCE,
			-width/2,-height/2,Config.BOUNDING_DISTANCE,
			width/2,-height/2,Config.BOUNDING_DISTANCE,
		};		
		this.vertices=vertices;
	}
	
}
