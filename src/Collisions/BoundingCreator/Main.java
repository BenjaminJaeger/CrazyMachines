package Collisions.BoundingCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import RenderEngine.Core.Config;
import RenderEngine.Core.Camera.Camera;
import RenderEngine.Core.Lights.AmbientLight;
import RenderEngine.Core.Lights.DirectionalLight;
import RenderEngine.Core.Math.Vector2f;
import RenderEngine.Core.Math.Vector3f;
import RenderEngine.Core.Models.LineModel;
import RenderEngine.Core.Models.TriangleModel;
import RenderEngine.Core.Renderer.Renderer;
import RenderEngine.Core.Shaders.Core.BasicShader;
import RenderEngine.Core.Shaders.Core.Material;
import RenderEngine.Primitives.CircleLine;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application implements GLEventListener{

	private Line line;
	private boolean click = true;
	ArrayList<ArrayList<Vector2f>> hulls = new ArrayList<ArrayList<Vector2f>>();
	private ArrayList<Vector2f> currentHull = new ArrayList<Vector2f>();	
	private boolean shapeDone = false;
	
	private String fileName;
	private boolean changeModel;
	
	private Vector2f lastMousePosition;
	
	private FPSAnimator animator;
	private GLJPanel canvas;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	
	private TriangleModel model;
	private ArrayList<LineModel> test = new ArrayList<LineModel>();
	

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		primaryStage.setTitle("Bounding Creator");
		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.show();	
		
		Pane drawingCanvas = new Pane();
		StackPane drawing = new StackPane();
		HBox options = new HBox(10);
		options.setStyle("-fx-background-color: rgb(102,127,102);");
		
		root.setCenter(drawing);
		root.setTop(options);
		
		Button reset = new Button("Reset");
		reset.setStyle("-fx-pref-height: 40px; -fx-pref-width: 150px;");
		reset.setOnAction(e->{
			currentHull = new ArrayList<Vector2f>();
			hulls = new ArrayList<ArrayList<Vector2f>>();
			drawingCanvas.getChildren().remove(0, drawingCanvas.getChildren().size());
		});
		
		Button undo = new Button("Undo");
		undo.setStyle("-fx-pref-height: 40px; -fx-pref-width: 150px;");
		undo.setOnAction(e->{
			drawingCanvas.getChildren().remove(drawingCanvas.getChildren().size()-1);
			drawingCanvas.getChildren().remove(drawingCanvas.getChildren().size()-1);
			drawingCanvas.getChildren().remove(drawingCanvas.getChildren().size()-2);
		
			currentHull.remove(currentHull.size()-1);
			
			line = new Line(currentHull.get(currentHull.size()-1).x,currentHull.get(currentHull.size()-1).y,currentHull.get(currentHull.size()-1).x+10,currentHull.get(currentHull.size()-1).y+10);
			line.setStroke(javafx.scene.paint.Color.CORNSILK);
			line.setStrokeWidth(5);
			drawingCanvas.getChildren().add(line);
		});
		
		Button openFileButton = new Button("Open OBJ file");
		openFileButton.setStyle("-fx-pref-height: 40px; -fx-pref-width: 150px;");
		openFileButton.setOnAction(e->{
			//set up fileChooser
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/res"));
			fileChooser.setTitle("Open OBJ file");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("OBJ files","*.obj"));
			File file = fileChooser.showOpenDialog(primaryStage);
				
			
			if(file!=null) {
				fileName = file.getAbsolutePath();
				
				int beginIndex = fileName.lastIndexOf("res");
				int lastIndex = fileName.indexOf(".obj");
				
				fileName = fileName.substring(beginIndex+4, lastIndex);
				
				changeModel = true;
			}
		});
		
		Button save = new Button("Save");
		save.setStyle("-fx-pref-height: 40px; -fx-pref-width: 150px;");
		save.setOnAction(e->{
			try {
				FileOutputStream file = new FileOutputStream(System.getProperty("user.home")+ "/Desktop/"+fileName+"Bounding.txt");
				for (ArrayList<Vector2f> hull : hulls) {
					file.write("#New BoundingPolygon".getBytes());
					file.write("\n".getBytes());
					for (Vector2f p : hull) {
						String string = p.x + " - " + p.y;
						file.write(string.getBytes());
						file.write("\n".getBytes());
					}
					file.write("\n".getBytes());
				}
				
				file.close();
				
			} catch (FileNotFoundException e1) {e1.printStackTrace();} catch (IOException e1) {e1.printStackTrace();}
		});
		
		options.getChildren().add(openFileButton);
		options.getChildren().add(undo);
		options.getChildren().add(reset);
		options.getChildren().add(save);
		
		
		drawingCanvas.setOnScroll(e -> {
			camera.setZ(camera.getZ()-(float)e.getDeltaY()*0.01f);
		});
		
		drawingCanvas.setOnMousePressed(e->{
			lastMousePosition = new Vector2f((float)e.getX(), (float)e.getY());
		});
		
		drawingCanvas.setOnMouseDragged(e->{
			float deltaY = (float)e.getY() - lastMousePosition.y;
			float deltaX = (float)e.getX() - lastMousePosition.x;
			lastMousePosition=  new Vector2f((float)e.getX(), (float)e.getY());
			if (e.getButton() ==  MouseButton.SECONDARY) {
		        camera.increaseX(-deltaX*1.5f);
		        camera.increaseY(-deltaY*1.5f);
		    }
		});
		
		drawingCanvas.setOnMouseClicked(e->{		
			if(e.getButton() ==  MouseButton.PRIMARY) {
				
				if(shapeDone)
					shapeDone=!shapeDone;
				
				if(currentHull.size()!=0) {
					if(currentHull.get(0).x <=e.getX()+10 && currentHull.get(0).x >= e.getX()-10  && currentHull.get(0).y <=e.getY()+10 && currentHull.get(0).y >= e.getY()-10 ) {
						shapeDone=true;
						
						line.setEndX(currentHull.get(0).x);		
						line.setEndY(currentHull.get(0).y);	
						
						Circle point = new Circle(currentHull.get(0).x, currentHull.get(0).y, 5);
						point.setFill(Color.ORANGE);
						drawingCanvas.getChildren().add(point);						
						
						for (Node shape : drawingCanvas.getChildren()) {
							if (shape instanceof Circle) 
								((Circle) shape).setFill(Color.AQUA);
							if (shape instanceof Line) 
								((Line) shape).setStroke(Color.CRIMSON);
							
						}
						
						hulls.add(currentHull);
						currentHull = new ArrayList<Vector2f>();	
					}
				}
				if (!shapeDone) {
	
					if(click){
						currentHull.add(new Vector2f((float)e.getX(),(float)e.getY()));
						line = new Line(e.getX(),e.getY(),e.getX()+10,e.getY()+10);
						line.setStroke(javafx.scene.paint.Color.CORNSILK);
						line.setStrokeWidth(5);
						drawingCanvas.getChildren().add(line);
						Circle point = new Circle(e.getX(), e.getY(), 5);
						point.setFill(Color.ORANGE);
						drawingCanvas.getChildren().add(point);	
					}
					
					if(!click) {
						currentHull.add(new Vector2f((float)e.getX(),(float)e.getY()));
						line = new Line(e.getX(),e.getY(),e.getX()+10,e.getY()+10);
						line.setStroke(javafx.scene.paint.Color.CORNSILK);
						line.setStrokeWidth(5);
						drawingCanvas.getChildren().add(line);	
						Circle point = new Circle(e.getX(), e.getY(), 5);
						point.setFill(Color.ORANGE);
						drawingCanvas.getChildren().add(point);	
					}
				}
				click=!click;
			}
		});
		
		drawingCanvas.setOnMouseMoved(e->{
			if(currentHull.size()!=0 && !shapeDone) {
				line.setEndX(e.getX());		
				line.setEndY(e.getY());			
			}
		});
		
		//JFX Code für Canvas
		final GLCapabilities capabilities = new GLCapabilities( GLProfile.getDefault());
		canvas = new GLJPanel(capabilities);	    
		SwingNode swingNode = new SwingNode();		 
		drawing.getChildren().add(swingNode);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		    	swingNode.setContent(canvas);	
		    }
		});	
		canvas.addGLEventListener(this);		
		animator = new FPSAnimator(canvas, 60);
	  	animator.start();
	  	
	  	drawing.getChildren().add(drawingCanvas);
	}
	
	@Override
	public void display(GLAutoDrawable arg0) {
		renderer.clear();	
		
		if(changeModel)
			model = new TriangleModel(fileName, new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f), 1, 0, 0, 200, 200);
		
		if(model!=null) 
			renderer.render(model, shader); 
		
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
		camera.setZ(10f);
		
		renderer = new Renderer(camera);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);
		
		model =   new TriangleModel("teapot", basicMaterial, 1, 0, 0, 200, 200);
		
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