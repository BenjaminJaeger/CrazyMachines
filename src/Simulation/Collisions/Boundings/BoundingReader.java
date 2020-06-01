package Simulation.Collisions.Boundings;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Math.Vector2f;

public class BoundingReader {

	public static ArrayList<ArrayList<Vector2f>> hulls;
	public static ArrayList<Vector2f> centers;
	
	public static BoundingPolygon[] read(String file,float x ,float y) {
		
		hulls = new ArrayList<ArrayList<Vector2f>>();
		centers =  new ArrayList<Vector2f>();
		readFile(file);
		
		BoundingPolygon[] boundingPolygons = createBoundingPolygons(x, y);
	
		return boundingPolygons;
	}
	
	private static void readFile(String file) {
		ArrayList<Vector2f> currentHull = new ArrayList<Vector2f>();
				
		try	{	
			
			Scanner sc = new Scanner(new File("res/Models/"+file+".txt"));
				
			int counter = 0;
			
			while (sc.hasNext()){	
				String scannedLine=sc.nextLine();
				
				if(scannedLine.startsWith("#New")) {
					if(counter > 0) {
						hulls.add(currentHull);
						currentHull = new ArrayList<Vector2f>();
					}
				}else if(scannedLine.startsWith("o ")) {
					String[] v = scannedLine.substring(2).split("/");
					centers.add(new Vector2f(Float.parseFloat(v[0]) *Config.CANVAS_WIDTH , Float.parseFloat(v[1]) * Config.CANVAS_HEIGHT )); 	
				}else {
					String[] v = scannedLine.substring(2).split("/");
					currentHull.add(new Vector2f(Float.parseFloat(v[0]) *Config.CANVAS_WIDTH , Float.parseFloat(v[1]) * Config.CANVAS_HEIGHT )); 			
				}
			
				counter++;
			}
			
			hulls.add(currentHull);
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	
	private static BoundingPolygon[] createBoundingPolygons(float x ,float y) {
		BoundingPolygon[] boundingPolygons = new BoundingPolygon[hulls.size()];
		
		for (int i = 0; i < boundingPolygons.length; i++) {
			Vector2f[] points = new Vector2f[hulls.get(i).size()];
			for (int j = 0; j < points.length; j++) 
				points[j] = hulls.get(i).get(j);
							
			boundingPolygons[i] = new BoundingPolygon(x, y, points,centers.get(i).x,centers.get(i).y);
		}
		
		return boundingPolygons;
	}
}
