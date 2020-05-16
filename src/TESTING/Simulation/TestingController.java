package TESTING.Simulation;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Simulation.Objects.MovableObjects.Ball.Ball;
import Simulation.Objects.MovableObjects.Box.TestBox;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import Simulation.Util;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.Objects.MovableObjects.Ball.TestBall;
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
import UI.BenjaminController.ObjectPickingMethods;
import UI.BenjaminController.ObjectTransformer;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestingController extends Application implements GLEventListener{

    //TODO Kopieren, umbennen in ControllerTest, start-Methode bekommt Eventlistener (canvas.addEventListener), ArrayList mit den Kreisen, Bewegung ausstellen, Testen, Maus-Listener implementieren, wenn Objekt geklickt wird,
    //Objekt vom ObjectTransformer erstellen, muss irgendwie gerendert werden, Display-Methode: Wenn eine der beiden Formen erzeugt wurden, dann werden die beiden Lines gerendert (render-Methode nur für Lines)

    private FPSAnimator animator;
    private GLJPanel canvas;
    private Renderer renderer;
    private BasicShader shader;
    private Camera camera;


    private ArrayList<MoveableObject> allobjects = new ArrayList<MoveableObject>();
    private ObjectTransformer objectTransformer;

    private ArrayList<LineModel> test = new ArrayList<LineModel>();


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        primaryStage.setTitle("Circle-Polygon Collision");
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


        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                //Die Methoden selbst gehen durch alles durch
                for (int first = 0; first < allobjects.size(); first++) {

                    for (int second = 0; second < allobjects.get(first).getCollisionContext().getBoundingPolygons().length; second++)
                    {
                        ObjectPickingMethods.choosePolygon(e, canvas, camera, allobjects.get(first));
                    }

                    for (int third = 0; third < allobjects.get(first).getCollisionContext().getBoundingCircles().length; third++)
                    {
                        ObjectPickingMethods.chooseSphere(e, canvas, camera, allobjects.get(first));
                    }
                }

                //ObjectPickingMethods.chooseSphere(e, canvas, camera, allobjects);
            }


            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Radius, ist der Abstand zwischen Punkt und Kreis kürzer als der Radius
        //Zweite if: wie gering ist der Abstand zwischen Radius und Punkt
        //Muss größer sein als Dreiviertel des Radius


        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (int objectCounter = 0; objectCounter < allobjects.size(); objectCounter++) {
                    if(allobjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseCircleLine(e, canvas, camera, allobjects.get(objectCounter).getObjectTransformer().getCircleUI()) == true)
                    {
                        System.out.println(objectCounter);
                        float objectX = allobjects.get(objectCounter).getX() + canvas.getWidth()/2;
                        float objectY = canvas.getHeight()/2 - allobjects.get(objectCounter).getY();

                        float mouseX = (float)e.getX();
                        float mouseY = (float)e.getY();

                        float rotation = -(float)Math.atan2(objectY-mouseY , objectX-mouseX) * 180/(float)Math.PI;
                        allobjects.get(objectCounter).setRotation(rotation);
                    }
                    else if (allobjects.get(objectCounter).isSelected() && ObjectPickingMethods.chooseSquareUI(e, canvas, camera, allobjects.get(objectCounter).getObjectTransformer().getSquareUI()) == true)
                    {
                        float objectX = allobjects.get(objectCounter).getX() + canvas.getWidth()/2;
                        float objectY = canvas.getHeight()/2 - allobjects.get(objectCounter).getY();

                        float mouseX = (float)e.getX();
                        float mouseY = (float)e.getY();

                        float scale = (float)Math.sqrt(Math.pow((objectX-mouseX),2)+Math.pow((objectY-mouseY),2)) /100;
                        allobjects.get(objectCounter).setScale(scale);
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    @Override
    public void display(GLAutoDrawable arg0) {
        renderer.clear();

        for (MoveableObject moveableObject : allobjects) {
            //moveableObject.update();
            renderer.render(moveableObject, shader);
        }

        for (LineModel object : test) {
            renderer.render(object,shader);
        }

        /*
        LineModel lineModel = new LineModel(new CircleLine(30, 30), 0, 0, 0, 0, 0);
        renderer.render(lineModel, objectTransformer.getCircleUI().getShader());
         */

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


        for (int i = 0; i < 1; i++) {
            float x = 100 - 50 * i;
            float y = 100 - 80 * i;
            float rotation = 96;
            //float velocityX = 135;
            //float velocityY = Util.getRandomVelocity(4);
            float radius = (float)Math.random()*30+30;
            //float mass = radius;
            float mass = 1;
            float size = mass*50;

            MoveableObject box = new TestBox(size, (float)Math.random(), (float)Math.random(), (float)Math.random(), x, y);
            //box.renderBounding(true);
            box.setMass(mass);
            //box.setAccelerationX(velocityX);
            //box.setAccelerationY(velocityY);
            box.setRotation(rotation);
            allobjects.add(box);
        }


        for (int i = 0; i < 1; i++) {
            float x = 20;
            float y = 20;
            float mass = 0.8f;
            float radius = mass*40;
            //float velocityX = Util.getRandomVelocity(4);
            //float velocityY = Util.getRandomVelocity(4);

            MoveableObject ball = new TestBall(radius, 40,(float)Math.random(),(float)Math.random(),(float)Math.random(), x, y);
            ball.setMass(mass);
            //ball.setAccelerationX(velocityX);
            //ball.setAccelerationY(velocityY);
            //ball.renderBounding(true);
            allobjects.add(ball);
        }

        //objectTransformer = new ObjectTransformer(allobjects.get(1));

        allobjects.get(0).setObjectTransformer(new ObjectTransformer(allobjects.get(0)));
        allobjects.get(1).setObjectTransformer(new ObjectTransformer(allobjects.get(1)));

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

//		frame = new Frame();

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