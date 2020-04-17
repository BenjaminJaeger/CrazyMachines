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
import Engine.Core.Models.InstancedModel;
import Engine.Core.Renderer.Renderer;
import Engine.Core.Shaders.Core.BasicShader;
import Engine.Core.Shaders.Core.Material;
import Objects.GameObject;
import Objects.MovableObjects.MoveableObject;
import Objects.MovableObjects.Ball.Ball;
import Objects.MovableObjects.Ball.MetallBall;

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
	
	private BasicShader instancedShader;
	private InstancedModel instancedModel;

	private ArrayList<MoveableObject> test = new ArrayList<MoveableObject>();
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
		FPSCounter.calculateFPS();
		
		renderer.clear();	
		
		Config.BACKGROUND_COLOR = new Vector3f(t,0.5f,0.4f);
		if (t>1) 
			tinc*=-1;
		
		if (t<0) 
			tinc*=-1;
		t+=tinc;
		
//		model1.update();
//		renderer.render(model1, shader); 
//		model2.update();
//		renderer.render(model2, shader); 
		
		for (MoveableObject ball : balls) {
			ball.update();
			renderer.render(ball,shader);	
		}
		
//		renderer.render(instancedModel, instancedShader);
		
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

		
//		float[] colors = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
//		model1 = new MetallBall(50, 50, colors, 200, 200);
//		model1.setVelocityX(1);
//		//model1.setVelocityY(4);
//		
//		model2 = new MetallBall(50, 50, colors, 400, 200);
//		model2.setVelocityX(-20);
//		//model2.setVelocityY(-4);
		
		for (int i = 0; i < 20; i++) {
			float[] color = {(float)Math.random(),(float)Math.random(),(float)Math.random()};
			
			float x = (float)Math.random()*Config.CANVAS_WIDTH;
			float y = (float)Math.random()*Config.CANVAS_HEIGHT;
			float mass = (float)Math.random()+0.5f;
			float radius = mass*50;
			float velocityX = (float)Math.random()*2;
			float velocityY = (float)Math.random()*2;
			
			Ball ball = new MetallBall(radius, 40,color, x, y);
			ball.setMass(mass);
			ball.setAccelerationX(velocityX);
			ball.setAccelerationY(velocityY);
			balls.add(ball);		
		}
		
		
		//instanced rendering
//		instancedShader = new BasicShader("Instanced");
//		
//		int instances = 10;
//		float[] colors = new float[instances*3];
//		for (int i = 0; i < instances*3; i+=3) {
//			colors[i]=(float)Math.random();
//			colors[i+1]=(float)Math.random();
//			colors[i+2]=(float)Math.random();
//		}
//		instancedModel = new InstancedModel(new Sphere(30,20), instances, basicMaterial, colors);
//				
//		//random position & scale
//		for (int i = 0; i < instancedModel.getInstances(); i++) {
//			instancedModel.setScale(i, (float)Math.random());
//			instancedModel.setX(i , (float)Math.random()*Config.CANVAS_WIDTH);
//			instancedModel.setY(i ,(float)Math.random()*Config.CANVAS_HEIGHT);
//		}
		
	
		
		//respawn balls
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
		
		float[] color = {0,0,0};
		test.add(new MetallBall(0, 0, color, 0, 0));
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