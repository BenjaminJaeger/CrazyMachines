package Objects.MovableObjects.Collisions;

import Engine.Core.Models.LineModel;
import Engine.Core.Shaders.Core.BasicShader;

public class Bounding {

	protected float offset;
	protected float x,y;
	
	protected LineModel model;
	protected static BasicShader shader = new BasicShader("Line");
	
	public Bounding (float offset,float x, float y) {
		this.offset=offset;
		this.x=x;
		this.y=y;
	}
	
	public LineModel getModel() {
		return model;
	}

	public BasicShader getShader() {
		return shader;
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	public float getX() {
		return x;
	}

	public void setXY(float x,float y) {
		this.x = x;
		model.setX(x);
		this.y=y;
		model.setY(y);
	}
	
	public void setX(float x) {
		this.x = x;
		model.setX(x);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		model.setY(y);
	}
	
}
