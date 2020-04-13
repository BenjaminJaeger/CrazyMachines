package Objects.MovableObjects.Ball;


import java.util.ArrayList;

import Engine.Core.Config;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.Sphere;
import Objects.Util;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Box.Box;

public class Ball extends MoveableObject{
	
	public static ArrayList<Ball> allBalls = new ArrayList<Ball>();
	
	private float radius;
	
	public Ball(float radius,int resolution, Material material, float[] colors, float x, float y) {
		super(new Sphere(resolution,radius), material, colors, x, y);
		this.radius=radius;
		allBalls.add(this);
	}
	
	@Override
	public void update() {
		
		applyForce(0, 1f);
		
		collission();
		
		checkEdges();
		
		increaseVelocity(accelerationX, accelerationY);
		increasePosition(velocityX, velocityY);
		resetAcceleration();
		
		accelerationX = -velocityX*0.05f;
		accelerationY = -velocityY*0.05f;
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

	
	public void collission() {
		for (Ball ball : allBalls) 
			if(id!=ball.getId() && checkCollission(ball)) 
				respondToCollission(ball);
	}
	
	public void respondToCollission(Ball ball) {
		removeCollission(ball);
		calculateNewVelocity(ball);		
	}
	
	public void removeCollission(Ball ball) {
		float distance = Util.getDistance(this.x, this.y, ball.getX(), ball.getY());
		
		float overlap = 0.5f*(distance-ball.getRadius()-radius);
		
		ball.setX(ball.getX()-overlap*(ball.getX()-x)/distance);
		ball.setY(ball.getY()-overlap*(ball.getY()-y)/distance);
		
		x += overlap*(ball.getX()-x)/distance;
		y += overlap*(ball.getY()-y)/distance;
	}
	
	public void calculateNewVelocity(Ball ball) {
		//1D
//		//calculate new Velocity of this
//		float addedMass = mass+ball.getMass();
//		float suptractedMass = mass-ball.getMass();	
//		float newVelocityXThis = (suptractedMass * velocityX + ball.getMass()*2*ball.getVelocityX()) / addedMass;
//				
//		//calculate new velocity of other ball
//		suptractedMass = ball.getMass()-mass;
//		float newVelocityXOther = (suptractedMass*ball.getVelocityX() + 2*mass*velocityX)/addedMass;
//				
//		//set new Velocity
//		ball.setVelocityX(newVelocityXOther);
//		velocityX = newVelocityXThis;
		
		//2D
		float distance =  Util.getDistance(this.x, this.y, ball.getX(), ball.getY());
		
		//this = b1
		//ball = b2
		
		float nx = (ball.getX()-x)/distance;
		float ny = (ball.getY()-y)/distance;
		
		
		float tx = -ny;
		float ty = nx;
	
		float dpTan1 = velocityX*tx + velocityY*ty;
		float dpTan2 = ball.getVelocityX()*tx + ball.getVelocityY()*ty;
		
		float dpNorm1 = velocityX *nx + velocityY * ny;
		float dpNorm2 = ball.getVelocityX() *nx + ball.getVelocityY() * ny;
		
		float m1 = (dpNorm1 * (mass - ball.getMass()) + 2 * ball.getMass() * dpNorm2) / (mass + ball.getMass());
		float m2 = (dpNorm2 * (ball.getMass() - mass) + 2 * mass * dpNorm1) / (mass + ball.getMass());
		
		velocityX = tx*dpTan1 + nx*m1;
		velocityY = ty*dpTan1 + ny*m1;
		
		ball.setVelocityX(tx * dpTan2 + nx * m2);
		ball.setVelocityY(ty * dpTan2 + ny * m2);
	}
	

	public boolean checkCollission(Ball ball) {
		return radius+ball.getRadius()>Util.getDistance(this.x, this.y, ball.getX(), ball.getY());
	}
	
	
public boolean checkCollission(Box box) {
		
		return false;
	}
	
	public float getRadius() {
		return radius;
	}
	
}
