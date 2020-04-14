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
import Engine.Core.Renderer.Renderer;
import Engine.Core.Shaders.Core.BasicShader;
import Engine.Core.Shaders.Core.Material;
import Objects.GameObject;
import Objects.ImmovableObjects.ImmovableObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;
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
	private ImmovableObject model3;
	
	private ArrayList<GameObject> test = new ArrayList<GameObject>();
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
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
			

		model1.update();
		renderer.render(model1, shader); 
		model2.update();
		renderer.render(model2, shader); 

		for (GameObject ball : balls) {
			ball.update();
			renderer.render(ball,shader);	
		}
			
		
		
		for (GameObject object : test) 
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
		Config.CANVAS_HEIGHT=canvas.getHeight();
		Config.CANVAS_WIDTH=canvas.getWidth();
		
		projectionMatrix=new Matrix4f();
		
		projectionMatrix.changeToPerspecitveMatrix(Config.FIELD_OF_VIEW, Config.NEAR_PLANE, Config.FAR_PLANE,canvas.getHeight(),canvas.getWidth());
		
		camera= new Camera(canvas);
		camera.setZ(1f);
		
		renderer = new Renderer(camera,projectionMatrix);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
										//new DirectionalLight(lightDirection,        diffuseColor,          speculaColor)
		DirectionalLight directionalLight=new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                        new Material(ambientColor, diffuseColor, specularColor, shininess, alpha)
		Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

		
		
		model1 = new MetallBall(50f,40, 200f, 200f);
		model1.setAccelerationX((float)Math.random()*4+2);
		model1.setAccelerationY((float)Math.random()*4+2);
		
		model2 = new MetallBall(100f,40, 400f, 200f);
		model2.setMass(9999999);
		
//		model2.setAccelerationY((float)Math.random()*2);
		
//		model2 = new MetallBox(100f, 400, 200);
//		model2.setAccelerationX(-(float)Math.random()*2);
//		model2.setAccelerationY(-(float)Math.random()*2);
		
		
		for (int i = 0; i < 20; i++) {
			float[] color = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
			
			float x = (float)Math.random()*Config.CANVAS_WIDTH;
			float y = (float)Math.random()*Config.CANVAS_HEIGHT;
			float mass = (float)Math.random()+0.5f;
			float radius = mass*30;
			float velocityX = (float)Math.random()*2;
			float velocityY = (float)Math.random()*2;
			
			
			Ball ball = new Ball(radius, 36, basicMaterial, color, x, y);
			ball.setMass(mass);
			ball.setAccelerationX(velocityX);
			ball.setAccelerationY(velocityY);
			balls.add(ball);		
		}
		

		
		
		test.add(new MetallBox(0f, 0f, 0f));
			
		canvas.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					for (Ball ball : balls) {
						float x = (float)Math.random()*Config.CANVAS_WIDTH;
						float y = (float)Math.random()*Config.CANVAS_HEIGHT;
						float velocityX = (float)Math.random()*2;
						float velocityY = (float)Math.random()*2;
						ball.setY(y);
						ball.setX(x);
						ball.setAccelerationX(velocityX);
						ball.setAccelerationY(velocityY);
					}
				}
			}
		});
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
