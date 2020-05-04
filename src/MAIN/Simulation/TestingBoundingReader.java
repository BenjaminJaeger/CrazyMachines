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

import Objects.Util;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Box.MetallBox;
import Objects.MovableObjects.ExternalObjects.Icosahedron;
import RenderEngine.Core.Config;
import RenderEngine.Core.Camera.Camera;
import RenderEngine.Core.Lights.AmbientLight;
import RenderEngine.Core.Lights.DirectionalLight;
import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Models.LineModel;
import RenderEngine.Core.Renderer.Renderer;
import RenderEngine.Core.Shaders.Core.BasicShader;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.CircleLine;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestingBoundingReader extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private MoveableObject model1;
	private MoveableObject model2;
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();

		primaryStage.setTitle("Circle-Polygon Collision");
		primaryStage.setScene(new Scene(root, 800, 800));
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


//		model1 = new Bucket(basicMaterial, 0, 1, 1, 0, -100);
//		model1.setAccelerationX(Util.getRandomVelocity(4));
//		model1.setAccelerationY(Util.getRandomVelocity(4));
//		model1.renderBounding(true);
//		model1.setScale(0.1f);
		
		model1 = new MetallBox(300,300,300, 0, 0, 1, 0, -100);
//		model1.setAccelerationX(Util.getRandomVelocity(4));
//		model1.setAccelerationY(Util.getRandomVelocity(4));
		model1.renderBounding(true);
		model1.setScale(0.1f);
		
		model2 = new Icosahedron(basicMaterial, 0, 1, 0, 0, 200);
		model2.setAccelerationX(Util.getRandomVelocity(4));
		model2.setAccelerationY(Util.getRandomVelocity(4));
//		model2.setAccelerationY(-2);
		model2.renderBounding(true);
		model2.setScale(0.3f);
		
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