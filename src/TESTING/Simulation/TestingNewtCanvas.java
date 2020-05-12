package TESTING.Simulation;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.javafx.NewtCanvasJFX;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.MovableObjects.ExternalObjects.Teapot;
import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import Simulation.RenderEngine.Core.Lights.AmbientLight;
import Simulation.RenderEngine.Core.Lights.DirectionalLight;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Renderer.Renderer;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.CircleLine;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestingNewtCanvas extends Application implements GLEventListener{

	private boolean experimental = true;
	private GLJPanel canvas1;
	private GLWindow canvas2;
	
	private FPSAnimator animator;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private MoveableObject teapot;

	private ArrayList<LineModel> test = new ArrayList<LineModel>();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();

		primaryStage.setTitle("Newt Canvas Test");
		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.show();	
		
		
		final GLCapabilities capabilities = new GLCapabilities( GLProfile.getDefault());
	    
		if(experimental) {
			Display jfxNewtDisplay = NewtFactory.createDisplay(null, false);
			Screen screen = NewtFactory.createScreen(jfxNewtDisplay, 0);
			canvas2 = GLWindow.create(screen, capabilities);
			NewtCanvasJFX glCanvas = new NewtCanvasJFX(canvas2);
			glCanvas.setWidth(800);
		    glCanvas.setHeight(800);		     
			root.getChildren().add(glCanvas);
			
			canvas2.addGLEventListener(this);		
			animator = new FPSAnimator(canvas1, 60);
		  	animator.start();
			
		}else {
			canvas1 = new GLJPanel(capabilities);	    
			SwingNode swingNode = new SwingNode();		 
			root.getChildren().add(swingNode);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
			    	swingNode.setContent(canvas1);	
			    }
			});	
			canvas1.addGLEventListener(this);		
			animator = new FPSAnimator(canvas1, 60);
		  	animator.start();
		}	
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		teapot.update();
		renderer.render(teapot, shader); 
		
		for (LineModel object : test) 
			renderer.render(object,shader);			
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		animator.stop();
	}

	@SuppressWarnings("unused")
	@Override
	public void init(GLAutoDrawable arg0) {
		Config.BACK_FACE_CULLING = false;
		Config.BACKGROUND_COLOR = new Vector3f(0.4f,0.5f,0.4f);
		
		if (experimental) {
			Config.CANVAS_HEIGHT = canvas2.getHeight();
			Config.CANVAS_WIDTH = canvas2.getWidth();
			camera= new Camera(canvas2);
		}else {
			Config.CANVAS_HEIGHT = canvas1.getHeight();
			Config.CANVAS_WIDTH = canvas1.getWidth();
			camera= new Camera(canvas1);
		}
		
		camera.setZ(10f);
		
		renderer = new Renderer(camera);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

		teapot = new Teapot(basicMaterial, 1, 0, 0, 200, 2000);
	
		test.add(new LineModel(new CircleLine(0, 0), 0,0,0,0, 0));
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