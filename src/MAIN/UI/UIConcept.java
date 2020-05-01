package MAIN.UI;

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

import Objects.ImmovableObjects.Plank;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.MetallBall;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UIConcept extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private ArrayList<MoveableObject> allobjects = new ArrayList<MoveableObject>();
	private ArrayList<Plank> frame = new ArrayList<Plank>();
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();
		root.setStyle("-fx-background-color: rgb(102,127,102);");

		primaryStage.setTitle("UI Test");
		primaryStage.setScene(new Scene(root, 1400, 900));
		primaryStage.show();	
		
		
		//Right Bar (Start/Stop Simulation)
		BorderPane rightBarContent = new BorderPane();
		rightBarContent.setMinWidth(230);
		VBox objectDetails = new VBox(20);
		objectDetails.setPadding(new Insets(10,10,10,10));
		ScrollPane s2 = new ScrollPane();
		s2.setContent(objectDetails);
		s2.setHbarPolicy(ScrollBarPolicy.NEVER);
		s2.getStylesheets().add(UIConcept.class.getResource("ScrollPane.css").toExternalForm());
		
		for (int i = 0; i < 10; i++) {
			HBox all = new HBox(20);
			all.getChildren().add(new Circle(50,Color.color(Math.random(), Math.random(), Math.random())));
			VBox details = new VBox(2);
			details.setAlignment(Pos.CENTER_LEFT);
			details.getChildren().add(new Label("Ball "+(i+1)));
			details.getChildren().add(new Label("Speed: x"));
			details.getChildren().add(new Label("Acc: y"));
			all.getChildren().add(details);
			objectDetails.getChildren().add(all);
		}
		
		
		HBox playpauseSimulation = new HBox(10);
		playpauseSimulation.setAlignment(Pos.CENTER);
		playpauseSimulation.setPadding(new Insets(10,10,10,10));
		playpauseSimulation.setStyle("-fx-background-color: rgb(255,255,255);");
		Polygon play = new Polygon();
		play.setFill(Color.GREEN);
		play.getPoints().addAll(new Double[]{0.0, 0.0, 50.0, 25.0, 0.0, 50.0 });	
		Rectangle pause = new Rectangle(50, 50);
		pause.setFill(Color.RED);
		playpauseSimulation.getChildren().addAll(play,pause);
		
		rightBarContent.setCenter(s2);
		rightBarContent.setBottom(playpauseSimulation);
		
		
		
		
		//Bottom Bar (Object chooser)
		TabPane placeAbleObjects = new TabPane();
		placeAbleObjects.setPadding(new Insets(0, 40, 0, 40));
		placeAbleObjects.setMinHeight(170);
		placeAbleObjects.setTabMinWidth(110);
		placeAbleObjects.setTabMinHeight(20);
		placeAbleObjects.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		placeAbleObjects.getStylesheets().add(UIConcept.class.getResource("ObjectChooser.css").toExternalForm());
		
		for (int i = 0; i < 8; i++) {
			Tab tab = new Tab("Category"+(i+1));
			HBox elementsTab = new HBox(20);
			elementsTab.setStyle(" -fx-background-color: transparent;");
			ScrollPane s1 = new ScrollPane();
			s1.setContent(elementsTab);
			s1.setVbarPolicy(ScrollBarPolicy.NEVER);
			s1.getStylesheets().add(UIConcept.class.getResource("ScrollPane.css").toExternalForm());
			elementsTab.setPadding(new Insets(10, 10, 10, 10));
			for (int j = 0; j < 4; j++) {
				elementsTab.getChildren().add(new Circle(50,Color.color(Math.random(), Math.random(), Math.random())));
				elementsTab.getChildren().add(new Rectangle(100, 100,Color.color(Math.random(), Math.random(), Math.random())));
				Polygon triangle = new Polygon();
				triangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
				triangle.getPoints().addAll(new Double[]{0.0, 100.0,100.0, 100.0,50.0, 0.0 });
				elementsTab.getChildren().add(triangle);
				Polygon hexagon = new Polygon();
				hexagon.setFill(Color.color(Math.random(), Math.random(), Math.random()));
				hexagon.getPoints().addAll(new Double[]{20.0, 0.0,80.0, 0.0,100.0, 50.0, 80.0, 100.0,20.0, 100.0,0.0, 50.0 });
				elementsTab.getChildren().add(hexagon);
			}
			tab.setContent(s1);
			placeAbleObjects.getTabs().add(tab);
		}
		
		
		
		
		//Simulation Canvas
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
	  	
	  	
	  	BorderPane canvasAndObjectChooser = new BorderPane();
	  	canvasAndObjectChooser.setCenter(swingNode);
	  	canvasAndObjectChooser.setBottom(placeAbleObjects);
	  	
	  	
	  	root.getChildren().addAll(canvasAndObjectChooser,rightBarContent);
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		for (MoveableObject moveableObject : allobjects) {
			moveableObject.update();
			renderer.render(moveableObject, shader); 
		}
		
		for (Plank plank : frame) 
			renderer.render(plank, shader); 

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
			float x = (float)Math.random()*Config.CANVAS_WIDTH;
			float y = (float)Math.random()*Config.CANVAS_HEIGHT;
			float mass = (float)Math.random()+0.5f;
			float radius = mass*50;
			float velocityX = (float)Math.random()*2;
			float velocityY = (float)Math.random()*2;
			
			MoveableObject ball = new MetallBall(radius, 40,(float)Math.random(),(float)Math.random(),(float)Math.random(), x, y);
			ball.setMass(mass);
			ball.setAccelerationX(velocityX);
			ball.setAccelerationY(velocityY);
			ball.renderBounding(true);
			allobjects.add(ball);		
		}
		
		float frameWidth = 10;
		frame.add(new Plank(frameWidth, Config.CANVAS_HEIGHT, 180, 180, 180, 0, Config.CANVAS_HEIGHT/2));
		frame.add(new Plank(frameWidth, Config.CANVAS_HEIGHT, 180, 180, 180, Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT/2));
		frame.add(new Plank(Config.CANVAS_WIDTH, frameWidth, 180, 180, 180, Config.CANVAS_WIDTH/2, 0));
		frame.add(new Plank(Config.CANVAS_WIDTH, frameWidth, 180, 180, 180, Config.CANVAS_WIDTH/2, Config.CANVAS_HEIGHT));
		
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