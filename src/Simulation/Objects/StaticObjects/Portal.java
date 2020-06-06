package Simulation.Objects.StaticObjects;
import java.util.Timer;
import java.util.TimerTask;

import Simulation.Collisions.DynamicCollisionContext;
import Simulation.Collisions.Boundings.BoundingRectangle;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Plane;
import UI.ObjectTransformer.ObjectTransformer;

public class Portal extends StaticObject{

	private float offset = 60;
	private Portal portal;
	private boolean activated = true;
	
	private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(0.1f,0.1f,0.1f), 4f);

	public Portal(float x, float y) {
		super(new Plane(100, 100, 0), "portal.png", material, x, y);			
		createCollsionContext();
		setRotation((float)Math.random()*360);
	}

	protected void createCollsionContext() {
		collisionContext = new DynamicCollisionContext(this,new BoundingRectangle(x, y, 40));
		objectTransformer = new ObjectTransformer(this);
	}
	
	public void setPortal(Portal portal) {
		this.portal = portal;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	@Override
	public void update() {
		models[0].increaseRotation(0, 0, 1);
	
		if (activated) 
			for (GameObject gameObject : allObjects) 
				if(gameObject instanceof MoveableObject) {
					MoveableObject object = (MoveableObject)gameObject;
					
					if(object.getX() <= x+offset && object.getX() >= x-offset && object.getY() <= y+offset && object.getY() >= y-offset) {
						object.setX(portal.getX());
						object.setY(portal.getY());
						activated=false;
						portal.setActivated(false);
						
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
						    public void run() {
						    	activated=true;
								portal.setActivated(true);   
						    }
						},2000);	
						
					}	
					
				}
	}

}
