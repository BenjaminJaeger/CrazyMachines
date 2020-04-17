package Objects.MovableObjects.Collisions;

import Engine.Core.Models.LineModel;
import Engine.Core.Shaders.Core.BasicShader;
import Objects.GameObject;

public class Bounding {

	protected GameObject object;
	protected float offset;
	
	protected LineModel model;
	protected static BasicShader shader = new BasicShader("Line");
	
	public Bounding (GameObject object,float offset) {
		this.object=object;
		this.offset=offset;
	}
	
	public LineModel getModel() {
		return model;
	}

	public BasicShader getShader() {
		return shader;
	}
	
}
