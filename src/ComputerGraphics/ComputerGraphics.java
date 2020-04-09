package ComputerGraphics;

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

public class ComputerGraphics  implements GLEventListener{

	private FPSAnimator animator;
	private GLJPanel canvas;
	private GLWindow canvas2;
	private Renderer renderer;
	private BasicShader shader;
	private Camera camera;
	private Matrix4f projectionMatrix;
	private GameObject model; 
	
	private ArrayList<GameObject> test = new ArrayList<GameObject>();
	
	private float t;
	private float tinc = 0.01f;
	
	private float modelX;
	private float modelY;

	
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
			
		model.increaseRotation(0.1f,0.5f);
		model.setX(modelX);
		model.setY(modelY);
		
		renderer.render(model, shader); 
		//renderer.render(model2, shader); 
		
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
		Config.BACKGROUND_COLOR = new Vector3f(0.4f,0.5f,0.4f);
		
		projectionMatrix=new Matrix4f();
		
		projectionMatrix.changeToPerspecitveMatrix(Config.FIELD_OF_VIEW, Config.NEAR_PLANE, Config.FAR_PLANE,canvas.getHeight(),canvas.getWidth());
		
		camera= new Camera(canvas);
		camera.setZ(10);
		
		renderer = new Renderer(camera,projectionMatrix);	
		
		shader=new BasicShader("PhongColor");
	
		AmbientLight ambientLight = new AmbientLight(1);    
		
										//new DirectionalLight(lightDirection,        diffuseColor,          speculaColor)
		DirectionalLight directionalLight=new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
		
		//                        new Material(ambientColor, diffuseColor, specularColor, shininess, alpha)
		Material basicMaterial = new Material( new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 16, 1f);
		
		//Sphere sphere = new Sphere(20);
		
		
		String[] files = {"cube"};
		float[][] colors = {{1f,0f,0f}};
		
		model = new GameObject(files, basicMaterial, colors, 0f, 0f);
		
		test.add(new GameObject(files, basicMaterial, colors, 0f, 0f));
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL4 gl=(GL4)GLContext.getCurrentGL();
		gl.glViewport(0, 0, width, height);
		Config.CANVAS_HEIGHT=height;
		Config.CANVAS_WIDTH=width;
		
		renderer.getProjectionMatrix().changeToPerspecitveMatrix(Config.FIELD_OF_VIEW, Config.NEAR_PLANE, Config.FAR_PLANE,canvas.getHeight(),canvas.getWidth());
		canvas.setSize(width, height);	
	}
	
	public void moveModel(float x,float y) {
		//modelX = convertX(x)/100;
		//modelY = -convertY(y)/100;
	}
	
	public float convertX(float x) {
		return x-Config.CANVAS_WIDTH/2;
	}

	public float convertY(float y) {
		return y-Config.CANVAS_HEIGHT/2;
	}
}
