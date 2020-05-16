package UI;

import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import javafx.embed.swing.SwingNode;

public class Util {

	public static float canvasX;
	public static float canvasY;
	
	public static SwingNode canvasWrapper;
	
	public static float convertMouseX(double ex,Camera camera) {
		float x = ((float)ex - Config.CANVAS_WIDTH/2) / Config.CANVAS_WIDTH;		  			
		x=x*camera.getZ()*0.75f +camera.getX() / Config.CANVAS_WIDTH;
		return x;
	}
	
	public static float convertMouseY(double ey,Camera camera) {		
		float y = (Config.CANVAS_WIDTH/2 - (float)ey) / Config.CANVAS_WIDTH;	
		y=y*camera.getZ()*0.75f + camera.getY() / Config.CANVAS_WIDTH;
		return y;
	}
	
}
