package MAIN;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import Simulation.RenderEngine.Core.Lights.AmbientLight;
import Simulation.RenderEngine.Core.Lights.DirectionalLight;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Renderer.Renderer;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import UI.CreateTabPaneEvents;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Simulation implements GLEventListener{

	private FPSAnimator animator;
	
	private Renderer renderer;
	private Camera camera;
	
	private LineModel tmp;
	private BasicShader tmpShader;
	
	public void initialize(StackPane root,BorderPane layout) {	
		Util.canvas = new GLJPanel(new GLCapabilities(GLProfile.getDefault()));
		Util.canvas.addGLEventListener(this);		
		Util.trackCanvasMouse();
		
		Util.canvas.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(java.awt.event.MouseEvent e) {
				if(Util.objectDragged) 
					CreateTabPaneEvents.scaleObject(e);
			}
			public void mouseDragged(java.awt.event.MouseEvent e) {}
		});

		Util.canvas.addMouseListener(new MouseListener() {
			public void mouseReleased(java.awt.event.MouseEvent e) {}
			public void mousePressed(java.awt.event.MouseEvent e) {}
			public void mouseExited(java.awt.event.MouseEvent e) {}
			public void mouseEntered(java.awt.event.MouseEvent e) {}
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(Util.objectDragged) {
					Util.objectDragged=false;
					GameObject.allObjects.get(GameObject.allObjects.size()-1).setOriginalscaleX(GameObject.allObjects.get(GameObject.allObjects.size()-1).getScaleX());
					GameObject.allObjects.get(GameObject.allObjects.size()-1).setOriginalscaleY(GameObject.allObjects.get(GameObject.allObjects.size()-1).getScaleY());
					GameObject.allObjects.get(GameObject.allObjects.size()-1).setOriginalrotation(GameObject.allObjects.get(GameObject.allObjects.size()-1).getRotation());	
				}
			}
		});
		
		Util.canvasWrapper = new SwingNode();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Util.canvasWrapper.setContent(Util.canvas);
			}
		});	
			
		layout.setCenter(Util.canvasWrapper);
				
		animator = new FPSAnimator(Util.canvas, 60);
		animator.start();
	}
	
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		for (GameObject object : GameObject.allObjects) 
			renderer.render(object, object.getShader()); 
		
		renderer.render(tmp,tmpShader);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		animator.stop();
		SimulationControler.pause();
	}

	
	@SuppressWarnings("unused")
	@Override
	public void init(GLAutoDrawable arg0) {
		Config.BACK_FACE_CULLING = false;
		Config.BACKGROUND_COLOR = new Vector3f(0.4f,0.5f,0.4f);
		Config.CANVAS_HEIGHT = Util.canvas.getHeight();
		Config.CANVAS_WIDTH = Util.canvas.getWidth();
			
		camera= new Camera(Util.canvas);
		camera.setZ(1f);
		
		renderer = new Renderer(camera);	

		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		tmp = new LineModel(new float[]{0,0,0}, 0, 0, 0, 0, 0); 
		tmpShader= new BasicShader("Line");
	}
	

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		gl.glViewport(0, 0, width, height);
		Config.CANVAS_HEIGHT=height;
		Config.CANVAS_WIDTH=width;
		renderer.updateProjectionMatrix();
	}
	
}
