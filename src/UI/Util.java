package UI;

import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;

public class Util {

	public static boolean dragMode;
	
	public static boolean muteAudio;
	public static int soundVolume = 50;
	
	public static SwingNode canvasWrapper;
	public static ColorAdjust colorAdjust = new ColorAdjust();

	public static Stage primaryStage;
	public static Scene mainScene;
	public static int currentLevel;
	
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
	
	public static float convertMouseX(double ex) {
		float x = (float)ex - Config.CANVAS_WIDTH/2;	
		x*=0.75f;
		return x;
	}
	
	public static float convertMouseY(double ey) {		
		float y = Config.CANVAS_WIDTH/2 - (float)ey;	
		y*=0.75f;
		return y;
	}
	
}
