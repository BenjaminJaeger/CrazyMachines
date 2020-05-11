package MAIN.Simulation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import Simulation.Simulation;
import Simulation.Util;
import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.MovableObjects.Ball.MetallBall;
import Simulation.Objects.StaticObjects.StaticBox;
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

public class Testing extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
	
	private GameObject model1;
	private MoveableObject model2;
	private GameObject model3;
	private GameObject model4;
	
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		    	swingNode.setContent(canvas);	
		    }
		});	
		canvas.addGLEventListener(this);		
		animator = new FPSAnimator(canvas, 60);
	  	animator.start();
	  	
		root.getChildren().add(swingNode);
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		for (GameObject object : allobjects) 
			renderer.render(object, shader); 
		
		renderer.render(model1, shader);
		renderer.render(model2, shader);
		renderer.render(model3, shader);
		renderer.render(model4, shader);
		
		for (LineModel object : test) 
			renderer.render(object,shader);			
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		animator.stop();
		Simulation.pause();
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

		model2 = new MetallBall(20, 30,1 , 1, 1, 150, 80);
//		model2.renderBounding(true);
		model2.setAccelerationX(-1);
		
		model1 = new StaticBox(200, 50, 0, 1, 1, 0, 0);
//		model1.renderBounding(true);
		model1.setRotation(20);
			
		model3 = new StaticBox(800, 50, 0, 1, 1, 0,-200);
//		model3.renderBounding(true);
		
		model4 = new StaticBox(200, 50, 0, 1, 1, 180,35);
//		model4.renderBounding(true);
		
		for (int i = 0; i < 0; i++) {			
			float x = Util.getRandomPositionX();
			float y = Util.getRandomPositionY();
			float width = (float)Math.random()*70+20;
			float height = (float)Math.random()*70+20;
			float rotation =(float)Math.random()*360;
			
//			GameObject box = new MetallBox(width,height,50, (float)Math.random(), (float)Math.random(), (float)Math.random(), x, y);
//			box.setRotation(rotation);
//			box.renderBounding(true);
//			allobjects.add(box);		
			
			x = Util.getRandomPositionX();
			y = Util.getRandomPositionY();
			float radius = (float)Math.random()*30+20;
				
			GameObject ball = new MetallBall(radius, 40, (float)Math.random(), (float)Math.random(), (float)Math.random(), x, y);
			ball.renderBounding(true);
			allobjects.add(ball);
		}
		
			
		canvas.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
//					for (GameObject object : allobjects) {
//						float rotation = (float)Math.random()*360;
//						float x = Util.getRandomPositionX();
//						float y = Util.getRandomPositionY();
//						object.setY(y);
//						object.setX(x);
//						object.setRotation(rotation);
//					}
					Simulation.play();
				}
			}
		});
				
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