package ComputerGraphics;

import javax.swing.SwingUtilities;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.javafx.NewtCanvasJFX;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

import javafx.embed.swing.SwingNode;
import javafx.scene.layout.Pane;

public class ComputerGraphicsCanvas {

private static boolean experimental = false;

	private static ComputerGraphics graphics;
	private static SwingNode swingNode;
	
	public static void addCanvas(Pane root) {	
		final GLCapabilities capabilities = new GLCapabilities( GLProfile.getDefault());
			    
		if(experimental) {
			Display jfxNewtDisplay = NewtFactory.createDisplay(null, false);
			Screen screen = NewtFactory.createScreen(jfxNewtDisplay, 0);
			GLWindow glWindow1 = GLWindow.create(screen, capabilities);
			NewtCanvasJFX glCanvas = new NewtCanvasJFX(glWindow1);
			glCanvas.setWidth(800);
		    glCanvas.setHeight(600);		     
			root.getChildren().add(glCanvas);
			new ComputerGraphics(glWindow1);    		
		}else {
			GLJPanel canvas = new GLJPanel(capabilities);
		    
			swingNode = new SwingNode();
			    	
			root.getChildren().add(swingNode);
		    	
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
			    	swingNode.setContent(canvas);
			    	graphics = new ComputerGraphics(canvas);    			
			    }
			});
		}	
				
	}
}
