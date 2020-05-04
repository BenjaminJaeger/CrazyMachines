package MAIN.Simulation;

import Simulation.Particles.ParticleSystem;
import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Camera.Camera;
import Simulation.RenderEngine.Core.Lights.AmbientLight;
import Simulation.RenderEngine.Core.Lights.DirectionalLight;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Renderer.Renderer;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.CircleLine;
import Simulation.RenderEngine.Primitives.Cube;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class TestingParticleSystem extends Application implements GLEventListener{

    private FPSAnimator animator;
    private GLJPanel canvas;
    private Renderer renderer;
    private BasicShader shader;
    private Camera camera;

    private ParticleSystem particleSystem;

    private ArrayList<LineModel> test = new ArrayList<LineModel>();


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        primaryStage.setTitle("JavaFX OpenGL");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();



        //JFX Code f�r Canvas
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

        particleSystem.update(new Vector2f(0,-2));

        renderer.render(particleSystem.particles, shader);

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

        shader=new BasicShader("Instanced");

        Cube cube = new Cube(10,20,20);


        AmbientLight ambientLight = new AmbientLight(1);

        //								    new DirectionalLight(lightDirection,         diffuseColor,          speculaColor)
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));

        //                       new Material(ambientColor, 				diffuseColor, 				  specularColor, 		   shininess, alpha)
        Material basicMaterial = new Material(new Vector3f(0.2f,0.2f,0.2f), new Vector3f(0.5f,0.5f,0.5f), new Vector3f(1.f, 1.f, 1.f), 10, 1f);

        particleSystem = new ParticleSystem(basicMaterial);

        test.add(new LineModel(new CircleLine(0,0),0,0,0,0,0));
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