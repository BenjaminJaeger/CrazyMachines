package Objects.MovableObjects.ExternalObjects;

import Collisions.DynamicCollisionContext;
import Objects.MovableObjects.MoveableObject;
import RenderEngine.Core.Config;
import RenderEngine.Core.Shaders.Core.Material;

public abstract class ExternalObject extends MoveableObject{

	public ExternalObject(String[] files, Material material, float r, float g, float b, float x, float y) {
		super(files, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this);
	}
	
	public ExternalObject(String file, Material material, float r, float g, float b, float x, float y) {
		super(file, material, r,g,b, x, y);
		collisionContext = new DynamicCollisionContext(this);
	}


	@Override
	public void checkEdges() {
		if(y<=0) {
			setY(0);
			velocityY*=-1;
		}
		
		if(y>=Config.CANVAS_HEIGHT) {
			setY(Config.CANVAS_HEIGHT);
			velocityY*=-1;
		}
		
		if(x>=Config.CANVAS_WIDTH) {
			setX(Config.CANVAS_WIDTH);
			velocityX*=-1;
		}
		
		if(x<=0) {
			setX(0);
			velocityX*=-1;
		}
	}

}
