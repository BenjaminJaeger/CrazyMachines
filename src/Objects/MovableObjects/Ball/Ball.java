package Objects.MovableObjects.Ball;


import java.util.ArrayList;

import Engine.Core.Config;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Sphere;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Box.Box;
import Objects.MovableObjects.Collisions.CircleCollision;
import Objects.MovableObjects.Collisions.RectangleCollision;

public class Ball extends MoveableObject{
	
	public static ArrayList<Ball> allBalls = new ArrayList<Ball>();
	
	private float radius;
	
	public Ball(float radius,int resolution, Material material, float[] colors, float x, float y) {
		super(new Sphere(resolution,radius), material, colors, x, y);
		this.radius=radius;
		allBalls.add(this);
	}
	
	public void collision() {
		//Collision with Ball
		for (Ball ball : allBalls) 
			if(id!=ball.getId() && CircleCollision.checkCollision(ball,this)) 
				respondToCollision(ball);
		
		//Collision with Box
		for (Box box : Box.allBoxes) 
			if (RectangleCollision.checkCollision(box,this)) 
				respondToCollision(box);
	}

	public void checkEdges() {
		if(y+radius>=Config.CANVAS_HEIGHT) {
			setY(Config.CANVAS_HEIGHT-radius);
			velocityY*=-1;
		}
		
		if(y-radius<=0) {
			setY(radius);
			velocityY*=-1;
		}
		
		if(x+radius>=Config.CANVAS_WIDTH) {
			setX(Config.CANVAS_WIDTH-radius);
			velocityX*=-1;
		}
		
		if(x-radius<=0) {
			setX(radius);
			velocityX*=-1;
		}
	}

	public void respondToCollision(Box box) {
		RectangleCollision.removeCollision(box,this);
		RectangleCollision.calculateNewVelocity(box,this);		
	}
	
	public void respondToCollision(Ball ball) {
		CircleCollision.removeCollision(ball, this);	
		CircleCollision.calculateNewVelocity(ball, this);
	}
	
	
	public float getRadius() {
		return radius;
	}
	
}
