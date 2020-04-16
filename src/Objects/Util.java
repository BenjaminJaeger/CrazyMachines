package Objects;

import Engine.Core.Math.Vector2f;

public class Util {
	
	public static float getDistance(float x1,float y1,float x2,float y2) {
		//pythagoras theorem
		return (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	
	public static Vector2f rotate(float x,float y,float angle) {
		float vx = (float)Math.cos(angle) * x - (float)Math.sin(angle) * y;
		float vy = (float)Math.sin(angle) * x + (float)Math.cos(angle) * y;
		return new Vector2f(vx, vy);
	}
	
	

}
 