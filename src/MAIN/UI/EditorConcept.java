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

import Simulation.Simulation;
import Simulation.Collisions.Boundings.Bounding;
import Simulation.Objects.GameObject;
import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.MetaObjects.Moveable.MetallBallMeta;
import Simulation.Objects.MetaObjects.Static.PlankMeta;
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
import UI.EditorTabPane;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditorConcept extends Application implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		//stackpane for switching between layouts
		StackPane root = new StackPane();
		
		Button playpause = new Button("Play");
		playpause.setOnAction(e->{
			if(Simulation.isPlaying()) {
				playpause.setText("Play");
				Simulation.pause();	
			}else {
				playpause.setText("Pause");
				Simulation.play();			
			}
				
		});
			
		Button stop = new Button("Restart");
		stop.setOnAction(e->{
			if (Simulation.isPlaying()) {
				Simulation.pause();
				playpause.setText("Play");
			}
			
			Simulation.restart();
		});
		
		Button clear = new Button("Clear");
		clear.setOnAction(e->{
			if (!Simulation.isPlaying()) 
				allObjects.clear();
		});
		
		HBox controls = new HBox(10);
		controls.setAlignment(Pos.CENTER);
		controls.getChildren().addAll(playpause,stop,clear);

		//pane for moving around the Image
		Pane dragAnimator = new Pane ();
		ImageView animateObject = new ImageView ();
		dragAnimator.getChildren().add(animateObject);

		//BorderPane for managing the general layout of the editor
		BorderPane layout = new BorderPane();

		root.getChildren().addAll(dragAnimator, layout);
		layout.toFront();
		layout.setStyle("-fx-background-color: rgb(102,127,102);");

		//build TabPane in bottom part of UI
		EditorTabPane etp = new EditorTabPane ();
		TabPane tabPane = etp.buildTabPane();
		layout.setBottom(tabPane);

		//window specs and show
		primaryStage.setTitle("Editor Alpha");
		primaryStage.setScene(new Scene(root, 1400, 900));
		primaryStage.show();
		
		//Simulation Canvas
		final GLCapabilities capabilities = new GLCapabilities( GLProfile.getDefault());
		canvas = new GLJPanel(capabilities);
		//wrapping OpenGL into a swing component so JavaFX could run it
		SwingNode canvasWrapper = new SwingNode();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				canvasWrapper.setContent(canvas);
			}
		});	
		canvas.addGLEventListener(this);		
		animator = new FPSAnimator(canvas, 60);
		animator.start();

		//event for creating an object after releasing the drag
		root.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
			layout.toFront();
			//define specs of mouse pointer and canvas size
			double mX = e.getSceneX();
			double mY = e.getSceneY();
			double cX = canvasWrapper.getLayoutX();
			double cY = canvasWrapper.getLayoutY();
			double cH = canvas.getHeight();
			double cW = canvas.getWidth();
			//Check if pointer is hovering above canvas
			if (etp.isDragging()
			&& mX >= cX && mY >= cY
			&& mX <= cX+cW && mY <= cY+cH) {
				//get currently dragged object from EditorTabPane "etp"
				MetaObject dObj = etp.getDraggedObject();
				
				//Convert Mouse Position
	  			float x = ((float)e.getX() - (float)root.getWidth()/2) * 1.35f;
	  			float y = ((float)root.getHeight()/2 - (float)e.getY()) * 0.55f;

	  			// Always check i the MetaObject dObj is an instance of a certain specific MetaObj
				// to assure a secure type cast for the object later
				if (dObj instanceof MetallBallMeta) {
					//Im Meta Objekt sollte dafür eine Abstrakte Methode sein
					allObjects.add(new MetallBall(30, 30, (float)Math.random(), (float)Math.random(), (float)Math.random(), x, y));
				}
				else if (dObj instanceof PlankMeta) {
					allObjects.add(new StaticBox(200, 20,  (float)Math.random(), (float)Math.random(), (float)Math.random(), x, y));
				}
			}

			etp.resetDrag();
		});

		//event for animating the drag
		root.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
			if (etp.isDragging()) {
				dragAnimator.toFront();
				animateObject.setImage(new Image(etp.getDraggedObject().getObjectImageURL()));
				double size = 100;
				animateObject.setFitHeight(size);
				animateObject.setFitWidth(size);
				animateObject.relocate(e.getX()-(size/2), e.getY()-(size/2));
			}
		});


		//add canvas to ui
		layout.setCenter(canvasWrapper);
		layout.setTop(controls);
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		for (GameObject moveableObject : allObjects) 
			renderer.render(moveableObject, shader); 
		
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
		Bounding.shader = new BasicShader("Line");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

		
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