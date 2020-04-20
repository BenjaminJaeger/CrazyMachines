package ComputerGraphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import Engine.Core.Config;
import Engine.Core.Camera.Camera;
import Engine.Core.Lights.AmbientLight;
import Engine.Core.Lights.DirectionalLight;
import Engine.Core.Math.Matrix4f;
import Engine.Core.Math.Vector3f;
import Engine.Core.Models.LineModel;
import Engine.Core.Renderer.Renderer;
import Engine.Core.Shaders.Core.BasicShader;
import Engine.Core.Shaders.Core.Material;
import Engine.Primitives.CircleLine;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.MetallBall;
import Objects.MovableObjects.Box.MetallBox;

public class ComputerGraphics  implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private GLWindow canvas2;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	private Matrix4f projectionMatrix;
	
	private MoveableObject model1;
	private MoveableObject model2;
	
	private ArrayList<LineModel> test = new ArrayList<LineModel>();
	private ArrayList<MoveableObject> allObjects = new ArrayList<MoveableObject>();
	
	private float t;
	private float tinc = 0.01f;
	

	public ComputerGraphics(GLJPanel canvas) {
		this.canvas = canvas;
				
		canvas.addGLEventListener(this);
			
		animator = new FPSAnimator(canvas, 60);
	  	animator.start();
	}
	
	public ComputerGraphics(GLWindow canvas) {
		this.canvas2 = canvas;
		
		canvas2.addGLEventListener(this);
			
		animator = new FPSAnimator(canvas2, 60);
	  	animator.start();
	}


	@Override
	public void display(GLAutoDrawable arg0) {	
		renderer.clear();	
		
		Config.BACKGROUND_COLOR = new Vector3f(t,0.5f,0.4f);
		if (t>1) 
			tinc*=-1;
		
		if (t<0) 
			tinc*=-1;
		t+=tinc;
		
//		model1.update();
//		model2.update();
//		renderer.render(model1, shader); 
//		renderer.render(model2, shader); 

		for (MoveableObject object : allObjects) {
			object.update();
			renderer.render(object,shader);	
		}
		
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
		
		projectionMatrix=new Matrix4f();
		
		projectionMatrix.changeToPerspecitveMatrix(Config.FIELD_OF_VIEW, Config.NEAR_PLANE, Config.FAR_PLANE,canvas.getHeight(),canvas.getWidth());
		
		camera= new Camera(canvas);
		camera.setZ(1f);
		
		renderer = new Renderer(camera,projectionMatrix);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
		//								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

		
		
		
//		model1 = new MetallBox(200, 0, 1, 1, 400, 400);	
//		model2 = new MetallBall(100, 30, 0, 1, 1, 300, 300);
		
		for (int i = 0; i < 10; i++) {			
			float x = (float)Math.random()*Config.CANVAS_WIDTH;
			float y = (float)Math.random()*Config.CANVAS_HEIGHT;
			float mass = (float)Math.random()+0.5f;
			float radius = mass*50;
			float velocityX = (float)Math.random()*4;
			float velocityY = (float)Math.random()*4;
			
			MoveableObject ball = new MetallBall(radius, 40,(float)Math.random(),(float)Math.random(),(float)Math.random(), x, y);
			ball.setMass(mass);
			ball.setAccelerationX(velocityX);
			ball.setAccelerationY(velocityY);
			allObjects.add(ball);		
			
			x = (float)Math.random()*Config.CANVAS_WIDTH;
			y = (float)Math.random()*Config.CANVAS_HEIGHT;
			mass = (float)Math.random()+0.5f;
			float size = mass*50;
			velocityX = (float)Math.random()*4;
			velocityY = (float)Math.random()*4;
			float rotation =  (float)Math.random()*360;
			
			MoveableObject box = new MetallBox(size, (float)Math.random(),(float)Math.random(),(float)Math.random(), x, y);
			box.renderBounding(true);
			box.setMass(mass);
			box.setAccelerationX(velocityX);
			box.setAccelerationY(velocityY);
			//box.setRotation(rotation);
			allObjects.add(box);	
		}
		
			
		canvas.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					for (MoveableObject object : allObjects) {
						float x = (float)Math.random()*Config.CANVAS_WIDTH;
						float y = (float)Math.random()*Config.CANVAS_HEIGHT;
						float velocityX = (float)Math.random()*2;
						float velocityY = (float)Math.random()*2;
						object.setY(y);
						object.setX(x);
						object.setAccelerationX(velocityX);
						object.setAccelerationY(velocityY);
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
		
		renderer.getProjectionMatrix().changeToPerspecitveMatrix(Config.FIELD_OF_VIEW, Config.NEAR_PLANE, Config.FAR_PLANE,height,width);
		canvas.setSize(width, height);	
	}
	
}