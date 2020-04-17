package Objects.MovableObjects.Collisions;

import Engine.Core.Models.LineModel;
import Objects.GameObject;

public class Bounding {

	protected GameObject object;
	protected float offset;
	
	protected LineModel model;
	
	public Bounding (GameObject object,float offset) {
		this.object=object;
		this.offset=offset;
	}
}
