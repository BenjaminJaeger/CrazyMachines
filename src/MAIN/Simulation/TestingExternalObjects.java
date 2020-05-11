package MAIN.Simulation;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import Simulation.Util;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.MovableObjects.Ball.MetallBall;
import Simulation.Objects.StaticObjects.StaticBucket;
import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import Simulation.RenderEngine.Core.Lights.AmbientLight;
import Simulation.RenderEngine.Core.Lights.DirectionalLight;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Renderer.Renderer;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestingExternalObjects extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private MoveableObject model1;
	private GameObject model2;
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();

		primaryStage.setTitle("Circle-Polygon Collision");
		primaryStage.setScene(new Scene(root, 900, 900));
		primaryStage.show();	
			
		//JFX Code für Canvas
		final GLCapabilities capabilities = new GLCapabilities( GLProfile.getDefault());
		canvas = new GLJPanel(capabilities);	    
		SwingNode swingNode = new SwingNode();		 
		root.getChildren().add(swingNode);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		    	swingNode.setContent(canvas);	
		    }
		});	
		canvas.addGLEventListener(this);		
		animator = new FPSAnimator(canvas, 60);
	  	animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		model1.update();
		renderer.render(model1, shader);
		model2.update();
		renderer.render(model2, shader);
		
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
		Config.CANVAS_HEIGHT = canvas.getHeight();
		Config.CANVAS_WIDTH = canvas.getWidth();
		
		
		camera= new Camera(canvas);
		camera.setZ(1f);
		
		renderer = new Renderer(camera);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

		model1 = new MetallBall(10, 30, 0, 0, 1, 0, 100);
		model1.renderBounding(true);
		model1.setVelocityY(Util.getRandomVelocity(10));
		model1.setVelocityX(Util.getRandomVelocity(10));
				
		model2 = new StaticBucket(basicMaterial, 0.8f, 0.5f, 0.2f, 0, 0);
		model2.renderBounding(true);
		model2.setScale(0.2f);
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