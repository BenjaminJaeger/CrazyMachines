package MAIN;

import java.util.ConcurrentModificationException;

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
import UI.Util;



public class Simulation implements GLEventListener{

	private FPSAnimator animator;
	
	private GLJPanel canvas;
	private Renderer renderer;
	private Camera camera;
	
	private LineModel tmp;
	private BasicShader tmpShader;
	
	public Simulation() {
		canvas = new GLJPanel(new GLCapabilities( GLProfile.getDefault()));	   
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Util.canvasWrapper.setContent(canvas);	
		    }
		});	
	
		canvas.addGLEventListener(this);		
		animator = new FPSAnimator(canvas, 60);
	  	animator.start();
	}
	
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		try {
			for (GameObject object : GameObject.allObjects) 
				renderer.render(object, object.getShader()); 
		} catch (ConcurrentModificationException e) {}
		
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
		Config.CANVAS_HEIGHT =canvas.getHeight();
		Config.CANVAS_WIDTH = canvas.getWidth();
			
		camera= new Camera(canvas);
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
