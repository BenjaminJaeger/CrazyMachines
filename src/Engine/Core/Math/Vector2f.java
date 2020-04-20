package Engine.Core.Math;

/**
 * 
 * 2 component vector
 * @author Simon Weck
 */
public class Vector2f {
	
	public float x;
	public float y;
	
	public Vector2f(float x,float y) {
		this.x=x;
		this.y=y;
	}

	/**
	 * divides each component through the devisor
	 * @param devisor
	 */
	public void divide(float devisor) {
		x/=devisor;
		y/=devisor;
	}

	public Vector2f subtract(Vector2f v) {	
		return new Vector2f(x-v.x, y-v.y);
	}

	public Vector2f perp() {
		return new Vector2f(-y,x);
	}

	public void rotate(float rotation) {
		rotation *= (float)Math.PI/(float)180;
		
		float cos = (float)Math.cos(rotation);
		float sin = (float)Math.sin(rotation);
		
		float newX = cos * x - sin * y;
		float newY = sin * x + cos * y;
		
		x = newX;
		y = newY;
	}
	
}