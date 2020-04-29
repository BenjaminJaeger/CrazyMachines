package Collisions.Boundings;

import RenderEngine.Core.Math.Vector2f;
import RenderEngine.Core.Models.LineModel;
import RenderEngine.Primitives.PolygonLine;

public class BoundingPolygon extends Bounding{
	
	Vector2f[] originalPoints;
	Vector2f[] points;

////////////////////
////Constructors////
////////////////////
	public BoundingPolygon(float offset, float x, float y,Vector2f[] points) {
		super(offset, x, y);
		this.originalPoints = points;
		
		//copy Array and add current position
		this.points = new Vector2f[originalPoints.length];
		for (int i = 0; i < this.points.length; i++) 
			this.points[i]= new Vector2f(originalPoints[i].x+x, originalPoints[i].y+y);
		
		model = new LineModel(new PolygonLine(points),0,1,0,x,y);
	}

	
/////////////////
////Collision////
/////////////////
	
	//http://www.dyn4j.org/2010/01/sat/
	public boolean checkCollision(BoundingPolygon polygon) {
		for (BoundingPolygon polyontmp : new BoundingPolygon[] {this,polygon}) {
			
			for (int i = 0; i < polyontmp.getPoints().length; i++){
				
				//calculate normal
			    Vector2f p1 = polyontmp.getPoints()[i];
			    Vector2f p2 = polyontmp.getPoints()[i + 1 == polyontmp.getPoints().length ? 0 : i + 1];
	
			    Vector2f normal = new Vector2f(p2.y - p1.y, p1.x - p2.x);
	
			    //project Shape onto axis
			    float minA = 0;
			    float maxA = 0;
			    for(Vector2f p : points){
			    	float projected = normal.x * p.x + normal.y * p.y;
			    	if (minA == 0 || projected < minA)
			    		minA = projected;
			    	if (maxA == 0 || projected > maxA)
			    		maxA = projected;
			    }
			    
			    float minB = 0;
			    float maxB = 0;
			    for(Vector2f p : polygon.getPoints()){
			    	float projected = normal.x * p.x + normal.y * p.y;
			    	if (minB == 0 || projected < minB)
			    		minB = projected;
			    	if (maxB == 0 || projected > maxB)
			    		maxB = projected;
			    }
			    

			    if (maxA < minB || maxB < minA)
			    	return false;
			    
			}
		}
		
	 	return true;
	}
	
	
/////////////////////////
////Getters & Setters////
/////////////////////////
	public Vector2f[] getPoints() {
		return points;
	}
	
	public void setX(float x) {
		super.setX(x);	
		
		//copy Array
		points = new Vector2f[originalPoints.length];
		for (int i = 0; i < points.length; i++) 
			points[i]= new Vector2f(originalPoints[i].x, originalPoints[i].y);
		
		for (Vector2f p : points) {
			p.rotate(-rotation);
			p.x+=x;		
			p.y+=y;
		}
	}
	
	public void setY(float y) {
		super.setY(y);
		
		//copy Array
		points = new Vector2f[originalPoints.length];
		for (int i = 0; i < points.length; i++) 
			points[i]= new Vector2f(originalPoints[i].x, originalPoints[i].y);
		
		for (Vector2f p : points) {
			p.rotate(-rotation);
			p.x+=x;		
			p.y+=y;
		}
	}
	
	public void setRotation(float rotation) {
		super.setRotation(rotation);

		//copy Array
		points = new Vector2f[originalPoints.length];
		for (int i = 0; i < points.length; i++) 
			points[i]= new Vector2f(originalPoints[i].x, originalPoints[i].y);
		
		for (Vector2f p : points) {			
			p.rotate(-rotation);
			p.x+=x;		
			p.y+=y;
		}
	}

}