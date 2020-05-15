package MAIN;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.awt.GLJPanel;

import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.TabPane;

public class Util {

	public static boolean objectDragged;
	
	public static float canvasX;
	public static float canvasY;
		
	public static GLJPanel canvas;
	public static SwingNode canvasWrapper;

	public static TabPane tabPane;
	
	public static void trackCanvasMouse(){
		canvas.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				canvasX = (float)e.getX();
				canvasY = (float)e.getY();
			}
			public void mouseDragged(MouseEvent e) {}
		});
	}
	
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
