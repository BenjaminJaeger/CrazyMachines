package Objects;

import Engine.Core.Math.Vector2f;

public class Util {
	
	public static float getDistance(float x1,float y1,float x2,float y2) {
		//pythagoras theorem
		return (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	
	public static Vector2f rotate(float x,float y,float angle) {
		float cos = (float)Math.cos(angle);
		float sin = (float)Math.sin(angle);
		float vx = cos * x - sin * y;
		float vy = sin * x + cos * y;
		return new Vector2f(vx, vy);
	}
	
	public static Vector2f rotateArroundPoint(float cx,float cy,float angle,float rx,float ry) {
		angle *= (float)Math.PI/(float)180;
		
		cx-=rx;
		cy-=ry;
		
		Vector2f newP = rotate(cx, cy, angle);
		
		return new Vector2f(newP.x+rx, newP.y+ry);
	}
	
	public static void copyVec2Array(Vector2f[] source,Vector2f[] dest) {
		dest = new Vector2f[source.length];
		for (int i = 0; i < dest.length; i++) 
			dest[i]= new Vector2f(source[i].x, source[i].y);
	}
}