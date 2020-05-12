package MAIN;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.awt.GLJPanel;

import javafx.embed.swing.SwingNode;

public class Util {

	public static boolean objectDragged;
	
	public static float canvasX;
	public static float canvasY;
	
	public static GLJPanel canvas;
	public static SwingNode canvasWrapper;
	
	public static void trackCanvasMouse(){
		canvas.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				canvasX = (float)e.getX();
				canvasY = (float)e.getY();
			}
			public void mouseDragged(MouseEvent e) {}
		});
	}
	
}
