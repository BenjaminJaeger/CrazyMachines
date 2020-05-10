package MAIN.UI;

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
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.MovableObjects.Ball.MetallBall;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestingSimulationControl extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private ArrayList<MoveableObject> allobjects = new ArrayList<MoveableObject>();
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();
	
	private boolean playpauseBoolean;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		root.setAlignment(Pos.TOP_LEFT);

		primaryStage.setTitle("Testing Simulation controler");
		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.show();	
		
		Button playpause = new Button("Play");
		playpause.setOnAction(e->{
			playpauseBoolean=!playpauseBoolean;
			if(playpauseBoolean) {
				playpause.setText("Pause");
				Simulation.play();
			}else {
				playpause.setText("Play");
				Simulation.pause();				
			}
				
		});
		
		
		Button stop = new Button("Stop");
		stop.setOnAction(e->{
			if (playpauseBoolean) {
				Simulation.pause();
				playpause.setText("Play");
				playpauseBoolean=false;
			}
			
			Simulation.restart();
		});
		
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(40);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setValue(Simulation.getUpdateTime());
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			 
	         @Override
	         public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
	        	Simulation.setUpdateTime(newValue.intValue());
	        	if(playpauseBoolean) {
	        		Simulation.pause();
	        		Simulation.play();
	        	}
	         }
	      });
		
		VBox controls = new VBox(10);
		controls.setMaxWidth(400);
		controls.getChildren().addAll(playpause,stop,slider);
		
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
	  	
	  	root.getChildren().add(controls);
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		for (MoveableObject moveableObject : allobjects) 
			renderer.render(moveableObject, shader); 
			
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
	
		for (int i = 0; i < 8; i++) {			
			float x = Util.getRandomPositionX();
			float y = Util.getRandomPositionY();
			float rotation = (float)Math.random()*360;
			float velocityX = Util.getRandomVelocity(4);
			float velocityY = Util.getRandomVelocity(4);
			float radius = (float)Math.random()*30+30;
			float mass = radius;
			
			MoveableObject ball = new MetallBall(radius, 30, (float)Math.random(), (float)Math.random(),(float)Math.random(), x, y);
			ball.setRotation(rotation);
			ball.renderBounding(true);
			ball.setVelocityX(velocityX);
			ball.setVelocityY(velocityY);
			ball.setScale(0.5f);
			ball.setOriginalscale(0.5f);
			allobjects.add(ball);		
		}
		
			
		canvas.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					for (MoveableObject object : allobjects) {
						float rotation = (float)Math.random()*360;
						float x = Util.getRandomPositionX();
						float y = Util.getRandomPositionY();
						
						float velocityX = Util.getRandomVelocity(4);
						float velocityY = Util.getRandomVelocity(4);;
						
						object.setVelocityX(velocityX);
						object.setVelocityY(velocityY);
						
						object.setY(y);
						object.setX(x);
					}

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